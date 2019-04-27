package com.yx.auth.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: JST
 * @Date: 2019/4/26 11:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yx.auth.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 创建api的基本信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("自动生成的哟!")
//                .termsOfServiceUrl("http://127.0.0.1:"+port)
                .termsOfServiceUrl("http://127.0.0.1:10010")
                .version("1.0")
                .build();
    }
}
