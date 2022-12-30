package com.sportify.ReservationMicroService.feignclient;

import com.sportify.ReservationMicroService.entity.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.Principal;

@FeignClient(name = "SPORTIFYAUTHENTIFICATION")
public interface JoueurClient {
    @GetMapping("/auth/user_auth")
    public AppUser profile(@RequestHeader("Authorization") String bearerToken);
}
