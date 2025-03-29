package com.systems.service.impl;

import com.systems.dto.MantLectPeriodosDTO;
import com.systems.dto.MantLectTorresDTO;
import com.systems.dto.MantLectVistaCabDTO;
import com.systems.dto.MantLectVistaDetDTO;
import com.systems.dto.ResultSpDTO;
import com.systems.repository.MantLectRepository;
import com.systems.service.MantLectService;
import com.systems.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MantLectServiceImpl implements MantLectService {

    private MantLectRepository mantLectRepository;
    private UserService userService;

    @Autowired
    public void setMantLectRepository(
            MantLectRepository mantLectRepository,
            UserService userService
    ) {
        this.mantLectRepository = mantLectRepository;
        this.userService = userService;
    }
    
    @Override
    public MantLectVistaCabDTO fViewMantLectCabProcesar(Integer idTorre, Integer idPeriodo) {
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();
        List<Object[]> result = mantLectRepository.fViewMantLectCabProcesar(idUsuario, idTorre, idPeriodo);
        if (!result.isEmpty()) {
            MantLectVistaCabDTO dto = new MantLectVistaCabDTO();
            for (Object[] row : result) {

                dto.setIdMantenimientoCab((Integer) row[0]);
                dto.setDePeriodo((String) row[1]);
                dto.setDeEstado((String) row[2]);
                dto.setFeLecturaActual(((java.sql.Date) row[3]).toLocalDate());
                dto.setImServicioAgua(((BigDecimal) row[4]).doubleValue());
                dto.setNuM3Recibo(((BigDecimal) row[5]).doubleValue());
                dto.setImMantenimiento(((BigDecimal) row[6]).doubleValue());
                dto.setImMinAgua(((BigDecimal) row[7]).doubleValue());
            }
            return dto;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public List<MantLectVistaDetDTO> fViewMantLectDepasxTorrePeriodo(Integer idTorre, Integer idPeriodo) {
        List<Object[]> result = mantLectRepository.fViewMantLectDepasxTorrePeriodo(idTorre, idPeriodo);
        if (!result.isEmpty()) {
            List<MantLectVistaDetDTO> dtoList = new ArrayList<>();

            for (Object[] row : result) {
                MantLectVistaDetDTO dto = new MantLectVistaDetDTO();
                dto.setDeDepartamento((String) row[0]);
                dto.setNuLecturaAnterior(((BigDecimal) row[1]).doubleValue());
                dto.setNuLecturaActual(((BigDecimal) row[2]).doubleValue());
                dto.setIdMantenimientoDet((Integer) row[3]);

                dtoList.add(dto);
            }
            return dtoList;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public ResultSpDTO fSpMantLectTorreFin(Integer idTorre, Integer idPeriodo) {
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();
        List<Object[]> result = mantLectRepository.fSpMantLectTorreFin(idUsuario, idTorre, idPeriodo);
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

    @Override
    public ResultSpDTO fSpMantLectTorreDetSave(Integer idTorre, Integer idPeriodo, LocalDate feLectura, Double imServicioAgua, Double nuM3Recibo, Integer totalRegistros, String cadRegId, String cadRegLect) {
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();

        List<Object[]> result = mantLectRepository.fSpMantLectTorreDetSave(idUsuario, idTorre, idPeriodo, feLectura, imServicioAgua,nuM3Recibo, totalRegistros, cadRegId, cadRegLect);
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

    @Override
    public ResultSpDTO fSpMantLectTorreCabSave(Integer idTorre, Integer idPeriodo) {
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();
        List<Object[]> result = mantLectRepository.fSpMantLectTorreCabSave(idUsuario, idTorre, idPeriodo);
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

    @Override
    public List<MantLectTorresDTO> getTorresxUsuario() {
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();
        List<Object[]> result = mantLectRepository.fLlistMantLectTorresxUsuario(idUsuario);
        if (!result.isEmpty()) {
            List<MantLectTorresDTO> dtoList = new ArrayList<>();
            for (Object[] row : result) {
                MantLectTorresDTO dto = new MantLectTorresDTO();

                dto.setDe_torre((String) row[0]);
                dto.setId_torre((Integer) row[1]);

                dtoList.add(dto);
            }
            return dtoList;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public MantLectPeriodosDTO getPeriodoActxTorre(Integer idTorre) {
        List<Object[]> result = mantLectRepository.fListMantLectPeriodoActxTorre(idTorre);
        if (!result.isEmpty()) {
            MantLectPeriodosDTO dto = new MantLectPeriodosDTO();
            for (Object[] row : result) {

                dto.setId_periodo((Integer) row[0]);
                dto.setDe_periodo((String) row[1]);

            }
            return dto;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public ResultSpDTO valMantLectEstado(Integer idTorre, Integer idPeriodo) {
        List<Object[]> result = mantLectRepository.fValMantLectxTorrePeriodo(idTorre, idPeriodo);
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

    @Override
    public List<MantLectPeriodosDTO> getPeriodosXusuarioTorre(Integer idTorre){
        Integer idUsuario = this.userService.getLoginUser().getId_usuario();
        List<Object[]> result = mantLectRepository.fListMantLectPeriodoXusuarioTorre(idUsuario,idTorre);
        if (!result.isEmpty()) {
            List<MantLectPeriodosDTO> dtoList = new ArrayList<>();
            
            for (Object[] row : result) {
                MantLectPeriodosDTO dto = new MantLectPeriodosDTO();
                dto.setId_periodo((Integer) row[0]);
                dto.setDe_periodo((String) row[1]);
                
                dtoList.add(dto);

            }
            return dtoList;
        }
        throw new RuntimeException("No se encontraron resultados");
    }
}
