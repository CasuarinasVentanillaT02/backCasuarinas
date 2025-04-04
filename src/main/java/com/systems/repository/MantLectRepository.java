package com.systems.repository;

import com.systems.dto.EntityFake;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MantLectRepository extends JpaRepository<EntityFake, Long> {
    
    @Query(value="select * from mantenimiento.f_list_mantlect_periodo_x_usuario_torre(:aiIdUsuario,:aiIdTorre)", nativeQuery = true)
    List<Object[]> fListMantLectPeriodoXusuarioTorre(
            @Param("aiIdUsuario") Integer aiIdUsuario,
            @Param("aiIdTorre") Integer aiIdTorre            
    );
    
    @Query(value="select * from mantenimiento.f_rep_mantlect_det(:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fRepMantLectDet(
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
    );
    
    @Query(value="select * from mantenimiento.f_rep_mantlect_cab_foot_v2(:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fRepMantLectCabFoot(
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
    );
    
    @Query(value = "SELECT * from mantenimiento.f_list_mantLect_torres_x_usuario(:aiIdUsuario)", nativeQuery = true)
    List<Object[]> fLlistMantLectTorresxUsuario(
            @Param("aiIdUsuario") Integer aiIdUsuario
            );
    @Query(value = "SELECT * from mantenimiento.f_list_mantLect_periodo_act_x_torre(:aiIdTorre)", nativeQuery = true)
    List<Object[]> fListMantLectPeriodoActxTorre(
            @Param("aiIdTorre") Integer aiIdTorre
            );
    @Query(value = "SELECT * from mantenimiento.f_val_mantLect_x_torre_periodo(:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fValMantLectxTorrePeriodo(
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
            );
    @Query(value = "SELECT * from mantenimiento.f_view_mantlect_cab_procesarv2(:aiIdUsuario,:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fViewMantLectCabProcesar(
            @Param("aiIdUsuario") Integer aiIdUsuario,
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
            );
    @Query(value = "SELECT * from mantenimiento.f_view_mantlect_depas_x_torre_periodo(:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fViewMantLectDepasxTorrePeriodo(
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
            );
    @Query(value = "SELECT * from mantenimiento.f_sp_mantlect_torre_cab_save("
            + ":aiIdUsuario,:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fSpMantLectTorreCabSave(
            @Param("aiIdUsuario") Integer aiIdUsuario,
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
            );
    @Query(value = "SELECT * FROM mantenimiento.f_sp_mantlect_torre_det_save_v2(" +
        "cast(:aiIdUsuario as integer), cast(:aiIdTorre as integer), cast(:aiIdPeriodo as integer), " +
        "cast(:adFeLectura as date), cast(:anImServicioAgua as numeric), cast(:anNuM3Recibo as numeric)," +
        "cast(:anTotalRegistros as integer), cast(:asCadRegId as varchar), cast(:asCadRegLect as varchar),"+
        "cast(:asCadRegStUrlImg as varchar), cast(:asCadRegDeUrlImg as varchar))"
        ,nativeQuery = true)
    List<Object[]> fSpMantLectTorreDetSave(
        @Param("aiIdUsuario") Integer aiIdUsuario,
        @Param("aiIdTorre") Integer aiIdTorre,
        @Param("aiIdPeriodo") Integer aiIdPeriodo,
        @Param("adFeLectura") LocalDate adFeLectura,
        @Param("anImServicioAgua") Double anImServicioAgua,
        @Param("anNuM3Recibo") Double anNuM3Recibo,
        @Param("anTotalRegistros") Integer anTotalRegistros,
        @Param("asCadRegId") String asCadRegId,
        @Param("asCadRegLect") String asCadRegLect,
        @Param("asCadRegStUrlImg") String asCadRegStUrlImg,
        @Param("asCadRegDeUrlImg") String asCadRegDeUrlImg
    );
    
    @Query(value = "SELECT * from mantenimiento.f_sp_mantlect_torre_fin("
            + ":aiIdUsuario,:aiIdTorre,:aiIdPeriodo)", nativeQuery = true)
    List<Object[]> fSpMantLectTorreFin(
            @Param("aiIdUsuario") Integer aiIdUsuario,
            @Param("aiIdTorre") Integer aiIdTorre,
            @Param("aiIdPeriodo") Integer aiIdPeriodo
            );
}
