package com.chuseok22.stocktrader.common.infrastructure.repository;

import com.chuseok22.stocktrader.common.infrastructure.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {

}
