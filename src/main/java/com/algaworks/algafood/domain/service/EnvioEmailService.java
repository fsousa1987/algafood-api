package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {

        @Singular
        private final Set<String> destinatarios;

        @NonNull
        private final String assunto;

        @NonNull
        private final String corpo;
    }
}
