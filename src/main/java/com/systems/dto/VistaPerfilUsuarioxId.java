package com.systems.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VistaPerfilUsuarioxId {
    private String de_alias;
    private String de_habitante;
    private String de_rol;
    private String st_activo;
    private LocalDateTime fe_baja;
    private String de_usuario_reg;
    private LocalDateTime fe_reg;
    private String de_usuario_upd;
    private LocalDateTime fe_upd;
}
