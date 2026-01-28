package com.BackEndArena.bea.submission.dto;

import com.BackEndArena.bea.submission.SubmissionStatus;
import com.BackEndArena.bea.submission.Verdict;

public class SubmissionResultResponse {
    private Long submissionId;
    private SubmissionStatus status;
    private Verdict verdict;
    private Long executionTimeMs;

    public SubmissionResultResponse(
            Long submissionId,
            SubmissionStatus status,
            Verdict verdict,
            Long executionTimeMs
    ) {
        this.submissionId = submissionId;
        this.status = status;
        this.verdict = verdict;
        this.executionTimeMs = executionTimeMs;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public Long getExecutionTimeMs() {
        return executionTimeMs;
    }
}
