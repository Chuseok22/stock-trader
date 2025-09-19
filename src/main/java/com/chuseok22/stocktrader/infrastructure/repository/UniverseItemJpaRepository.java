package com.chuseok22.stocktrader.infrastructure.repository;

import com.chuseok22.stocktrader.infrastructure.entity.UniverseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniverseItemJpaRepository extends JpaRepository<UniverseItem, Long> {

}
