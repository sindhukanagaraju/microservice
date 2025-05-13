package com.microservice.product.controller;


import com.microservice.common_service.dto.ResponseDTO;
import com.microservice.common_service.util.Constant;
import com.microservice.product.DTO.ProductResponse;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/showroom/{Id}")
    public ResponseDTO findProductByShowroomId(@PathVariable("Id") final int showroomId) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.productService.retrieveProductById(showroomId));
    }

    @PostMapping("/product")
    public ResponseDTO createProduct(@RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.productService.createProduct(product));
    }

    @GetMapping("/product/{id}")
    public ResponseDTO retrieveProductById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.productService.retrieveProductById(id));
    }

    @GetMapping("/product")
    public ResponseDTO retrieveProduct() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.productService.retrieveProduct());
    }

    @PutMapping("/product/{id}")
    public ResponseDTO updateProductById(@PathVariable final Integer id, @RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.productService.updateProductById(product, id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseDTO removeProductById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.productService.removeProductById(id));
    }
}
