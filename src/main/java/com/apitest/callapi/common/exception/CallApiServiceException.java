package com.apitest.callapi.common.exception;

import lombok.Getter;

public class CallApiServiceException extends RuntimeException {

  @Getter
  private String code;

  public CallApiServiceException(String code, String message) {
    super(message);
    this.code = code;
  }
}