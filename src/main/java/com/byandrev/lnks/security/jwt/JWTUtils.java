package com.byandrev.lnks.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    public String generateAccessToken(String username) {
        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(this.timeExpiration)))
            .signWith(this.getSignatureKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts
                .parserBuilder()
                .setSigningKey(this.getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameToken(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = this.getAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
