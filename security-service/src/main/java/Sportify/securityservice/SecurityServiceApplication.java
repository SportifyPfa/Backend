package Sportify.securityservice;

import Sportify.securityservice.entities.AppRole;
import Sportify.securityservice.entities.AppUser;
import Sportify.securityservice.service.AccountService;
import Sportify.securityservice.service.AccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@EnableDiscoveryClient
@EnableJpaRepositories
@SpringBootApplication
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start (AccountServiceImpl accountService){
		return args ->{
				accountService.addNewUser(new AppUser( "yassine", "123","15",new ArrayList<>()));
				accountService.addNewRole(new AppRole( "joueur"));
				accountService.addNewRole(new AppRole( "entite"));
				accountService.addRoleToUser("yassine", "joueur");
		};
	}

}
