package com.microservice.product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;
    private String model;
    private Double price;
    private String colour;
    private String stock;
    private String brand;
    private Integer showroomId;


}
