package com.systems.service.impl;

import com.systems.dto.HabiTratamientoDTO;
import com.systems.dto.HabitantesEntity;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.TipoDocuDTO;
import com.systems.dto.TipoDocuEntity;
import com.systems.repository.HabitanteRepository;
import com.systems.repository.TipoDocuRepository;
import com.systems.service.HabitanteService;
import com.systems.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
            habitante.setFeReg(LocalDateTime.now());
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
        //return habitanteRepository.findAll(pageable);
        return habitanteRepository.findByIdHabitanteNot(1L, pageable);
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

    @Override
    public List<TipoDocuDTO> listTipoDocu() {
        List<TipoDocuDTO> dtoList = new ArrayList<>();
        List<Object[]> result = habitanteRepository.listTipoDocu();
        if(!result.isEmpty()){
            for(Object[] row : result){
                TipoDocuDTO dto = new TipoDocuDTO();
                dto.setId_tipo_docu((Integer) row[0]);
                dto.setDe_tipo_docu((String) row[1]);
                
                dtoList.add(dto);
            }
        }        
        return dtoList;
    }

    @Override
    public List<HabiTratamientoDTO> listHabiTratamiento() {
        List<HabiTratamientoDTO> dtoList = new ArrayList<>();
        List<Object[]> result = habitanteRepository.listHabiTratamiento();
        if(!result.isEmpty()){
            for(Object[] row : result){
                HabiTratamientoDTO dto = new HabiTratamientoDTO();
                dto.setId_tratamiento((Integer) row[0]);
                dto.setDe_tratamiento((String) row[1]);
                
                dtoList.add(dto);
            }
        }        
        return dtoList;
    }

    @Override
    public ResultSpDTO getValTipoNumdocu(Long idHabitante, Integer idTipoDocu, String nuDocumento) {
        List<Object[]> result = habitanteRepository.getValTipoNumdocu(idHabitante, idTipoDocu, nuDocumento);
        if (!result.isEmpty()) {
            ResultSpDTO dto = new ResultSpDTO();
            for (Object[] row : result) {
                dto.setCodigo((Integer) row[0]);
                dto.setStatus(dto.getCodigo() == 0 ? 500 : 200);
                dto.setMensaje((String) row[1]);

            }
            return dto;
        }
        throw new RuntimeException("No se encontraron resultados");
    }
    
}
