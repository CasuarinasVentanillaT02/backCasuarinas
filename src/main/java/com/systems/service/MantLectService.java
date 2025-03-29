package com.systems.service;

import com.systems.dto.MantLectPeriodosDTO;
import com.systems.dto.MantLectTorresDTO;
import com.systems.dto.MantLectVistaCabDTO;
import com.systems.dto.MantLectVistaDetDTO;
import com.systems.dto.ResultSpDTO;
import java.time.LocalDate;
import java.util.List;

public interface MantLectService {
    public List<MantLectTorresDTO> getTorresxUsuario();

    public MantLectPeriodosDTO getPeriodoActxTorre(Integer idTorre);

    public ResultSpDTO valMantLectEstado(Integer idTorre, Integer idPeriodo);

    public ResultSpDTO fSpMantLectTorreCabSave(Integer idTorre, Integer idPeriodo);

    public ResultSpDTO fSpMantLectTorreDetSave(Integer idTorre, Integer idPeriodo, LocalDate feLectura, Double imServicioAgua, Double nuM3Recibo,Integer totalRegistros, String cadRegId, String cadRegLect);
    
    public ResultSpDTO fSpMantLectTorreFin(Integer idTorre, Integer idPeriodo);
    
    public MantLectVistaCabDTO fViewMantLectCabProcesar(Integer idTorre, Integer idPeriodo);
    
    public List<MantLectVistaDetDTO> fViewMantLectDepasxTorrePeriodo(Integer idTorre, Integer idPeriodo);
    
    public List<MantLectPeriodosDTO> getPeriodosXusuarioTorre(Integer idTorre);
}
