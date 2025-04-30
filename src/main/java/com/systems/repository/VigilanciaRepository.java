package com.systems.repository;

import com.systems.dto.EntityFake;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VigilanciaRepository extends JpaRepository<EntityFake, Long> {
    @Query(value = "select * from mantenimiento.f_view_vig_habi_vehi_cochera_x_placa(:asDePlaca)", nativeQuery = true)
    List<Object[]> fViewVigHabiVehiCocheraXPlaca(
            @Param("asDePlaca") String asDePlaca
    );
}
