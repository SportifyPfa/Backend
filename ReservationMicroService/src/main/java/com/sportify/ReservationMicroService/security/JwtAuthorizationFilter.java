package com.sportify.ReservationMicroService.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO
        if(request.getServletPath().equals("/auth/refreshToken")){
            filterChain.doFilter(request,response);
        }else {
            String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIX)) {
                try {
                    String jwt = authorizationToken.substring(JWTUtil.PREFIX.length());
                    Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    //PAYLOAD:DATA
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    for (String r : roles) {
                        authorities.add(new SimpleGrantedAuthority(r));
                    }

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    //Authentifier l'util
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    //Pour passer au filtre suivant
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setHeader("error-message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}