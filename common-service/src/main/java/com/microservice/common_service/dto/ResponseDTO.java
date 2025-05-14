package com.microservice.common_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ResponseDTO {
    private final Integer statusCode;
    private final String message;
    private final Object data;
}