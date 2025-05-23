package com.systems.controller;

import com.systems.dto.VigCocheraHabiDTO;
import com.systems.service.VigilanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vig/v1")
public class VigilanciaController {
    private final VigilanciaService vigilanciaService;
    
    @Autowired
    public VigilanciaController(VigilanciaService vigilanciaService) {
        this.vigilanciaService = vigilanciaService;
    }
    @GetMapping("/Placa/{secretApi}/{dePlaca}")
    public ResponseEntity<VigCocheraHabiDTO> vistaUserxId(@PathVariable String secretApi,@PathVariable String dePlaca){
        VigCocheraHabiDTO resultado = vigilanciaService.fViewVigHabiVehiCocheraXPlaca(secretApi,dePlaca);
        return ResponseEntity.ok(resultado);
    }
}
