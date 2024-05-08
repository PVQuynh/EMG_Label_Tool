package com.iBME.emg_label_tool;

import com.iBME.emg_label_tool.repository.LabelRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@OpenAPIDefinition(
		info = @Info(
				title = "EMG Segment Tool",
				version = "1.0.0",
				description = "Segment muscle signals for diseases",
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
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(LabelRepository labelRepository) {
//
//		return args -> {
//			System.out.println(labelRepository.findLabelIdAndStart(1).getId());
//			System.out.println(labelRepository.findLabelIdAndStart(1).getStart());
//		};
//	}

}
