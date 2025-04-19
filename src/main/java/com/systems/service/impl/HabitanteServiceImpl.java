package com.systems.service.impl;

import com.systems.dto.HabitantesEntity;
import com.systems.repository.HabitanteRepository;
import com.systems.service.HabitanteService;
import com.systems.service.UserService;
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
    private UserService userService;
    
    @Autowired
    public HabitanteServiceImpl(HabitanteRepository habitanteRepository,
            UserService userService) {
        this.habitanteRepository = habitanteRepository;
        this.userService = userService;
    }
    
    @Override
    public HabitantesEntity saveHabitante(HabitantesEntity habitante) {
        if(habitante.getIdHabitante()>0){
            habitante.setIdUsuarioUpd(this.userService.getLoginUser().getId_usuario());
        }else{
            habitante.setIdUsuarioReg(this.userService.getLoginUser().getId_usuario());
        }        
        return habitanteRepository.save(habitante);
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
    
}
