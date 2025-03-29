package com.systems.controller;

import com.systems.service.ReportesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
//import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import javax.sql.DataSource;

@RestController
@RequestMapping("/api/reportes/v1")
@RequiredArgsConstructor
@Log
public class ReportesController {
    private final ReportesService reportesService;
    private final DataSource dataSource;
    
    @GetMapping("/pdf01/{idTorre}/{idPeriodo}")
    public ResponseEntity<byte[]> repPdf01(@PathVariable Integer idTorre,@PathVariable Integer idPeriodo) {
        try(Connection connection = dataSource.getConnection()) {
            byte[] report = reportesService.reporteMantLect01(idTorre, idPeriodo,connection);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            log.info("Reporte_PDF.pdf");
            //headers.setContentDisposition(ContentDisposition.attachment().filename("Reporte_PDF.pdf").build());
            headers.add("Content-Disposition", "inline;filename=reporte.pdf");
            
            return new ResponseEntity<>(report,headers,HttpStatus.OK);
            
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
