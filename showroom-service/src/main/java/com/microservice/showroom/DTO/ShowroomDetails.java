//package com.microservice.showroom.DTO;
//
//import com.microservice.showroom.entity.Showroom;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ShowroomDetails {
//
//    private Showroom showroom;
/// /    private List<ProductServiceResponse> productResponseDTOList;
/// /
/// /    public ShowroomDetails(Showroom showroom, ProductServiceResponse productResponse) {
/// /    }
//private ProductServiceResponse product;
//}
package com.microservice.showroom.DTO;

import com.microservice.showroom.entity.Showroom;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class ShowroomDetails {
    private Showroom showroom;
    private List<ProductResponse> product;

    public ShowroomDetails(Showroom showroom, List<ProductResponse> product) {
        this.showroom = showroom;
        this.product = product;
    }
}
