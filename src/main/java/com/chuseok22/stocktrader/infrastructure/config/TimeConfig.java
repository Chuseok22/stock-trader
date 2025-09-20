package com.chuseok22.stocktrader.infrastructure.config;

import com.chuseok22.stocktrader.infrastructure.properties.TimeZoneProperties;
import java.time.Clock;
import java.time.ZoneId;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimeZoneProperties.class)
public class TimeConfig {

  // 애플리케이션 전역 UTC Clock
  @Bean
  public Clock systemUtcClock() {
    return Clock.systemUTC();
  }

  // Jackson에 명시적으로 UTC 적용
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> builder.timeZone(TimeZone.getTimeZone("UTC"));
  }

  // KR/US ZoneId Bean (서비스에서 주입받아 LocalDateTime 계산)
  @Bean(name = "zoneKr")
  public ZoneId zoneKr(TimeZoneProperties properties) {
    return ZoneId.of(properties.kr());
  }

  @Bean(name = "zoneUs")
  public ZoneId zoneUs(TimeZoneProperties properties) {
    return ZoneId.of(properties.us());
  }
}
