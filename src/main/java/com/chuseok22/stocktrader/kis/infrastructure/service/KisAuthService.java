package com.chuseok22.stocktrader.kis.infrastructure.service;

import com.chuseok22.stocktrader.common.application.exception.CustomException;
import com.chuseok22.stocktrader.common.application.exception.ErrorCode;
import com.chuseok22.stocktrader.common.infrastructure.properties.KisProperties;
import com.chuseok22.stocktrader.kis.application.dto.request.KisTokenRequest;
import com.chuseok22.stocktrader.kis.application.dto.response.KisTokenResponse;
import com.chuseok22.stocktrader.kis.core.service.KisTokenStore;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * KIS accessToken 발급
 * https://apiportal.koreainvestment.com/apiservice-apiservice
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KisAuthService {

  private static final long SAFETY_SKEW_SECONDS = 300L; // 5분 버퍼
  private static final String TOKEN_PATH = "/oauth2/token";

  private final WebClient kisWebClient;
  private final KisTokenStore tokenStore;
  private final KisProperties properties;

  /**
   * Bearer 토큰 획득
   * Redis에 저장되어있으면 그대로 사용, 없으면 발급 후 TTL 저장
   *
   * @return Bearer 토큰
   */
  public Mono<String> getAccessTokenBearer() {
    return tokenStore.getBearer()
        .flatMap(optional ->
            optional.<Mono<? extends String>>map(Mono::just)
                .orElseGet(this::requestAccessToken));
  }

  /**
   * KIS 토큰 발급 및 저장
   */
  public Mono<String> requestAccessToken() {
    KisTokenRequest body = new KisTokenRequest("client_credentials", properties.appKey(), properties.appSecret());
    log.info("KIS 서버로 부터 새로운 accessToken 발급 시도");
    return kisWebClient.post()
        .uri(TOKEN_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .onStatus(HttpStatusCode::isError, clientResponse ->
            clientResponse.bodyToMono(String.class)
                .defaultIfEmpty("")
                .flatMap(msg -> {
                  int status = clientResponse.statusCode().value();
                  log.error("[KIS] 토큰 발급 실패. status={}, message={}", status, msg);
                  return Mono.error(new CustomException(ErrorCode.KIS_TOKEN_REQUEST_FAILED));
                })
        )
        .bodyToMono(KisTokenResponse.class)
        .flatMap(response -> {
          String bearer = response.tokenType() + " " + response.accessToken();
          long expiresIn = Math.max(0L, response.expiresIn());
          long ttlSeconds = Math.max(60L, expiresIn - SAFETY_SKEW_SECONDS);
          Duration ttl = Duration.ofSeconds(ttlSeconds);
          log.info("[KIS] accessToken 발급 성공");
          return tokenStore.saveBearer(bearer, ttl).thenReturn(bearer);
        });
  }
}
