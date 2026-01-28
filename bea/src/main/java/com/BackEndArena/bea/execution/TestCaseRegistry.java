package com.BackEndArena.bea.execution;



import com.BackEndArena.execution_contract.contract.TestCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TestCaseRegistry {

    /**
     * TEMPORARY DEMO-ONLY REGISTRY
     *
     * ❌ No DB
     * ❌ No business logic
     * ✅ Easy to delete after demo
     */
    private static final Map<Long, List<TestCase>> DEMO_TEST_CASES =
            Map.of(
                    3L, List.of(
                            new TestCase("2 3", "5", false),
                            new TestCase("10 20", "30", false),
                            new TestCase("0 0", "0", false)
                    )
            );
    public List<TestCase> getTestCasesForProblem(Long problemId) {
        return DEMO_TEST_CASES.getOrDefault(problemId, List.of());
    }
}

