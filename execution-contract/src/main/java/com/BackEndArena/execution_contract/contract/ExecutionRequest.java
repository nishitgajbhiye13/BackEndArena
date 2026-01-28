package com.BackEndArena.execution_contract.contract;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExecutionRequest {

    private final Long submissionId;
    private final String sourceCode;
    private final String language;
    private final List<TestCase> testCases;
    private final ExecutionLimits executionLimits;

    @JsonCreator
    public ExecutionRequest(
            @JsonProperty("submissionId") Long submissionId,
            @JsonProperty("sourceCode") String sourceCode,
            @JsonProperty("language") String language,
            @JsonProperty("testCases") List<TestCase> testCases,
            @JsonProperty("executionLimits") ExecutionLimits executionLimits
    ) {
        this.submissionId = submissionId;
        this.sourceCode = sourceCode;
        this.language = language;
        this.testCases = testCases;
        this.executionLimits = executionLimits;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getLanguage() {
        return language;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public ExecutionLimits getExecutionLimits() {
        return executionLimits;
    }
}



