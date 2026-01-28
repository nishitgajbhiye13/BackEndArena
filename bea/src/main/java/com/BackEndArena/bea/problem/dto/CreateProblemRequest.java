package com.BackEndArena.bea.problem.dto;

import com.BackEndArena.bea.problem.ProblemDifficulty;
import lombok.Getter;
import lombok.Setter;

public class CreateProblemRequest {
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String statement;
    @Getter
    @Setter
    private ProblemDifficulty difficulty;
    @Getter
    @Setter
    private boolean industryBased;
    @Getter
    @Setter
    private Long conceptId;
}
