package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private final Integer status;
    private final String type;
    private final String title;
    private final String detail;

    private final String userMessage;
    private LocalDateTime timestamp;
}
