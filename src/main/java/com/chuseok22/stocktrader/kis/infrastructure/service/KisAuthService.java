package com.chuseok22.stocktrader.kis.infrastructure.service;

import com.chuseok22.stocktrader.common.infrastructure.properties.KisProperties;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class KisAuthService {

  private final KisProperties properties;
  private final WebClient kisWebClient;

  private String token;
  private Instant exp = Instant.EPOCH;

  /**
   * KIS 서버로부터 Bearer 토큰 획득
   *
   * @return Bearer 토큰
   */
  public synchronized String getBearerToken() {
    if (!StringUtils.hasText(token) || Instant.now().isAfter(exp)) {
      Map<?, ?> response = kisWebClient.post()
          .uri("/oauth2/tokenP")
          .bodyValue(Map.of(
              "grant_type", "client_credentials",
              "appkey", properties.appKey(),
              "appsecret", properties.appSecret()
          ))
          .retrieve()
          .bodyToMono(Map.class)
          .block();
      this.token = "Bearer " + String.valueOf(response.get("access_token"));
      this.exp = Instant.now().plusSeconds(23 * 3600); // 23h 캐시
    }
    return token;
  }
}
