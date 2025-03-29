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
public class VistaUsuarioxId {

    private Integer id_usuario;
    private Integer id_habitante;
    private Integer id_rol;
    private String de_alias;
    private String st_activo;
    private String de_usuario_reg;
    private LocalDateTime fe_reg;
    private String de_usuario_upd;
    private LocalDateTime fe_upd;
}
