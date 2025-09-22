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
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    name = "stock",
    uniqueConstraints = @UniqueConstraint(name = "uq_stock_region_code", columnNames = {"region", "code"}),
    indexes = {
        @Index(name = "ix_stock_region_active_code", columnList = "region,active,code")
    }
)
public class Stock extends BasePostgresEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Region region; // KR, US

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private boolean active;
}
