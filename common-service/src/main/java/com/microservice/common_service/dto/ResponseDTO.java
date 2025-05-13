package com.microservice.common_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor(force = true)
public record ResponseDTO (Integer statusCode, String message,Object data
){

}