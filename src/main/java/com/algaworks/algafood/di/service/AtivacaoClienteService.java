package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    private final List<Notificador> notificadores;

    @Autowired
    public AtivacaoClienteService(List<Notificador> notificadores) {
        this.notificadores = notificadores;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        for (Notificador notificador : notificadores) {
           notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        }
    }
}
