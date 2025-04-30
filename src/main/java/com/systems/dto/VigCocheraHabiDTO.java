package com.systems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VigCocheraHabiDTO {
    private String de_existe;
    private String de_habitante;
    private String de_depa_habitante;
    private String de_ubic_cochera;
    private String de_placa;
    private String st_propietario;
    private String st_activo;
    private String de_observaciones;
}
