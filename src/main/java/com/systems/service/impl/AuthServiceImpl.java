package com.systems.service.impl;

import com.systems.dto.LoginRequest;
import com.systems.dto.TokenResponse;
import com.systems.dto.UserResponse;
import com.systems.exceptions.CustomException;
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
        
        try{
            // Obtenemos el resultado de la consulta
            List<Object[]> userArray = authRepository.validateLogin(loginRequest.deUsuario(), loginRequest.deClave());
            
            // Aseguramos que hay suficientes datos en el array
            if (userArray.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario/Clave incorrecta");
            }
            
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
        }catch(ResponseStatusException e){
            throw new CustomException("Error al validar el login", e);
        }
                
    }

    @Override
    public TokenResponse refreshToken(final String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new IllegalArgumentException("Refresh Token no puede ser nulo o vacío");
        }

        final String username = jwtService.extractUsername(refreshToken);

        if (username == null) {
            throw new IllegalArgumentException("Refresh Token inválido: sin usuario");
        }

        List<Object[]> userArray = authRepository.validateUser(username);
        if (userArray.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        Object[] user = userArray.get(0);
        UserResponse userResponse = new UserResponse(
            (Integer) user[0], // aio_nu_result
            (String) user[1], // aso_de_result
            (Integer) user[2], // aio_id_usuario
            (String) user[3], // aso_de_alias
            (String) user[4], // aso_de_rol
            (Integer) user[5], // aio_id_habitante
            (String) user[6]  // aso_de_habitante
        );

        if (!jwtService.isTokenValid(refreshToken, userResponse)) {
            throw new IllegalArgumentException("Refresh Token inválido");
        }

        final String newAccessToken = jwtService.generateToken(userResponse);

        return new TokenResponse(200, 1, "Token actualizado correctamente", newAccessToken, refreshToken);
    }

    @Override
    public String getResultadosPorPeriodo(String periodoId) {
        return authRepository.getUpixPeriodo(periodoId);
    }

}
