package com.systems.repository;

import com.systems.dto.HabitantesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitanteRepository extends JpaRepository<HabitantesEntity,Long> {
    Page<HabitantesEntity> findByNuDocumento(String nuDocumento, Pageable pageable);
    @Query(value = "SELECT * FROM mantenimiento.tb_habitantes h WHERE CONCAT(h.de_apellidos, h.de_nombres) LIKE %:parametro%", nativeQuery = true)
    Page<HabitantesEntity> findByApellidosAndNombresCombinedNative(String parametro, Pageable pageable);
}

