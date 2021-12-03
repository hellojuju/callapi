package com.apitest.callapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"local", "dev", "alpha", "real"})
public class ApiDocumentConfiguration {

  @Bean
  public Docket api() {

    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Call api server")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.apitest.callapi"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(
            new ApiInfoBuilder()
                .version("1.0")
                .title("CALL API For ApiTest")
                .description("Documentation CALL API v1.0")
                .license("hellojuju")
                .build()
        );
  }
}
