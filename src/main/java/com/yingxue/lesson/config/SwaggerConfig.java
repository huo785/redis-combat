package com.yingxue.lesson.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SwaggerConfig
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {
        /**
         * 这是为了我们在用 swagger 测试接口的时候添加头部信息
         */
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("sessionId").description("swagger测试用(模拟sessionId传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        /**
         * 多个的时候 就直接添加到 pars 就可以了
         */
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yingxue.lesson.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .enable(enable)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("迎学教育--shiro 实战")
                .description("迎学教育-spring boot 实战系列")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
