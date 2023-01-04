package com.sportify.ReservationMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories
public class ReservationMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationMicroServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/order/**").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("SPORTIFYENTITY/terrain/**").allowedOrigins("*").allowedMethods("*");
				
				
			}
		};
	}
}
