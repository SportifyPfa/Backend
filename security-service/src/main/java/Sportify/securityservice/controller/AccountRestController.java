package Sportify.securityservice.controller;

import Sportify.securityservice.JWTUtil;
import Sportify.securityservice.entities.AppRole;
import Sportify.securityservice.entities.AppUser;
import Sportify.securityservice.entities.RoleUserForm;
import Sportify.securityservice.service.AccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AccountRestController {

    private AccountService accountService;


    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/all_users")
    public List<AppUser> appUsers(){
        return accountService.lisUsers();
    }


    @GetMapping("/all_roles")
    public List<AppRole> appRoles(){
        return accountService.lisRoles();
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        accountService.deleteUser(id);
    }


    @PostMapping("/save_user")
    public AppUser saveUser(@RequestBody AppUser appUser) throws Exception {
        return accountService.addNewUser(appUser);
    }


    @PostMapping("/save_role")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }


    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
         accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

    //Recuperer l'utilisateur authentifié
    @GetMapping("/user_auth")
    public AppUser profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }

    //Recuperer l'utilisateur by username
    @GetMapping("/load_by_username/{username}")
    public AppUser loadByUsername(@PathVariable(name = "username") String username ){
        return accountService.loadUserByUsername(username);
    }



    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken=request.getHeader("refresh_token");
        if(authToken !=null && authToken.startsWith(JWTUtil.PREFIX)){
            try {
                String jwt=authToken.substring(JWTUtil.PREFIX.length());
                Algorithm algorithm=Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
                //PAYLOAD:DATA
                DecodedJWT decodedJWT=jwtVerifier.verify(jwt);
                String username=decodedJWT.getSubject();
                AppUser appUser=accountService.loadUserByUsername(username);
                String jwtAccessToken= JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",appUser.getAppRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken=new HashMap<>();
                idToken.put("access_token",jwtAccessToken);
                idToken.put("refresh_token",jwt);
                //sérialisation
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
                response.setHeader("error-message",e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        else {
            throw new RuntimeException("Refresh Token Required");
        }
    }


}
