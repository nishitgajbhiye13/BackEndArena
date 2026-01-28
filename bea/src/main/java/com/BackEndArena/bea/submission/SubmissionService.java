package com.BackEndArena.bea.submission;

import com.BackEndArena.bea.submission.dto.SubmissionResultResponse;
import com.BackEndArena.bea.submission.dto.SubmitCodeRequest;

public interface SubmissionService {

    ProblemSubmission submitCode(Long userId, SubmitCodeRequest request);
    SubmissionResultResponse getSubmissionResult(Long submissionId);

}
