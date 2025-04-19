package com.systems.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_habitantes", schema = "mantenimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitantesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitante")
    private Long idHabitante;

    @Column(name = "id_tipo_docu", nullable = false)
    private Integer idTipoDocu;

    @Column(name = "nu_documento", nullable = false, length = 20) // Ajusta la longitud según sea necesario
    private String nuDocumento;

    @Column(name = "de_apellidos", nullable = false, length = 50) // Ajusta la longitud según sea necesario
    private String deApellidos;

    @Column(name = "de_nombres", nullable = false, length = 50) // Ajusta la longitud según sea necesario
    private String deNombres;

    @Column(name = "id_tratamiento")
    private Integer idTratamiento;

    @Column(name = "st_activo", length = 1) // Ajusta la longitud según sea necesario
    private String stActivo;

    @Column(name = "fe_reg", nullable = false)
    private LocalDateTime feReg;

    @Column(name = "id_usuario_reg", nullable = false)
    private Integer idUsuarioReg;

    @Column(name = "fe_upd")
    private LocalDateTime feUpd;

    @Column(name = "id_usuario_upd")
    private Integer idUsuarioUpd;
}
