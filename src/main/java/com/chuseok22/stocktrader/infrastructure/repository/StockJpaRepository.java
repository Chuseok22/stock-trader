package com.chuseok22.stocktrader.infrastructure.repository;

import com.chuseok22.stocktrader.infrastructure.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {

}
