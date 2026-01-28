package com.BackEndArena.bea.submission.dto;

import lombok.Getter;
import lombok.Setter;

public class SubmitCodeRequest {

    @Getter
    @Setter
    private Long attemptId;
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    private String code;
}
