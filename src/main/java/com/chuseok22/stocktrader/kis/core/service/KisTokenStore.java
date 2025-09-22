package com.chuseok22.stocktrader.kis.core.service;

import java.time.Duration;
import java.util.Optional;
import reactor.core.publisher.Mono;

public interface KisTokenStore {

  Mono<Optional<String>> getBearer();

  Mono<Void> saveBearer(String bearer, Duration ttl);

  Mono<Void> clear();

}
