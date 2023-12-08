package com.example.kiseya.security;

import static com.example.kiseya.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.kiseya.security.SecurityConstants.SECRET;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {
    public static String getToken(String email) {
        String token = JWT.create()
            .withSubject(email)
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.getBytes()));
        
        System.out.println("Generated token for email: " + email);
        
        return token;
    }
}

