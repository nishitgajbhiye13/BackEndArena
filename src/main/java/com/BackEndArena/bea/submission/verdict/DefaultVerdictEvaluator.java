package com.BackEndArena.bea.submission.verdict;

import com.BackEndArena.bea.submission.Verdict;
import com.BackEndArena.execution_contract.contract.TestCase;
import com.BackEndArena.execution_contract.contract.TestCaseExecutionResult;
import org.springframework.stereotype.Component;

import java.util.List;





@Component
public class DefaultVerdictEvaluator implements VerdictEvaluator {

    @Override
    public Verdict evaluate(
            List<TestCaseExecutionResult> results,
            List<TestCase> testCases
    ) {
        for (int i = 0; i < results.size(); i++) {

            TestCaseExecutionResult result = results.get(i);
            TestCase testCase = testCases.get(i);

            // TIME LIMIT EXCEEDED
            if (result.getExitCode() == -1) {
                return Verdict.TLE;
            }

            // RUNTIME ERROR
            if (result.getExitCode() != 0) {
                return Verdict.RTE;
            }

            // WRONG ANSWER
            if (!normalize(result.getOutput())
                    .equals(normalize(testCase.getExpectedOutput()))) {
                return Verdict.WA;
            }
        }

        return Verdict.AC;
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().replaceAll("\\s+", " ");
    }
}


