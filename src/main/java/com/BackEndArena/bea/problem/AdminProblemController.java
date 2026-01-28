package com.BackEndArena.bea.problem;

import com.BackEndArena.bea.problem.dto.CreateProblemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/problems")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProblemController {
    private final ProblemService problemService;

    public AdminProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    /**
     * ADMIN ONLY
     * Create a new problem under a concept
     */
    @PostMapping

    public ResponseEntity<Problem> createProblem(
            @RequestBody CreateProblemRequest request
    ) {
        Problem createdProblem = problemService.createProblem(request);
        return new ResponseEntity<>(createdProblem, HttpStatus.CREATED);
    }


}
