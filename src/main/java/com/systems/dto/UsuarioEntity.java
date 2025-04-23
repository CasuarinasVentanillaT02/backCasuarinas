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
@Table(name = "tb_usuarios", schema = "mantenimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "id_habitante")
    private Long idHabitante;
    
    @Column(name = "id_rol")
    private Integer idRol;
    
    @Column(name = "de_alias", nullable = false, length = 50) // Ajusta la longitud según sea necesario
    private String deAlias;
    
    @Column(name = "de_clave", nullable = false, length = 500) // Ajusta la longitud según sea necesario
    private String deClave;
    
    @Column(name = "st_activo", nullable = false, length = 1) // Ajusta la longitud según sea necesario
    private String stActivo;
    
    @Column(name = "fe_baja")
    private LocalDateTime feBaja;
    
    @Column(name = "fe_reg", nullable = false)
    private LocalDateTime feReg;
    
    @Column(name = "id_usuario_reg", nullable = false)
    private Integer idUsuarioReg;
    
    @Column(name = "fe_upd")
    private LocalDateTime feUpd;
    
    @Column(name = "id_usuario_upd")
    private Integer idUsuarioUpd;
        
}
