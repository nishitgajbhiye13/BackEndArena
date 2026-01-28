package com.BackEndArena.execution_contract.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCase {

    private final String input;
    private final String expectedOutput;
    private final boolean hidden;

    @JsonCreator
    public TestCase(
            @JsonProperty("input") String input,
            @JsonProperty("expectedOutput") String expectedOutput,
            @JsonProperty("hidden") boolean hidden
    ) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.hidden = hidden;
    }

    public String getInput() { return input; }
    public String getExpectedOutput() { return expectedOutput; }
    public boolean isHidden() { return hidden; }
}

