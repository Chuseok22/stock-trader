package com.chuseok22.stocktrader.infrastructure.config;

import com.chuseok22.stocktrader.infrastructure.properties.AnalyticsProperties;
import com.chuseok22.stocktrader.infrastructure.properties.KisProperties;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({AnalyticsProperties.class, KisProperties.class})
public class WebClientConfig {

  private final AnalyticsProperties analyticsProperties;
  private final KisProperties kisProperties;

  @Bean
  public WebClient analyticsWebClient() {
    HttpClient httpClient = HttpClient.create()
        .responseTimeout(Duration.ofMillis(analyticsProperties.timeoutMs()));
    return WebClient.builder()
        .baseUrl(analyticsProperties.baseUrl())
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  public WebClient kisWebClient() {
    HttpClient httpClient = HttpClient.create()
        .responseTimeout(Duration.ofMillis(kisProperties.timeoutMs()));
    return WebClient.builder()
        .baseUrl(kisProperties.baseUrl())
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
