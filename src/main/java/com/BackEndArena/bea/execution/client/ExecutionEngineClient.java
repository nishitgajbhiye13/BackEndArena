package com.BackEndArena.bea.execution.client;

import com.BackEndArena.execution_contract.contract.ExecutionRequest;
import com.BackEndArena.execution_contract.contract.ExecutionResult;

public interface ExecutionEngineClient {

    ExecutionResult execute(ExecutionRequest request);
}
