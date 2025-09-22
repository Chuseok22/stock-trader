package com.chuseok22.stocktrader.common.infrastructure.repository;

import com.chuseok22.stocktrader.common.infrastructure.entity.UniverseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniverseItemJpaRepository extends JpaRepository<UniverseItem, Long> {

}
