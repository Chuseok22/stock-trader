package com.chuseok22.stocktrader.common.infrastructure.repository;

import com.chuseok22.stocktrader.common.infrastructure.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationJpaRepository extends JpaRepository<Recommendation, Long> {

}
