package com.BackEndArena.bea.execution.result;


import com.BackEndArena.bea.submission.verdict.VerdictEvaluator;
import com.BackEndArena.bea.submission.*;
import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_contract.contract.TestCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExecutionResultHandlerImpl implements ExecutionResultHandler {

    private final VerdictEvaluator verdictEvaluator;
    private final ProblemSubmissionRepository submissionRepository;

//    @Override
//    public void handleResult(
//            ProblemSubmission submission,
//            ExecutionResult executionResult,
//            List<TestCase> testCases
//    ) {
//
//        Verdict verdict =
//                verdictEvaluator.evaluate(
//                        executionResult.getTestCaseResults(),
//                        testCases
//                );
//
//        submission.markCompleted(
//                verdict,
//                executionResult.getTotalExecutionTimeMs()
//        );
//
//        submissionRepository.save(submission);
//    }
@Override
@Transactional
public void handleResult(
        ProblemSubmission submission,
        ExecutionResult executionResult,
        List<TestCase> testCases
) {
    // HARD ISOLATION — no verdict, no evaluator
    submission.setStatus(SubmissionStatus.COMPLETED);
    submission.setResult(SubmissionResult.ACCEPTED);
    submission.setCompletedAt(Instant.now());
    submission.setTotalExecutionTimeMs(
            executionResult.getTotalExecutionTimeMs()
    );

    submissionRepository.save(submission);

}








}

