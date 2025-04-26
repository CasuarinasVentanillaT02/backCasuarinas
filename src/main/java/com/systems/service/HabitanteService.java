package com.systems.service;

import com.systems.dto.HabiTratamientoDTO;
import com.systems.dto.HabitantesEntity;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.TipoDocuDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HabitanteService {
    HabitantesEntity saveHabitante(HabitantesEntity habitantesEntity); // Crear o actualizar un habitante

    Optional<HabitantesEntity> findById(Long id); // Obtener un habitante por ID

    Page<HabitantesEntity> findAll(Pageable pageable); // Listar habitantes con paginación

    void deleteById(Long id); // Eliminar un habitante por ID
    
    Page<HabitantesEntity> findByDeApellidosNombres(String deApellidosdeNombres, Pageable pageable); // Buscar por apellidos con paginación

    Page<HabitantesEntity> findByNuDocumento(String nuDocumento, Pageable pageable); // Buscar por documento con paginación

    List<TipoDocuDTO> listTipoDocu();
    
    List<HabiTratamientoDTO> listHabiTratamiento();
    
    ResultSpDTO getValTipoNumdocu(Long idHabitante,Integer idTipoDocu,String nuDocumento);
}
