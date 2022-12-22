package sportify_backend.entite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories
@SpringBootApplication
@EnableDiscoveryClient
public class SportifyEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportifyEntityApplication.class, args);
	}
/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/terrain/**").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}*/
}
