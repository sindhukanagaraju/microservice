package com.microservice.showroom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowroomDTO {

    private int id;
    private String showroomName;
    private String Address;
    private String contactNumber;

}