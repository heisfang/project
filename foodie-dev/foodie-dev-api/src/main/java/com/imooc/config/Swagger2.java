package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)      //制定api类型为swagger2
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.imooc.controller"))   //制定controller的包
                            .paths(PathSelectors.any())             //所有controller
                            .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口API")
                .contact(new Contact("imooc",
                        "https://www.imooc.com",
                        "fannyhe@163.com"))
                .description("天天吃货api文档")
                .version("1.0.1")
                .termsOfServiceUrl("https://www.imooc.com")
                .build();

    }
}
