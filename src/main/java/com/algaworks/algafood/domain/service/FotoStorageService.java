package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    FotoRecuperada recuperar(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID() + "_" + nomeOriginal;
    }

    @Builder
    @Getter
    class NovaFoto {

        private final String nomeArquivo;
        private final String contentType;
        private final InputStream inputStream;
    }

    @Builder
    @Getter
    class FotoRecuperada {

        private final InputStream inputStream;
        private final String url;

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != null;
        }
    }
}
