package com.example.post;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo());
		
	}
	
	private ApiInfo apiInfo() {
		
		Contact contact = new Contact("Natalia Trybulova","nataliatrybulova.com", "n.trybulova@gmail.com");
	    
		ApiInfo apiinfo = new ApiInfo( "My REST API", 
	      "Management of users' posts", 
	      "1v", 
	      "Free to use", contact, "License of API", "http://postexample.com", Collections.emptyList());

	    		return apiinfo;
	}

	
}
