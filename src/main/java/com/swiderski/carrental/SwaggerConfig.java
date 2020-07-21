package com.swiderski.carrental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


import static javax.management.Query.or;
import static springfox.documentation.builders.PathSelectors.regex;

//@Configuration
//@EnableSwagger2WebMvc
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Spring Demo with Swagger")
//                .description("Spring Demo with Swagger")
//                .contact(new Contact("Krzysztof Chrusciel",
//                        "http://codecouple.pl",
//                        "email@here.pl"))
//                .license("License name here")
//                .licenseUrl("URL to license")
//                .version("1.0.1")
//                .build();
//    }

}