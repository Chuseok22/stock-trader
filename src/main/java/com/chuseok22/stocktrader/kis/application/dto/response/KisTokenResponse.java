package com.chuseok22.stocktrader.kis.application.dto.response;

public record KisTokenResponse(
    String accessToken,
    String tokenType,
    long expiresIn,
    String expiredAt
) {

}
