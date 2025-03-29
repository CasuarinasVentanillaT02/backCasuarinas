package com.systems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("aio_nu_result")
        Integer aioNuResult,
        @JsonProperty("aso_de_result")	
        String asoDeResult,
        @JsonProperty("aio_id_usuario")
        Integer aioIdUsuario,
        @JsonProperty("aso_de_alias")
        String asoDeAlias,
	@JsonProperty("aso_de_rol")
	String asoDeRol,
        @JsonProperty("aio_id_habitante")
        Integer aioIdHabitante,
	@JsonProperty("aso_de_habitante")
	String asoDeHabitante
) {    
}
