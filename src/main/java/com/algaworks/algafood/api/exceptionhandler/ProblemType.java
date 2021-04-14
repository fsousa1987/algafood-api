package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada");

    private final String uri;
    private final String title;

    ProblemType(String title, String path) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
