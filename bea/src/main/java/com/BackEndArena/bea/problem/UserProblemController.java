package com.BackEndArena.bea.problem;

import com.BackEndArena.bea.problem.dto.ProblemResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/concepts/{conceptId}/problems")
public class UserProblemController {

    private final ProblemService problemService;

    public UserProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    /**
     * Get all problems for a concept
     * Accessible to authenticated users
     */
    @GetMapping("")
    public ResponseEntity<List<ProblemResponseDto>> getProblemsByConcept(
            @PathVariable Long conceptId
    ) {
        List<ProblemResponseDto> problems = problemService.getPublishedProblemsByConcept(conceptId);
        return ResponseEntity.ok(problems);
    }

}
