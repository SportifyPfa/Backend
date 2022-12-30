package com.sportify.PaiementMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroServiceApplication.class, args);
	}

}
