package com.BackEndArena.execution_contract.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCaseExecutionResult {

    private final String output;
    private final long executionTimeMs;
    private final Boolean runtimeError;
    private final int exitCode;

    @JsonCreator
    public TestCaseExecutionResult(
            @JsonProperty("output") String output,
            @JsonProperty("executionTimeMs") long executionTimeMs,
            @JsonProperty("runtimeError") boolean runtimeError,
            @JsonProperty("exitCode") int exitCode
    ) {
        this.output = output;
        this.executionTimeMs = executionTimeMs;
        this.runtimeError = runtimeError;
        this.exitCode = exitCode;
    }

    public String getOutput() { return output; }
    public long getExecutionTimeMs() { return executionTimeMs; }
    public Boolean isRuntimeError() { return runtimeError; }
    public int getExitCode() { return exitCode; }
}
