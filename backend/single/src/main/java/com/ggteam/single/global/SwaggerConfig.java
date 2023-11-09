package com.ggteam.single.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	private static final String SECURITY_SCHEME_NAME = "authorization";
	@Bean
	public OpenAPI swaggerApi() {
		return new OpenAPI()
			.components(new Components()
				// 여기부터 추가 부분
				.addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
					.name(SECURITY_SCHEME_NAME)
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")))
			.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
			// 여기까지
			.info(new Info()
				.title("Happy Programmer API 사이트")
				.description("Gumi Games")
				.version("1.0.0"));
	}
}
