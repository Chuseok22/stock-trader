package com.chuseok22.stocktrader.infrastructure.config;

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
@EnableConfigurationProperties(KisProperties.class)
public class KisWebClientConfig {

  private final KisProperties properties;

  @Bean
  public WebClient kisWebClient() {
    return WebClient.builder()
        .baseUrl(properties.baseUrl())
        .clientConnector(kisHttpConnector())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  public ReactorClientHttpConnector kisHttpConnector() {
    HttpClient httpClient = HttpClient.create()
        .responseTimeout(Duration.ofMillis(properties.timeoutMs()));
    return new ReactorClientHttpConnector(httpClient);
  }
}
