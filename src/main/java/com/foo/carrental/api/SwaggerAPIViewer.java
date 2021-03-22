package com.foo.carrental.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAPIViewer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select()
				.apis(RequestHandlerSelectors.basePackage("com.foo")).build();
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Car Rental Application as REST Service API").description("API reference for developers")
				.version("1.0-1").build();
	}
}
