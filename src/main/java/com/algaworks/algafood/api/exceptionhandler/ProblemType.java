package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio");

    private final String uri;
    private final String title;

    ProblemType(String title, String path) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
