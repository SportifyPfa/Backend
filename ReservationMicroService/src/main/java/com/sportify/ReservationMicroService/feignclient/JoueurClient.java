package com.sportify.ReservationMicroService.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sportify.ReservationMicroService.entity.User;

@FeignClient(name = "USERMICROSERVICE")
public interface JoueurClient {
	 @GetMapping(value = "/user/users/{id}")
	  public User getUserById(@PathVariable("id") int id);
}
