package com.systems.controller;

import com.systems.dto.HabiTratamientoDTO;
import com.systems.dto.HabitantesEntity;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.TipoDocuDTO;
import com.systems.service.HabitanteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    /*@DeleteMapping("/{id}")
    public void deleteHabitante(@PathVariable Long id) {
        habitanteService.deleteById(id);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteHabitante(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<HabitantesEntity> habitante = habitanteService.findById(id);
        if (habitante.isEmpty()) {
            response.put("status", 500);
            response.put("message", "No se pudo eliminar el habitante. No Existe: " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        try {
            habitanteService.deleteById(id); // Lógica para borrar el habitante
            response.put("status", 200);
            response.put("message", "Habitante eliminado correctamente.");
            return ResponseEntity.ok(response); // Devuelve el código 200
        } catch (Exception e) {
            response.put("status", 500);
            response.put("message", "No se pudo eliminar el habitante. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Devuelve el código 500
        }
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
    
    @GetMapping("/tipoDocu")
    public List<TipoDocuDTO> getTipoDocu(){
        return habitanteService.listTipoDocu();
    }
    
    @GetMapping("/habiTratamiento")
    public List<HabiTratamientoDTO> getTratamiento(){
        return habitanteService.listHabiTratamiento();
    }
    
    @GetMapping("/valTipoNumDocu")
    public ResultSpDTO getValTipoNumdocu(@RequestParam Long idHabitante,@RequestParam Integer idTipoDocu,@RequestParam String nuDocumento){
        return habitanteService.getValTipoNumdocu(idHabitante, idTipoDocu, nuDocumento);
    }
}
