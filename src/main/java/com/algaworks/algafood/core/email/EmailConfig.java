package com.algaworks.algafood.core.email;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SandboxEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    private final EmailProperties emailProperties;

    @Autowired
    public EmailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public EnvioEmailService envioEmailService() {
        return switch (emailProperties.getImpl()) {
            case FAKE -> new FakeEnvioEmailService();
            case SMTP -> new SmtpEnvioEmailService();
            case SANDBOX -> new SandboxEnvioEmailService();
        };
    }
}
