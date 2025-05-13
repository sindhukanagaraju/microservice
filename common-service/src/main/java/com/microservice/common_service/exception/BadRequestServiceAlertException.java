package com.microservice.common_service.exception;

public class BadRequestServiceAlertException extends RuntimeException {
  public BadRequestServiceAlertException(String message) {
    super(message);
  }
}
