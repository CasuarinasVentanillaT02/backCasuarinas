package com.systems.controller;

import com.systems.dto.MantLectCabDetSave;
import com.systems.dto.MantLectCabSave;
import com.systems.dto.MantLectPeriodosDTO;
import com.systems.dto.MantLectTorresDTO;
import com.systems.dto.MantLectVistaCabDTO;
import com.systems.dto.MantLectVistaDetDTO;
import com.systems.dto.ResultSpDTO;
import com.systems.service.MantLectService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mantLect/v1")
@RequiredArgsConstructor
public class MantLectController {
    private final MantLectService mantLectService;
    
    @GetMapping("/getTorres")
    public ResponseEntity<List<MantLectTorresDTO>> listaTorres() {
        return ResponseEntity.ok(mantLectService.getTorresxUsuario());
    }
    
    @GetMapping("/getPeriodo/{idTorre}")
    public ResponseEntity<MantLectPeriodosDTO> periodoAct(@PathVariable Integer idTorre) {
        return ResponseEntity.ok(mantLectService.getPeriodoActxTorre(idTorre));
    }
    
    @GetMapping("/getPeriodos/{idTorre}")
    public ResponseEntity<List<MantLectPeriodosDTO>> periodosXtorre(@PathVariable Integer idTorre) {
        return ResponseEntity.ok(mantLectService.getPeriodosXusuarioTorre(idTorre));
    }
    
    @GetMapping("/getEstado/{idTorre}/{idPeriodo}")
    public ResponseEntity<ResultSpDTO> valTorrePeriodo(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo) {
        return ResponseEntity.ok(mantLectService.valMantLectEstado(idTorre,idPeriodo));
    }
    
    @PostMapping("/saveCab")
    public ResponseEntity<ResultSpDTO> saveCab(@RequestBody @Valid MantLectCabSave mantLectCabSave) {
        return ResponseEntity.ok(mantLectService.fSpMantLectTorreCabSave(mantLectCabSave.getIdTorre(),mantLectCabSave.getIdPeriodo()));
    }
    
    @PostMapping("/saveDet")
    public ResponseEntity<ResultSpDTO> saveDet(@RequestBody @Valid MantLectCabDetSave mantLectCabDetSave) {        
        return ResponseEntity.ok(mantLectService.fSpMantLectTorreDetSave(
                    mantLectCabDetSave.getIdTorre(),
                    mantLectCabDetSave.getIdPeriodo(),
                    mantLectCabDetSave.getFeLectura(),
                    mantLectCabDetSave.getImServicioAgua(),
                    mantLectCabDetSave.getNuM3Recibo(),
                    mantLectCabDetSave.getTotalRegistros(),
                    mantLectCabDetSave.getCadRegId(),
                    mantLectCabDetSave.getCadRegLect()
        ));
    }
    
    @PostMapping("/cabFin")
    public ResponseEntity<ResultSpDTO> finCab(@RequestBody @Valid MantLectCabSave mantLectCabSave) {
        return ResponseEntity.ok(mantLectService.fSpMantLectTorreFin(mantLectCabSave.getIdTorre(),mantLectCabSave.getIdPeriodo()));
    }
    
    @GetMapping("/vistaCab/{idTorre}/{idPeriodo}")
    public ResponseEntity<MantLectVistaCabDTO> vistaCab(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo) {
        return ResponseEntity.ok(mantLectService.fViewMantLectCabProcesar(idTorre,idPeriodo));
    }
    
    @GetMapping("/vistaDet/{idTorre}/{idPeriodo}")
    public ResponseEntity<List<MantLectVistaDetDTO>> vistaDet(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo) {
        return ResponseEntity.ok(mantLectService.fViewMantLectDepasxTorrePeriodo(idTorre,idPeriodo));
    }
    
    /*@GetMapping("/reporteA401/{idTorre}/{idPeriodo}")
    public ResponseEntity<Map<String, Object>> getReport(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo) {
        Map<String, Object> report = mantLectService.genRepHojaA401(idTorre, idPeriodo);
        return ResponseEntity.ok(report);
    }
  
    @GetMapping("exportPdf01/{idTorre}/{idPeriodo}")
    public ResponseEntity<Resource> exportInvoice(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo){
        return this.mantLectService.genRepPdfA401(idTorre, idPeriodo);
    }*/
}
