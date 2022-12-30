package com.sportify.ReservationMicroService.security;

public class JWTUtil {
    public static final String SECRET="api-recruitment-db";
    public static final String AUTH_HEADER="Authorization";
    public static final String PREFIX="Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN=300000;
    public static final long EXPIRE_REFRESH_TOKEN=525600*60*1000 ;



}
