package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

    private Long id;

    /*
    Origem: cozinha,nome
    Destino: cozinha,cozinha,nome
     */
    private String cozinhaNome;
}
