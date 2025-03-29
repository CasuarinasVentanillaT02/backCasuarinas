package com.systems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(
    @JsonProperty("usuario")    
    String deUsuario,
    @JsonProperty("password") 
    String deClave
) {
    
}
