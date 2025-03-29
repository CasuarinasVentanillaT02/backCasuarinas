package com.systems.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MantLectVistaCabDTO {
    private Integer idMantenimientoCab;
    private String dePeriodo;
    private String deEstado;
    private LocalDate feLecturaActual;
    private Double imServicioAgua;
    private Double nuM3Recibo;
    private Double imMantenimiento;
    private Double imMinAgua;
}
