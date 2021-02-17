package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algaworks.algafood.di.notificacao.NivelUrgencia.SEM_URGENCIA;

@TipoDoNotificador(SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    private final NotificadorProperties properties;

    @Autowired
    public NotificadorEmail(NotificadorProperties properties) {
        this.properties = properties;
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + properties.getHostServidor());
        System.out.println("Porta: " + properties.getPortaServidor());

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
