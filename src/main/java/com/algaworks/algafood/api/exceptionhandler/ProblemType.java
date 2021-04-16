package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema"),
    PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio");

    private final String uri;
    private final String title;

    ProblemType(String title, String path) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
