package com.systems.repository;

import com.systems.dto.EntityFake;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<EntityFake, Long> { 

    @Query(value = "SELECT * from mantenimiento.f_sp_val_login_v2(:asDeUsuario, :asDeClave)", nativeQuery = true)
    List<Object[]> validateLogin(
        @Param("asDeUsuario") String asDeUsuario,
        @Param("asDeClave") String asDeClave
    );

    @Query(value = "SELECT * from mantenimiento.f_sp_val_user_v2(:asDeUsuario)", nativeQuery = true)
    List<Object[]> validateUser(
        @Param("asDeUsuario") String asDeUsuario
    );
    
    @Query(value = "SELECT mantenimiento.get_mo_upi_resultados_x_peri(:asPeriodoId)", nativeQuery = true)
    String getUpixPeriodo(
        @Param("asPeriodoId") String asPeriodoId
    );
}
