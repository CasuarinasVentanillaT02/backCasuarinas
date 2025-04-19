package com.systems.repository;

import com.systems.dto.HabitantesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitanteRepository extends JpaRepository<HabitantesEntity,Long> {
    List<HabitantesEntity> findByDeApellidos(String deApellidos);
    List<HabitantesEntity> findByNuDocumento(String nuDocumento);
}

