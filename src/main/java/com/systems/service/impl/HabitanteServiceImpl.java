package com.systems.service.impl;

import com.systems.dto.HabitantesEntity;
import com.systems.dto.TipoDocuEntity;
import com.systems.dto.UsuarioEntity;
import com.systems.repository.HabitanteRepository;
import com.systems.repository.TipoDocuRepository;
import com.systems.service.HabitanteService;
import com.systems.service.UserService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitanteServiceImpl implements HabitanteService {
    
    private HabitanteRepository habitanteRepository;
    private TipoDocuRepository tipoDocuRepository;    
    private UserService userService;
    
    @Autowired
    public HabitanteServiceImpl(
            HabitanteRepository habitanteRepository,
            TipoDocuRepository tipoDocuRepository,
            UserService userService) {
        this.habitanteRepository = habitanteRepository;
        this.tipoDocuRepository = tipoDocuRepository;
        this.userService = userService;
    }
    
    @Override
    public HabitantesEntity saveHabitante(HabitantesEntity habitante) {
        if(habitante.getIdHabitante() == null){
            habitante.setIdUsuarioReg(this.userService.getLoginUser().getId_usuario());
            return habitanteRepository.save(habitante);
        }else{
            HabitantesEntity habitanteUpd = habitanteRepository.findById(habitante.getIdHabitante())
                     .orElseThrow(() -> new RuntimeException("Habitante no encontrado"));
            if (habitante.getDeApellidos() != null) {
                habitanteUpd.setDeApellidos(habitante.getDeApellidos());
            }
            if (habitante.getDeNombres() != null) {
                habitanteUpd.setDeNombres(habitante.getDeNombres());
            }
            if (habitante.getNuDocumento() != null) {
                habitanteUpd.setNuDocumento(habitante.getNuDocumento());
            }
            if (habitante.getStActivo()!= null) {
                habitanteUpd.setStActivo(habitante.getStActivo());
            }
            if(habitante.getTipoDocu().getIdTipoDocu()!=null){
                TipoDocuEntity tipoDocuEntity = tipoDocuRepository.findById(habitante.getTipoDocu().getIdTipoDocu().longValue())
                        .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
                habitanteUpd.setTipoDocu(tipoDocuEntity);
            }
            if(habitante.getIdTratamiento()!=null){
                habitanteUpd.setIdTratamiento(habitante.getIdTratamiento());
            }
            habitanteUpd.setIdUsuarioUpd(this.userService.getLoginUser().getId_usuario());
            habitanteUpd.setFeUpd(LocalDateTime.now());
            
            return habitanteRepository.save(habitanteUpd);
        }        
        
    }

    @Override
    public Optional<HabitantesEntity> findById(Long id) {
        return habitanteRepository.findById(id);
    }

    @Override
    public Page<HabitantesEntity> findAll(Pageable pageable) {
        return habitanteRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        habitanteRepository.deleteById(id);
    }

    @Override
    public Page<HabitantesEntity> findByDeApellidosNombres(String deApellidosdeNombres, Pageable pageable) {
        return habitanteRepository.findByApellidosAndNombresCombinedNative(deApellidosdeNombres, pageable);
    }

    @Override
    public Page<HabitantesEntity> findByNuDocumento(String nuDocumento, Pageable pageable) {
        return habitanteRepository.findByNuDocumento(nuDocumento, pageable);
    }
    
}
