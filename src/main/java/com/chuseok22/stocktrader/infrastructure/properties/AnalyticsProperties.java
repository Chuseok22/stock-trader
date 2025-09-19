package com.chuseok22.stocktrader.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.analytics")
public record AnalyticsProperties(
    String baseUrl,
    long timeoutMs
) {

}
