package com.BackEndArena.execution_contract.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionResult {

    private final Long submissionId;
    private final boolean compilationError;
    private final String compilationOutput;
    private final List<TestCaseExecutionResult> testCaseResults;
    private final long totalExecutionTimeMs;

    @JsonCreator
    public ExecutionResult(
            @JsonProperty("submissionId") Long submissionId,
            @JsonProperty("compilationError") boolean compilationError,
            @JsonProperty("compilationOutput") String compilationOutput,
            @JsonProperty("testCaseResults") List<TestCaseExecutionResult> testCaseResults,
            @JsonProperty("totalExecutionTimeMs") long totalExecutionTimeMs
    ) {
        this.submissionId = submissionId;
        this.compilationError = compilationError;
        this.compilationOutput = compilationOutput;
        this.testCaseResults = testCaseResults;
        this.totalExecutionTimeMs = totalExecutionTimeMs;
    }

    public Long getSubmissionId() { return submissionId; }
    public boolean isCompilationError() { return compilationError; }
    public String getCompilationOutput() { return compilationOutput; }
    public List<TestCaseExecutionResult> getTestCaseResults() { return testCaseResults; }
    public long getTotalExecutionTimeMs() { return totalExecutionTimeMs; }
}
