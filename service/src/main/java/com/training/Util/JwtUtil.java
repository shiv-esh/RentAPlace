package com.training.Util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
	

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenValidity}")
    private Long tokenValidity;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        final long currTimeInMili = System.currentTimeMillis();
        final long expirationTimeInMili = currTimeInMili + tokenValidity;
        Date expiryDate = new Date(expirationTimeInMili);
        return Jwts.builder().setClaims(claims).setIssuer("GreatLearning").setExpiration(expiryDate)
                .setAudience("Java Developers").signWith(SignatureAlgorithm.HS512, secret).compact();


    }

    public String getUserName(final String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

