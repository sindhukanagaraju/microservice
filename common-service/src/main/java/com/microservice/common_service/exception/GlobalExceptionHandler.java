package com.microservice.common_service.exception;

import com.microservice.common_service.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.ServiceUnavailableException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestServiceAlertException.class)
    public ResponseEntity<ResponseDTO> handleBadRequestServiceAlertException(final BadRequestServiceAlertException exception, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false));
        exception.printStackTrace();
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleSecurityException(final Exception exception, WebRequest request) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ResponseDTO> serviceNotHandleCustomException(final ServiceUnavailableException ex, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseDTO, HttpStatus.SERVICE_UNAVAILABLE);
}
}
