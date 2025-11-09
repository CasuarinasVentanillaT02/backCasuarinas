package com.systems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoUPRequest {
    @NotBlank(message = "Periodo es requerido")
    private String periodo;
    @NotBlank(message = "Usuario es requerido")
    private String usuario;
    
    @NotBlank(message = "Clave es requerido")
    private String clave;
}
