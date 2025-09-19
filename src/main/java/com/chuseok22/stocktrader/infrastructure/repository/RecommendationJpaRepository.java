package com.chuseok22.stocktrader.infrastructure.repository;

import com.chuseok22.stocktrader.infrastructure.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationJpaRepository extends JpaRepository<Recommendation, Long> {

}
