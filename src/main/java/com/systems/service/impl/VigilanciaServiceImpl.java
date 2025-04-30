package com.systems.service.impl;

import com.systems.dto.VigCocheraHabiDTO;
import com.systems.repository.VigilanciaRepository;
import com.systems.service.VigilanciaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VigilanciaServiceImpl implements VigilanciaService {
    
    @Value("${secreteAPKString}")
    private String secretKey;
    
    private VigilanciaRepository vigilanciaRepository;
    
    @Autowired
    public void setUserRepository(VigilanciaRepository vigilanciaRepository) {
        this.vigilanciaRepository = vigilanciaRepository;
    }
    @Override
    public VigCocheraHabiDTO fViewVigHabiVehiCocheraXPlaca(String secretKeyApk,String asDePlaca) {
        VigCocheraHabiDTO dto = new VigCocheraHabiDTO();
        if(secretKeyApk.equals(secretKey)){
            List<Object[]> result = vigilanciaRepository.fViewVigHabiVehiCocheraXPlaca(asDePlaca);
            log.info("Result, {}", result);
            if(!result.isEmpty()){
                Object[] row = result.get(0);
                dto.setDe_existe((String) row[0]);
                dto.setDe_habitante((String) row[1]);
                dto.setDe_depa_habitante((String) row[2]);
                dto.setDe_ubic_cochera((String) row[3]);
                dto.setDe_placa((String) row[4]);
                dto.setSt_propietario((String) row[5]);
                dto.setSt_activo((String) row[6]);
                dto.setDe_observaciones((String) row[7]);
                return dto;
            }
            throw new RuntimeException("No se encontraron resultados");
        }else{
            dto.setDe_existe("X");
            dto.setDe_habitante("-");
            dto.setDe_depa_habitante("-");
            dto.setDe_ubic_cochera("-");
            dto.setDe_placa("-");
            dto.setSt_propietario("-");
            dto.setSt_activo("-");
            dto.setDe_observaciones("Esta APK esta Descontinuada, debe actualizar para continuar usando");
            return dto;
        }
    }
    
}
