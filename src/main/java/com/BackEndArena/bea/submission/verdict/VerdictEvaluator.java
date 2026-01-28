package com.BackEndArena.bea.submission.verdict;

import com.BackEndArena.bea.submission.Verdict;
import com.BackEndArena.execution_contract.contract.TestCase;
import com.BackEndArena.execution_contract.contract.TestCaseExecutionResult;

import java.util.List;

public interface VerdictEvaluator {
    Verdict evaluate(
            List<TestCaseExecutionResult> executionResults,
            List<TestCase> testCases
    );
}
