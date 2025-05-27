package com.systems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systems.dto.LoginRequest;
import com.systems.dto.TokenResponse;
import com.systems.security.CryptoJSDecrypt;
import com.systems.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @Value("${secretLogin}")
    private String secretKeyLogin;

    @PostMapping("/loginv2")
    public ResponseEntity<TokenResponse> autenticarv2(@RequestBody Map<String, String> request) {
        try {

            String encryptedData = request.get("encryptedData");

            String decryptedData = CryptoJSDecrypt.decrypt(encryptedData, secretKeyLogin);

            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(decryptedData, LoginRequest.class);

            final TokenResponse token = authService.login(loginRequest);

            // Retornar solo el accessToken al frontend
            return ResponseEntity.ok(new TokenResponse(
                token.status(),
                token.codigo(),
                token.mensaje(),
                token.accessToken(),
                null // no mandamos el refresh token al body
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new TokenResponse(
                    500,                   // status HTTP
                    -1,                    // código personalizado de error
                    "Error: " + e.getMessage(),  // mensaje de error
                    null,                  // sin access token
                    null                   // sin refresh token
                ));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody final LoginRequest loginRequest,
                                                    HttpServletResponse response) {
        final TokenResponse token = authService.login(loginRequest);

        // Crear cookie con refresh token
        // ✅ Cookie moderna - compatible con frontend moderno (Angular)
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", token.refreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/api/auth/refresh")
                .maxAge(7 * 24 * 60 * 60) // 7 días
                .sameSite("None") // <-- importante si tu frontend está en otro dominio
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());


        // Retornar solo el accessToken al frontend
        return ResponseEntity.ok(new TokenResponse(
            token.status(),
            token.codigo(),
            token.mensaje(),
            token.accessToken(),
            null // no mandamos el refresh token al body
        ));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request) {
        String refreshToken = null;

        // Buscar cookie con nombre "refreshToken"
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.status(401).body(new TokenResponse(401, 0, "Refresh token no encontrado", null, null));
        }

        // Llamamos al servicio pasando el token sin "Bearer "
        final TokenResponse newToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(newToken);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // Eliminar la cookie 'refreshToken' configurándola con fecha de expiración en el pasado
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // la eliminamos
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    
    @GetMapping("/apiKeyLogin")
    public String getApikeyLogin(){
        return secretKeyLogin;
    }
    @GetMapping("/holaJava")
    public String holaJava(){
        return "Hola Java: "+LocalDateTime.now().toString();
    }
}