package com.systems.security;

import com.systems.dto.UserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUtils {

    @Value("${secreteJwtString}")
    private String secretKey;
    @Value("${expiration-access}")
    private long jwtExpiration;
    @Value("${expiration-refresh}")
    private long refreshExpiration;

    public String generateToken(UserResponse user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(UserResponse user) {
        return buildToken(user, refreshExpiration);
    }
    
    private String buildToken(final UserResponse user,final long expiration){
        return Jwts.builder()
                .id(user.aioIdUsuario().toString())
                .claims(Map.of("deMensaje",user.asoDeResult()))
                .claims(Map.of("deHabitante",user.asoDeHabitante()))
                .claims(Map.of("idHabitante",user.aioIdHabitante().toString()))
                .claims(Map.of("idUsuario",user.aioIdUsuario().toString()))
                .claims(Map.of("deRol",user.asoDeRol()))
                .subject(user.asoDeAlias())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey())
                .compact();
    }
    
    private SecretKey getSignInKey(){
        /*
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
        */
        byte[] keyByte = secretKey.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyByte, "HmacSHA256");
    }

    public String extractUsername(final String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getSubject();
    }

    public boolean isTokenValid(final String token,final UserResponse userResponse) {
        final String username = extractUsername(token);
        return (username.equals(userResponse.asoDeAlias())) && !isTokenExpired(token);        
    }
    public boolean isTokenValid(final String token,UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);        
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getExpiration();
    }

}
