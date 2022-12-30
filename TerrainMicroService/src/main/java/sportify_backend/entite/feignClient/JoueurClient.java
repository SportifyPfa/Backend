package sportify_backend.entite.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import sportify_backend.entite.terrain.AppUser;

import java.security.Principal;

@FeignClient(name = "SPORTIFYAUTHENTIFICATION")
public interface JoueurClient {
	@GetMapping("/load_by_username/{username}")
    public AppUser loadByUsername(@PathVariable(name = "username") String username );
    @GetMapping("/auth/user_auth")
    public AppUser profile(@RequestHeader("Authorization") String bearerToken);
}
