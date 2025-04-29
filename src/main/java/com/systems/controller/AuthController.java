package com.systems.controller;

import com.systems.dto.LoginRequest;
import com.systems.dto.TokenResponse;
import com.systems.service.AuthService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody final LoginRequest loginRequest){
        final TokenResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
    
    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        return authService.refreshToken(authHeader);
    }
    
    @GetMapping("/holaJava")
    public String holaJava(){
        return "Hola Java: "+LocalDateTime.now().toString();
    }
}