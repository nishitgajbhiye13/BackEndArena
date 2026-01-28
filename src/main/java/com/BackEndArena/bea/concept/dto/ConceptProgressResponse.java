package com.BackEndArena.bea.concept.dto;

import lombok.Getter;
import lombok.Setter;

public class ConceptProgressResponse {

    @Getter
    @Setter
    private Long conceptId;

    @Getter
    @Setter
    private int totalProblems;

    @Getter
    @Setter
    private int attemptedProblems;

    @Getter
    @Setter
    private int progressPercentage;

    @Getter
    @Setter
    private boolean completed;

}
