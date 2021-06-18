package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                    .paths(PathSelectors.any())
                    .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"));

    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AlgaFood")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact("Francisco Sousa", "https://github.com/fsousa1987",
                        "franciscoeds1987@gmail.com"))
                .build();
    }
}

// http://api.algafood.local:8080/swagger-ui/