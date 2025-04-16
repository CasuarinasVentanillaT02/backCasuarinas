package com.systems.service.impl;

import com.systems.dto.LoginRequest;
import com.systems.dto.TokenResponse;
import com.systems.dto.UserResponse;
import com.systems.repository.AuthRepository;
import com.systems.security.JwtUtils;
import com.systems.service.AuthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;
    private final JwtUtils jwtService;

    @Autowired
    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        // Obtenemos el resultado de la consulta
        List<Object[]> userArray = authRepository.validateLogin(loginRequest.deUsuario(), loginRequest.deClave());

        // Aseguramos que hay suficientes datos en el array
        Object[] user = userArray.get(0);

        // Convertimos Object[] en UserResponse de manera segura
        UserResponse userResponse = new UserResponse(
                (Integer) user[0], // aio_nu_result
                (String) user[1], // aso_de_result
                (Integer) user[2], // aio_id_usuario
                (String) user[3], // aso_de_alias
                (String) user[4], // aso_de_rol
                (Integer) user[5], // aio_id_habitante
                (String) user[6] // aso_de_habitante
        );

        var jwtToken = "";
        var refreshToken = "";

        if (userResponse.aioNuResult() == 1) {
            jwtToken = jwtService.generateToken(userResponse);
            refreshToken = jwtService.generateRefreshToken(userResponse);
        }//else{
            
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, userResponse.asoDeResult());
        //}

        return new TokenResponse(200,userResponse.aioNuResult(),userResponse.asoDeResult(),jwtToken, refreshToken);
    }

    @Override
    public TokenResponse refreshToken(final String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new IllegalArgumentException("Invalido token Bearer");
        }
        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);
        
        if(username == null){
            throw new IllegalArgumentException("Invalido Refresh Token 1");
        }
        
        List<Object[]> userArray = authRepository.validateUser(username);
                //.orElseThrow(()->UsernameNotFoundException(username));
        Object[] user = userArray.get(0);
        UserResponse userResponse = new UserResponse(
                (Integer) user[0], // aio_nu_result
                (String) user[1], // aso_de_result
                (Integer) user[2], // aio_id_usuario
                (String) user[3], // aso_de_alias
                (String) user[4], // aso_de_rol
                (Integer) user[5], // aio_id_habitante
                (String) user[6] // aso_de_habitante
        );
        
        if(!jwtService.isTokenValid(refreshToken,userResponse)){
            throw new IllegalArgumentException("Invalido Refresh Token 2");
        }
        
        final String accessToken = jwtService.generateToken(userResponse);
        
        return new TokenResponse(200,1,"Token Refresh", accessToken,accessToken);
    }

}
