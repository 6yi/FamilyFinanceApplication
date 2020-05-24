package com.lzheng.familyfinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * @ClassName SwaggerConfig
 * @Author 6yi
 * @Date 2020/5/23 19:17
 * @Version 1.0
 * @Description:
 */

@EnableSwagger2// 开启Swagger2的自动配置
@Configuration
public class SwaggerConfig {

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lzheng.familyfinance.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfo("Api 文档"
                , "数据库课程设计:家庭理财管理Api文档"
                , "1.0", "urn:tos"
                , DEFAULT_CONTACT
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList());
    }

}
