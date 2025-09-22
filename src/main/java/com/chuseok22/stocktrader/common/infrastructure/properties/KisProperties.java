package com.chuseok22.stocktrader.common.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kis")
public record KisProperties(
    String baseUrl,
    String mockBaseUrl,
    String appKey,
    String appSecret,
    String customerType,
    long timeoutMs
) {

}
