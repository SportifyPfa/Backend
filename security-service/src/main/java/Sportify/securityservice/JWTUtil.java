package Sportify.securityservice;

public class JWTUtil {
    public static final String SECRET="Api-key-Sportify";
    public static final String AUTH_HEADER="Authorization";
    public static final String PREFIX="Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN=3000000;
    public static final long EXPIRE_REFRESH_TOKEN=525600*60*1000 ;



}
