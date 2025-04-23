package com.demo.hotel.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTUtils {

    @Value("${jwt.key}")
    private String secret;

    @Value("${jwt.expiry-time}")
    private long expiry; // in milliseconds

    @Value("${jwt.issuer}")
    private String issuer;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name", username)
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String extractUsername(String token) {
        return decodeToken(token).getSubject(); // or .getClaim("name").asString();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        try {
            DecodedJWT jwt = decodeToken(token);
            String username = jwt.getSubject();
            return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(DecodedJWT jwt) {
        return jwt.getExpiresAt().before(new Date());
    }

    private DecodedJWT decodeToken(String token) {
        return verifier.verify(token);
    }
}
