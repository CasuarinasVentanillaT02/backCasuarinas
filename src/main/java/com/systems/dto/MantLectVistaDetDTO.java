package com.systems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MantLectVistaDetDTO {
    private String deDepartamento;
    private Double nuLecturaAnterior;
    private Double nuLecturaActual;
    private Integer idMantenimientoDet;
}
