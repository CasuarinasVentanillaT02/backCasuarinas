package com.systems.service;

import com.systems.dto.HabitantesEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HabitanteService {
    HabitantesEntity saveHabitante(HabitantesEntity habitantesEntity); // Crear o actualizar un habitante

    Optional<HabitantesEntity> findById(Long id); // Obtener un habitante por ID

    Page<HabitantesEntity> findAll(Pageable pageable); // Listar habitantes con paginación

    void deleteById(Long id); // Eliminar un habitante por ID
}
