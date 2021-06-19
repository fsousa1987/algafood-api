package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private final Integer status;

    @ApiModelProperty(example = "2021-06-19T16:47:12.419618Z")
    private final OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos")
    private final String type;

    @ApiModelProperty(example = "Dados inválidos")
    private final String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private final String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private final String userMessage;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)")
    private final List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "preço")
        private final String name;

        @ApiModelProperty(example = "O preço é obrigatório")
        private final String userMessage;
    }
}
