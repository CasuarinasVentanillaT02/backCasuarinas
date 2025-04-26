package com.systems.repository;

import com.systems.dto.HabitantesEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitanteRepository extends JpaRepository<HabitantesEntity,Long> {
    Page<HabitantesEntity> findByNuDocumento(String nuDocumento, Pageable pageable);
    @Query(value = "SELECT * FROM mantenimiento.tb_habitantes h WHERE UPPER(CONCAT(h.de_apellidos, h.de_nombres)) LIKE CONCAT('%', UPPER(:parametro), '%')", nativeQuery = true)
    Page<HabitantesEntity> findByApellidosAndNombresCombinedNative(@Param("parametro") String parametro, Pageable pageable);
    @Query(value = "SELECT * FROM mantenimiento.tb_tipo_docu ORDER BY nu_orden", nativeQuery = true)
    List<Object[]>listTipoDocu();
    @Query(value = "SELECT * FROM mantenimiento.tb_habitante_tratamiento ORDER BY nu_orden", nativeQuery = true)
    List<Object[]>listHabiTratamiento();
    
    Page<HabitantesEntity> findByIdHabitanteNot(Long idHabitante, Pageable pageable);
    
    @Query(value = "select * from mantenimiento.f_val_tipo_num_docu(:idHabitante,:idTipoDocu,:NuDocumeto)", nativeQuery = true)
    List<Object[]>getValTipoNumdocu(@Param("idHabitante") Long idHabitante,@Param("idTipoDocu") Integer idTipoDocu,@Param("NuDocumeto") String NuDocumeto);
        
}

