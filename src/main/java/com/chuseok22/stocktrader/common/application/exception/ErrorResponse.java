package com.chuseok22.stocktrader.common.application.exception;

public record ErrorResponse(
    ErrorCode errorCode,
    String errorMessage
) {

  public static ErrorResponse from(ErrorCode errorCode, String errorMessage) {
    return new ErrorResponse(errorCode, errorMessage);
  }
}
