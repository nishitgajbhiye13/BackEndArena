package com.BackEndArena.execution_engine;


import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_contract.contract.TestCase;
import com.BackEndArena.execution_contract.contract.TestCaseExecutionResult;
import com.BackEndArena.execution_engine.runner.JavaRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ExecutionEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutionEngineApplication.class, args);
    }


//    @Bean
//    CommandLineRunner testJavaRunner() {
//        return args -> {
//
//            JavaRunner javaRunner = new JavaRunner();
//
//            System.out.println("===== BACKENDARENA ENGINE MANUAL TEST =====");
//
//            //  Hardcoded user code
//            String sourceCode = """
//                    import java.util.*;
//                    public class Main {
//                        public static void main(String[] args) {
//                            Scanner sc = new Scanner(System.in);
//                            int a = sc.nextInt();
//                            int b = sc.nextInt();
//                            System.out.println(a + b);
//                        }
//                    }
//                    """;
//
//
//            List<TestCase> testCases = List.of(
//                    new TestCase("1 2\n", "3", false),
//                    new TestCase("10 20\n", "30", false),
//                    new TestCase("100 200\n", "300", true)
//            );
//
//            Long submissionId = 1L;
//
//            ExecutionResult executionResult =
//                    javaRunner.run(submissionId, sourceCode, testCases);
//
//            List<TestCaseExecutionResult> results = executionResult.getTestCaseResults();
//            for (int i = 0; i < results.size(); i++) {
//
//                TestCaseExecutionResult r = results.get(i);
//                TestCase tc = testCases.get(i);
//
//                System.out.println("\n========== TEST CASE " + (i + 1) + " ==========");
//                System.out.println("Input:");
//                System.out.print(tc.getInput());
//
//                System.out.println("Output:");
//                System.out.print(r.getOutput());
//
//                System.out.println("Execution Time: " + r.getExecutionTimeMs() + " ms");
//                System.out.println("Timed Out: " + r.isTimedOut());
//                System.out.println("Exit Code: " + r.getExitCode());
//            }
//
//        };
//    }
}




