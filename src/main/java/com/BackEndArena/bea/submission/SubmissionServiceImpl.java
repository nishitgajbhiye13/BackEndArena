package com.BackEndArena.bea.submission;

import com.BackEndArena.bea.attempt.AttemptStatus;
import com.BackEndArena.bea.attempt.ProblemAttempt;
import com.BackEndArena.bea.attempt.ProblemAttemptRepository;
import com.BackEndArena.bea.exception.ForbiddenException;
import com.BackEndArena.bea.exception.ResourceNotFoundException;
import com.BackEndArena.bea.execution.TestCaseRegistry;
import com.BackEndArena.bea.execution.client.ExecutionEngineClient;
import com.BackEndArena.bea.execution.result.ExecutionResultHandler;
import com.BackEndArena.bea.submission.dto.SubmissionResultResponse;
import com.BackEndArena.bea.submission.dto.SubmitCodeRequest;
import com.BackEndArena.bea.submission.verdict.VerdictEvaluator;
import com.BackEndArena.execution_contract.contract.ExecutionLimits;
import com.BackEndArena.execution_contract.contract.ExecutionRequest;
import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_contract.contract.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Transactional
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {

    private final ProblemAttemptRepository problemAttemptRepository;
    private final ProblemSubmissionRepository problemSubmissionRepository;
    private final ExecutionEngineClient executionEngineClient;
    private final ExecutionResultHandler executionResultHandler;
    private final TestCaseRegistry testCaseRegistry;
    private final VerdictEvaluator verdictEvaluator;

    public SubmissionServiceImpl(
            ProblemAttemptRepository problemAttemptRepository,
            ProblemSubmissionRepository problemSubmissionRepository, ExecutionEngineClient executionEngineClient, ExecutionResultHandler executionResultHandler, TestCaseRegistry testCaseRegistry,
            VerdictEvaluator verdictEvaluator) {
        this.problemAttemptRepository = problemAttemptRepository;
        this.problemSubmissionRepository = problemSubmissionRepository;
        this.executionEngineClient = executionEngineClient;
        this.executionResultHandler = executionResultHandler;
        this.testCaseRegistry = testCaseRegistry;
        this.verdictEvaluator = verdictEvaluator;
    }

    @Override
    public  ProblemSubmission submitCode(Long userId, SubmitCodeRequest request) {

        ProblemAttempt attempt = problemAttemptRepository
                .findById(request.getAttemptId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attempt not found")
                );

        // Ownership check (critical)
        if (!attempt.getUser().getId().equals(userId)) {
            throw new ForbiddenException("Not allowed to submit for this attempt");
        }

        ProblemSubmission submission = new ProblemSubmission(
                attempt,
                request.getCode(),
                request.getLanguage()
        );

        ProblemSubmission savedSubmission = problemSubmissionRepository.save(submission);

        ExecutionLimits executionLimits = new ExecutionLimits(
                2000,   // timeLimitMs
                256     // memoryLimitMb
        );

        Long problemId = attempt.getProblem().getId();

        List<TestCase> testCases =
                testCaseRegistry.getTestCasesForProblem(problemId);


        ExecutionRequest executionRequest = new ExecutionRequest(
                savedSubmission.getId(),
                savedSubmission.getCode(),
                savedSubmission.getLanguage(),
                testCases,              // testCases (empty for now)
                executionLimits
        );




        System.out.println("=== [BackendArena] About to call Execution Engine ===");
        System.out.println("Language: " + executionRequest.getLanguage());
        System.out.println("Code length: " + executionRequest.getSourceCode().length());
        System.out.println("TestCases count: " + executionRequest.getTestCases().size());



        ExecutionResult executionResult =
                executionEngineClient.execute(executionRequest);

        log.info("ENGINE RESULT RECEIVED: {}", executionResult);

        // 1. Evaluate verdict
        Verdict verdict = verdictEvaluator.evaluate(
                executionResult.getTestCaseResults(),
                testCases
        );

        log.info("FINAL VERDICT: {}", verdict);
        log.info("TOTAL TIME: {}", executionResult.getTotalExecutionTimeMs());

        // 2. Update submission
        submission.setVerdict(verdict);
        submission.setStatus(SubmissionStatus.COMPLETED);
        submission.setTotalExecutionTimeMs(
                executionResult.getTotalExecutionTimeMs()
        );
        submission.setCompletedAt(Instant.now());

        // 3. Persist submission
        problemSubmissionRepository.save(submission);

        // 4. Update attempt
        attempt.setStatus(AttemptStatus.COMPLETED);
        attempt.setCompletedAt(Instant.now());
        problemAttemptRepository.save(attempt);





        System.out.println("=== [BackendArena] Execution Engine responded ===");

        System.out.println("Compilation error: " + executionResult.isCompilationError());
        System.out.println("Compilation output: " + executionResult.getCompilationOutput());

        System.out.println("Test case count: " +
                executionResult.getTestCaseResults().size());

        System.out.println("Total execution time (ms): " +
                executionResult.getTotalExecutionTimeMs());




// TEMP: testCases empty until wired
        executionResultHandler.handleResult(
                savedSubmission,
                executionResult,
                testCases
        );


        return savedSubmission;



    }
    @Override
    public SubmissionResultResponse getSubmissionResult(Long submissionId) {

        ProblemSubmission submission = problemSubmissionRepository
                .findById(submissionId)
                .orElseThrow(() ->
                        new RuntimeException("Submission not found"));

        return new SubmissionResultResponse(
                submission.getId(),
                submission.getStatus(),
                submission.getVerdict(),
                submission.getTotalExecutionTimeMs()
        );
    }

}
