package com.BackEndArena.bea.attempt;

import com.BackEndArena.bea.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/problems")
public class UserProblemAttemptController {

    private final ProblemAttemptService problemAttemptService;

    public UserProblemAttemptController(
            ProblemAttemptService problemAttemptService
    ) {
        this.problemAttemptService = problemAttemptService;
    }

    /**
     * USER: Start an attempt for a problem
     * Triggered only when learner explicitly clicks "Start Attempt"
     */
    @PostMapping("/{problemId}/attempts/start")
    public ResponseEntity<Void> startAttempt(
            @PathVariable Long problemId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        problemAttemptService.startAttempt(
                problemId,
                userDetails.getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
