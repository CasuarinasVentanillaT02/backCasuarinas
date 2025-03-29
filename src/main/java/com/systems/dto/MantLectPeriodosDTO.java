package com.systems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MantLectPeriodosDTO {
    private Integer id_periodo;
    private String de_periodo;
}
