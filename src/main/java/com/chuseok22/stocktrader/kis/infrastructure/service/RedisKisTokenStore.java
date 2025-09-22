package com.chuseok22.stocktrader.kis.infrastructure.service;

import com.chuseok22.stocktrader.kis.core.service.KisTokenStore;
import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedisKisTokenStore implements KisTokenStore {

  private static final String KEY = "kis:access_token";

  private final ReactiveStringRedisTemplate redisTemplate;

  @Override
  public Mono<Optional<String>> getBearer() {
    return redisTemplate.opsForValue().get(KEY).map(Optional::ofNullable);
  }

  @Override
  public Mono<Void> saveBearer(String bearer, Duration ttl) {
    return redisTemplate.opsForValue()
        .set(KEY, bearer, ttl)
        .then();
  }

  @Override
  public Mono<Void> clear() {
    return redisTemplate.delete(KEY).then();
  }
}
