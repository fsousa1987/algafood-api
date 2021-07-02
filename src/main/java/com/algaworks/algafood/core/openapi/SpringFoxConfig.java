package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PedidosResumoModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                    .paths(PathSelectors.any())
                    .build()
                .globalResponses(HttpMethod.GET, globalGetResponseMessage())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class),
                        CozinhasModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, PedidoResumoModel.class),
                        PedidosResumoModelOpenApi.class))
                .apiInfo(apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Grupos", "Gerencia os grupos de usuários"),
                        new Tag("Cozinhas", "Gerencia as cozinhas"),
                        new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                        new Tag("Pedidos", "Gerencia os pedidos"),
                        new Tag("Restaurantes", "Gerencia os restaurantes"),
                        new Tag("Estados", "Gerencia os estados"),
                        new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                        new Tag("Usuários", "Gerencia os usuários"),
                        new Tag("Estatísticas", "Estatísticas da AlgaFood"));
    }

    private List<Response> globalGetResponseMessage() {
        return Arrays.asList(new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                    .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(builderModelProblema())
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AlgaFood")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact("Francisco Sousa", "https://github.com/fsousa1987",
                        "franciscoeds1987@gmail.com"))
                .build();
    }

    private Consumer<RepresentationBuilder> builderModelProblema() {
        return r -> r.model(m -> m.name("Problema").referenceModel(ref -> ref.key(
                k -> k.qualifiedModelName(
                        q -> q.name("Problema")
                                .namespace("com.algaworks.algafood.api.exceptionhandler")
                ))));
    }
}

// http://api.algafood.local:8080/swagger-ui/