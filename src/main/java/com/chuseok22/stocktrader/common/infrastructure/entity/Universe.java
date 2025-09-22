package com.chuseok22.stocktrader.common.infrastructure.entity;

import com.chuseok22.stocktrader.common.core.constants.Region;
import com.chuseok22.stocktrader.common.infrastructure.jpa.BasePostgresEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    name = "universe",
    uniqueConstraints = @UniqueConstraint(name = "uq_universe_region_date", columnNames = {"region", "snapshot_date"})
)
public class Universe extends BasePostgresEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Region region;

  @Column(nullable = false)
  private int size; // 포함 종목 수

  @Column(name = "snapshot_date", nullable = false)
  private LocalDate snapshotDate; // 스냅샷 기준일

  @Column(nullable = false)
  private String ruleVersion;
}
