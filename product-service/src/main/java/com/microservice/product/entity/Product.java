package com.microservice.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Double price;

    @Column(name = "colour")
    private String colour;

    @Column(name = "stock")
    private String stock;

    @Column(name = "brand")
    private String brand;

    @Column(name = "showroom_id")
    private Integer showroomId;

}

