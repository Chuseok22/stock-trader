package com.chuseok22.stocktrader.common.infrastructure.entity;

import com.chuseok22.stocktrader.common.infrastructure.jpa.BasePostgresEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "recommendation",
    uniqueConstraints = @UniqueConstraint(name = "uq_reco_date_stock", columnNames = {"for_date", "stock_id"}),
    indexes = @Index(name = "ix_reco_date_score", columnList = "for_date, score DESC")
)
public class Recommendation extends BasePostgresEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "stock_id", nullable = false)
  private Stock stock;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "universe_id", nullable = false)
  private Universe universe;

  @Column(name = "for_date", nullable = false)
  private LocalDate forDate; // 추천 날짜

  @Column(nullable = false)
  private double score;

  private Integer rank; // 순위

  @Column(name = "reason_json", columnDefinition = "jsonb")
  private String reasonJson; // 사유(JSON)
}
