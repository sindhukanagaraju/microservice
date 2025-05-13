package com.microservice.showroom;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {
		"com.microservice.showroom",
		"com.microservice.common_service"
})

public class ShowroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowroomApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
