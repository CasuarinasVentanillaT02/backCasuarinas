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
public class MantLectCabDetSave {
    private Integer idTorre;
    private Integer idPeriodo;
    private LocalDate feLectura;
    private Double imServicioAgua;
    private Double nuM3Recibo;
    private Integer totalRegistros;
    private String cadRegId;
    private String cadRegLect;
    private String cadRegStImg;
    private String cadRegDeImg;
}
