//package com.revature.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.*;
//import java.util.function.Function;
//import java.nio.charset.StandardCharsets;
//
///*
// * JWTUtils is a utility class for generating, validating, and extracting information from JWT tokens.
// * It provides methods to create tokens, extract claims, and check token validity.
// */
//
//
//@Component
//public class JWTUtils {
//    private SecretKey Key;
//    private static final long EXPIRATION_TIME = 86400000; //24 hours
//
//    public JWTUtils(){
//        String secretString = "SECRETSTRINGFOROURPROJECT";
//        byte[] ketBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
//        this.Key = new SecretKeySpec(ketBytes, "HmacSHA256");
//
//    }
//
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .subject(userDetails.getUsername())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(Key)
//                .compact();
//    }
//
//    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
//        return Jwts.builder()
//                .claims(claims)
//                .subject(userDetails.getUsername())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(Key)
//                .compact();
//    }
//
//
//
//    public String extractUsername(String token) {
//        return extractClaims(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
//        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
//    }
//
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public boolean isTokenExpired(String token){
//        return extractClaims(token, Claims::getExpiration).before(new Date());
//    }
//
//}
