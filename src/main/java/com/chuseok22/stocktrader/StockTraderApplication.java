package com.chuseok22.stocktrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StockTraderApplication {

  public static void main(String[] args) {
    SpringApplication.run(StockTraderApplication.class, args);
  }

}
