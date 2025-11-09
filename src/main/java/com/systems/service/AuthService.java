package com.systems.service;

import com.systems.dto.LoginRequest;
import com.systems.dto.TokenResponse;

public interface AuthService {

    public TokenResponse login(LoginRequest loginRequest);

    public TokenResponse refreshToken(final String authHeader);
    
    public String getResultadosPorPeriodo(String periodoId);
    
}
