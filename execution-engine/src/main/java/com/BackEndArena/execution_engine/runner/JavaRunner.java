package com.BackEndArena.execution_engine.runner;

import com.BackEndArena.execution_contract.contract.ExecutionResult;
import com.BackEndArena.execution_contract.contract.TestCase;
import com.BackEndArena.execution_contract.contract.TestCaseExecutionResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Component
public class JavaRunner {

    private static final long TIME_LIMIT_MS = 2000;
    private static final int EXIT_CODE_TIMEOUT = -1;

    public ExecutionResult run(
            Long submissionId,
            String sourceCode,
            List<TestCase> testCases
    ) throws Exception {

        Path tempDir = Files.createTempDirectory("exec-java-");

        try {
            // =========================
            // 1️⃣ Write source file
            // =========================
            Path sourceFile = tempDir.resolve("Main.java");
            Files.writeString(sourceFile, sourceCode);

            // =========================
            // 2️⃣ Compile ONCE
            // =========================
            Process compileProcess = new ProcessBuilder(
                    "javac",
                    sourceFile.getFileName().toString() // Main.java
            )
                    .directory(tempDir.toFile())
                    .redirectErrorStream(true)
                    .start();

            String compileOutput = readOutput(compileProcess);
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode != 0) {
                return new ExecutionResult(
                        submissionId,
                        true,             // compilationError
                        compileOutput,
                        List.of(),
                        0
                );
            }

            // =========================
            // 3️⃣ Execute test cases
            // =========================
            long totalExecutionTimeMs = 0;
            List<TestCaseExecutionResult> testCaseResults = new ArrayList<>();

            for (TestCase testCase : testCases) {

                Process runProcess = new ProcessBuilder(
                        "java",
                        "Main"
                )
                        .directory(tempDir.toFile())
                        .redirectErrorStream(true)
                        .start();

                // write input
                try (BufferedWriter writer =
                             new BufferedWriter(
                                     new OutputStreamWriter(runProcess.getOutputStream()))) {
                    writer.write(testCase.getInput());
                    writer.newLine();
                    writer.flush();
                }

                long startTime = System.currentTimeMillis();
                boolean finished =
                        runProcess.waitFor(TIME_LIMIT_MS, TimeUnit.MILLISECONDS);
                long endTime = System.currentTimeMillis();

                long executionTimeMs = endTime - startTime;
                totalExecutionTimeMs += executionTimeMs;

                if (!finished) {
                    runProcess.destroyForcibly();
                    testCaseResults.add(new TestCaseExecutionResult(
                            "TIME_LIMIT_EXCEEDED",
                            executionTimeMs,
                            true,
                            EXIT_CODE_TIMEOUT
                    ));
                    continue;
                }

                String output = readOutput(runProcess);
                int exitCode = runProcess.exitValue();

                if (exitCode != 0) {
                    testCaseResults.add(new TestCaseExecutionResult(
                            output,
                            executionTimeMs,
                            true,
                            exitCode
                    ));
                    continue;
                }

                testCaseResults.add(new TestCaseExecutionResult(
                        output,
                        executionTimeMs,
                        false,
                        exitCode
                ));
            }

            return new ExecutionResult(
                    submissionId,
                    false,
                    "",
                    testCaseResults,
                    totalExecutionTimeMs
            );

        } finally {
            // =========================
            // 4️⃣ Cleanup LAST
            // =========================
            deleteDirectoryRecursively(tempDir);
        }
    }

    private String readOutput(Process process) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(process.getInputStream()))) {

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        }
    }

    private void deleteDirectoryRecursively(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException ignored) {
                    }
                });
    }
}
