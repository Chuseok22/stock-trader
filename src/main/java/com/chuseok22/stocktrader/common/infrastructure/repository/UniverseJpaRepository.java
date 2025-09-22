package com.chuseok22.stocktrader.common.infrastructure.repository;

import com.chuseok22.stocktrader.common.infrastructure.entity.Universe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniverseJpaRepository extends JpaRepository<Universe, Long> {

}
