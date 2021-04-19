package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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
    private final LocalDateTime timestamp;
    private final List<Field> fields;

    @Getter
    @Builder
    public static class Field {

        private final String name;
        private final String userMessage;
    }
}
