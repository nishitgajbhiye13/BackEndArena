package com.BackEndArena.bea.submission;


import com.BackEndArena.bea.security.CustomUserDetails;
import com.BackEndArena.bea.submission.dto.SubmissionResultResponse;
import com.BackEndArena.bea.submission.dto.SubmitCodeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/submissions")
public class UserSubmissionController {

    private final SubmissionService submissionService;

    public UserSubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Void> submitCode(
            @RequestBody SubmitCodeRequest request,
            Authentication authentication
    ) {
        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        submissionService.submitCode(user.getId(), request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResultResponse> getSubmissionResult(
            @PathVariable Long id
    ) {
        SubmissionResultResponse response =
                submissionService.getSubmissionResult(id);

        return ResponseEntity.ok(response);
    }

}
