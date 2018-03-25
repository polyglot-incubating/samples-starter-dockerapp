package org.chiwooplatform.samples.dockerapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                // .apis(RequestHandlerSelectors.any()) // show all service, including spring-boot default.
                .apis(RequestHandlerSelectors.basePackage("org.chiwooplatform.samples.dockerapp.api")) // show specific class path only                 
                .paths(PathSelectors.any()).build().apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        final ApiInfo info = new ApiInfo("Spring 5 RESTful Project Example", // title
                "Documentation of Project REST API", // sub-title
                "1.0", // api version
                "Terms of service..bla..bla", // term of service
                new Contact("Author", "http://www.author.com/about/", "author@gmail.com"), // author
                "License of API is MIT", // License Type
                "https://github.com/author/spring5-rest-api/LICENSE");
        return info;
    }
}