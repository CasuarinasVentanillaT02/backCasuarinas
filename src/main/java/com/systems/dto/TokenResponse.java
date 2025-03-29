package com.systems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        Integer status,
        Integer codigo,
        @JsonProperty("mensaje")
        String mensaje,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {    
}
