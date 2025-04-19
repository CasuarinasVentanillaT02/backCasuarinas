package com.systems.controller;

import com.systems.dto.HabitantesEntity;
import com.systems.service.HabitanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/habitantes/v1")
//@RequiredArgsConstructor
public class HabitanteController {
    private final HabitanteService habitanteService;

    @Autowired
    public HabitanteController(HabitanteService habitanteService) {
        this.habitanteService = habitanteService;
    }

    @PostMapping
    public HabitantesEntity createHabitante(@RequestBody HabitantesEntity habitante) {
        return habitanteService.saveHabitante(habitante);
    }

    @GetMapping("/{id}")
    public Optional<HabitantesEntity> getHabitante(@PathVariable Long id) {
        return habitanteService.findById(id);
    }

    @GetMapping
    public Page<HabitantesEntity> getAllHabitantes(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return habitanteService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteHabitante(@PathVariable Long id) {
        habitanteService.deleteById(id);
    }
    
    @GetMapping("/buscarApeNom")
    public Page<HabitantesEntity> getHabitantesByCombinedNames(@RequestParam String parametro,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return habitanteService.findByDeApellidosNombres(parametro, pageable);
    }
    
    @GetMapping("/buscarDoc")
    public Page<HabitantesEntity> getHabitantesByDocumento(@RequestParam String parametro,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return habitanteService.findByNuDocumento(parametro, pageable);
    }

}
