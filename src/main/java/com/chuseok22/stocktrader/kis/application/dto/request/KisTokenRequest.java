package com.chuseok22.stocktrader.kis.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KisTokenRequest {

  private String grantType = "client_credentials";

  private String appKey;

  private String appSecret;
}
