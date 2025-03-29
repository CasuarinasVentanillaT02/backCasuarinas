package com.systems.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String de_habitante;
    
    @NotBlank(message = "Alias es requerido")
    private String de_alias;
    
    @NotBlank(message = "Clave es requerido")
    private String de_clave;
    
    private String de_rol;
    
    private String st_activo;
    
    private String de_usuario_reg;
    
    private LocalDateTime fe_reg;
    
    private String de_usuario_upd;
    
    private LocalDateTime fe_upd;
    
    private Integer id_usuario;

    private Integer id_habitante;

    private Integer id_rol;
    
    
    
    
}
