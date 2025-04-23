package com.systems.repository;

import com.systems.dto.TipoDocuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocuRepository extends JpaRepository<TipoDocuEntity,Long> {
    
}
