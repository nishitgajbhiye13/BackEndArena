package com.BackEndArena.bea.execution.result;


import com.BackEndArena.bea.submission.ProblemSubmission;
import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_contract.contract.TestCase;

import java.util.List;

public interface ExecutionResultHandler {

    void handleResult(
            ProblemSubmission submission,
            ExecutionResult executionResult,
            List<TestCase> testCases
    );
}
