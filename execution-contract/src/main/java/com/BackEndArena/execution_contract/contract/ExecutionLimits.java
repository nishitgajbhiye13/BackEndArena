package com.BackEndArena.execution_contract.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionLimits {

    private final long timeLimitMs;
    private final long memoryLimitMb;

    @JsonCreator
    public ExecutionLimits(
            @JsonProperty("timeLimitMs") long timeLimitMs,
            @JsonProperty("memoryLimitMb") long memoryLimitMb
    ) {
        this.timeLimitMs = timeLimitMs;
        this.memoryLimitMb = memoryLimitMb;
    }

    public long getTimeLimitMs() { return timeLimitMs; }
    public long getMemoryLimitMb() { return memoryLimitMb; }
}

