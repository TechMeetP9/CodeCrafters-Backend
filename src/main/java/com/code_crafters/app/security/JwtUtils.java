package com.code_crafters.app.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {
        UsersDetailsImpl userPrincipal = (UsersDetailsImpl) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("userId", userPrincipal.getId()) 
                .claim("email", userPrincipal.getEmail()) 
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    public Long getUserIdFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        Object userId = claims.get("userId");
        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        }
        return (Long) userId;
    }


    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid Token JWT {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired Token JWT: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported Token JWT: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            logger.error("Invalid signature JWT: {}", e.getMessage());
        }
        return false;
    }

    public Date getExpirationDateFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromJwtToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}