package com.BackEndArena.bea.problem.dto;

import lombok.Getter;
import lombok.Setter;

public class ProblemResponseDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String difficulty;
}
