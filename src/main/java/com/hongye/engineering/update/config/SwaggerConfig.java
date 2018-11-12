/** 
 * Project Name:enterprise-system 
 * File Name:SwaggerConfig.java 
 * Package Name:org.cloudbuild.enterprise.config 
 * Date:2018年2月9日上午11:08:53 
 * Copyright (c) 2018
 * 
*/

package com.hongye.engineering.update.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket enterpriseApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(enterpriseApiInfo())
                .tags(new Tag("UpdateController", "版本更新"), getTags()).select()
                .apis(RequestHandlerSelectors.basePackage("com.hongye.engineering.update.controller"))
                // 注意修改此处的包名
                .paths(PathSelectors.any())
                .build();
    }

    private Tag[] getTags() {
        Tag[] tags = {
                new Tag("UpdateController", "版本更新")
        };
        return tags;
    }

    private ApiInfo enterpriseApiInfo() {
        return new ApiInfoBuilder()
                .title("更新服务v1.1.0").description("版本跟新接口文档")
                .contact(new Contact("wangjiang", null, "wangjiang@hongye.com.cn"))
                .version("1.1.0")
                .build();
    }
}
