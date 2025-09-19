package com.chuseok22.stocktrader.infrastructure.entity;

import com.chuseok22.stocktrader.core.Region;
import com.chuseok22.stocktrader.infrastructure.jpa.BasePostgresEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Stock extends BasePostgresEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated
  @Column(nullable = false)
  private Region region;

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String name;

  private boolean active;
}
