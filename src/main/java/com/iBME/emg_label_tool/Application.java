package com.iBME.emg_label_tool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@OpenAPIDefinition(
		info = @Info(
				title = "EMG Label Tool",
				version = "1.0.0",
				description = "Label muscle signals for diseases",
				contact = @Contact(
						name = "Phạm Văn Quỳnh",
						email = "quynh.pv193074@sis.hust.edu.vn"
				)
		),
		security = @SecurityRequirement(
				name = "bearerAuth"
		)
)
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description PVQ",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		;
	}

}
