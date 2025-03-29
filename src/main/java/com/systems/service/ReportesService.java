package com.systems.service;

import java.sql.Connection;

public interface ReportesService {
    public byte[] reporteMantLect01(Integer idTorre, Integer idPeriodo,Connection conexion) throws Exception;
}
