package com.systems.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tipo_docu", schema = "mantenimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoDocuEntity {
    @Id
    @Column(name = "id_tipo_docu")
    private Integer idTipoDocu;

    @Column(name = "de_tipo_docu", nullable = false, length = 50)
    private String deTipoDocu;
    
    @Column(name = "nu_digitos_val")
    private Integer nuDigitosVal;
    
    @Column(name = "nu_orden")
    private Integer nuOrden;
    
    @Column(name = "co_tipo_doc_ide_cpe", nullable = false, length = 4)
    private String coTipoDocIdeCpe;


}
