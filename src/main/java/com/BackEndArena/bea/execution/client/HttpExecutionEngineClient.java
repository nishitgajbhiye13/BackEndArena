package com.BackEndArena.bea.execution.client;



import com.BackEndArena.execution_contract.contract.ExecutionRequest;
import com.BackEndArena.execution_contract.contract.ExecutionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Primary
public class HttpExecutionEngineClient
        implements ExecutionEngineClient {

    private final RestTemplate restTemplate ;

    @Value("http://localhost:8090/execute")
    private String engineUrl;

    public HttpExecutionEngineClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ExecutionResult execute(ExecutionRequest request) {

        System.out.println("===== BACKEND → ENGINE CALL =====");
        System.out.println("Engine URL: " + engineUrl);
        System.out.println("Submission ID: " + request.getSubmissionId());
        System.out.println("Language: " + request.getLanguage());
        System.out.println("Test cases: " + request.getTestCases().size());

        ExecutionResult result =
                restTemplate.postForObject(
                        engineUrl,
                        request,
                        ExecutionResult.class
                );

        System.out.println("===== BACKEND ← ENGINE RESPONSE =====");
        System.out.println("Total execution time: " + result.getTotalExecutionTimeMs());
        System.out.println("Test case results: " + result.getTestCaseResults().size());

        return result;
    }
}

