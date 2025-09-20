package com.chuseok22.stocktrader.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.time-zone")
public record TimeZoneProperties(
    String kr,
    String us
) {

}
