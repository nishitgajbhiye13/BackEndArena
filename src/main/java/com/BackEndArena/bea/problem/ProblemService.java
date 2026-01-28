package com.BackEndArena.bea.problem;

import com.BackEndArena.bea.problem.dto.CreateProblemRequest;
import com.BackEndArena.bea.problem.dto.ProblemResponseDto;

import java.util.List;

public interface ProblemService {


    Problem createProblem(CreateProblemRequest request);

    List<ProblemResponseDto> getPublishedProblemsByConcept(Long conceptId);

}
