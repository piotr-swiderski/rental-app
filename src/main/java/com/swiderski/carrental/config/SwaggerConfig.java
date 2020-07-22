package com.swiderski.carrental.config;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;
    @Value("${config.oauth2.client.id}")
    private String clientId;
    @Value("${config.oauth2.client.secret}")
    private String clientSecret;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths((Predicate.not(PathSelectors.regex("/error.*"))))
                .paths(Predicate.not(PathSelectors.regex("/actuator.*")))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey(), securitySchema()))
                .apiInfo(apiInfo());
    }


    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header");
    }


    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = newArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .build();
    }


    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");
        return Arrays.asList(new SecurityReference("oauth2", authorizationScopes), new SecurityReference("JWT", authorizationScopes));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Rental api").description("")
                .termsOfServiceUrl("https://localhost:9090/v1")
                .license("Open Source")
                .version("1.0.0")
                .build();
    }
}
