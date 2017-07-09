package com.dexcloudapp.restful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@ComponentScan("com.dexcloudapp.restful")
public class SwaggerConfig {                                    
    @Bean
    
    /*
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }*/
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dexcloudapp.restful"))
                .paths(PathSelectors.any())
                .build();
    }
/*
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "SwapTest",
                "An application to test SWAP",
                "SWAP v1",
                "Terms of service",
                "abcd@sdfsd.com",
                "License of sdfasd",
                "License URL");
        return apiInfo;
    }*/
}
