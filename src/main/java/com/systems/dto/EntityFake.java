package com.systems.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
public class EntityFake {
    @Id
    private Integer idUsuario;
    
    private Integer status;
    
    private Integer codigo;
    
    private String mensaje;
}
