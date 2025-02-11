package com.cdac.campussync.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import com.cdac.campussync.Enum.Role;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Generates the JWT Token for a particular user
    public String generateToken(String username, Role role, Long id) {
        final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode("YBh-3DloRA6e8_o1K3ORPkBFUsTOk3w7fq-UcRL8Kbg-YBh-3DloRA6e8_o1"));;
        JwtBuilder builder = Jwts.builder();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Custom attribute: User Role
        claims.put("userId", id);

        builder.claims(claims);
        builder.subject(username);
        builder.issuedAt(new Date());

        // the JWT token will expire in 1/2 hour
        long EXPIRATION_TIME = 1800000L;
        builder.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        builder.signWith(SECRET_KEY);
        return builder.compact();
    }

    // Validates a JWT token
    public boolean isTokenValid(String token) {
        final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode("YBh-3DloRA6e8_o1K3ORPkBFUsTOk3w7fq-UcRL8Kbg-YBh-3DloRA6e8_o1"));;
        try {
            JwtParserBuilder builder = Jwts.parser();
            builder.verifyWith(SECRET_KEY);

            JwtParser parser = builder.build();

            Jws<Claims> claims = parser.parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String extractUsernameFromToken(String token) {
        final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode("YBh-3DloRA6e8_o1K3ORPkBFUsTOk3w7fq-UcRL8Kbg-YBh-3DloRA6e8_o1"));;
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractRoleFromToken(String token) {
        final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode("YBh-3DloRA6e8_o1K3ORPkBFUsTOk3w7fq-UcRL8Kbg-YBh-3DloRA6e8_o1"));;
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }
}