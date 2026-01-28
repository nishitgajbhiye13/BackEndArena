package com.BackEndArena.execution_engine.controller;

import com.BackEndArena.execution_contract.contract.ExecutionRequest;




import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_engine.runner.JavaRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/execute")
public class ExecutionController {

    private final JavaRunner javaRunner;

    public ExecutionController(JavaRunner javaRunner) {
        this.javaRunner = new JavaRunner();
    }

    @PostMapping
    public ExecutionResult execute(@RequestBody ExecutionRequest request) {
        System.out.println("===== ENGINE RECEIVED REQUEST =====");
        System.out.println("Submission ID: " + request.getSubmissionId());
        System.out.println("Language: " + request.getLanguage());
        System.out.println("Test case count: " + request.getTestCases().size());
        ExecutionResult executionResult;
        try {
            executionResult = javaRunner.run(request.getSubmissionId(),
                    request.getSourceCode(),
                    request.getTestCases()
            );
        } catch (Exception e) {
            throw new RuntimeException("Execution failed", e);

        }

        System.out.println("===== ENGINE EXECUTION COMPLETE =====");
        System.out.println("Total time: " + executionResult.getTotalExecutionTimeMs());
        System.out.println("Test case results: " +
                executionResult.getTestCaseResults().size());
        return executionResult;

    }
}
