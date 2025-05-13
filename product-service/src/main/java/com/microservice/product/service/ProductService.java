package com.microservice.product.service;


import com.microservice.common_service.exception.BadRequestServiceAlertException;
import com.microservice.common_service.util.Constant;
import com.microservice.product.DTO.ProductResponse;
import com.microservice.product.entity.Product;
import com.microservice.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ProductService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<ProductResponse> findProductByShowroomId(final int showroomId) {

        List<Product> productsByShowroomId= this.productRepository.findByShowroomId(showroomId);
        if (productsByShowroomId.isEmpty()) {

            throw new BadRequestServiceAlertException("Products not found for showroom ID: " + showroomId);
        }
        return productsByShowroomId.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    @Transactional
    public Product createProduct(final Product product) {
        return this.productRepository.save(product);
    }

    public Product retrieveProductById(final Integer id) {
        return this.productRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Product> retrieveProduct() {
        return this.productRepository.findAll();
    }

    @Transactional
    public Product updateProductById(final Product product, final Integer id) {
        final Product existingProduct = this.productRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (product.getModel() != null) {
            existingProduct.setModel(product.getModel());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getColour() != null) {
            existingProduct.setColour(product.getColour());
        }
        if (product.getBrand() != null) {
            existingProduct.setBrand(product.getBrand());
        }
        if (product.getStock() != null) {
            existingProduct.setStock(product.getStock());
        }
        if (product.getShowroomId() != null) {
            existingProduct.setShowroomId(product.getShowroomId());
        }
        return this.productRepository.save(existingProduct);
    }

    public String removeProductById(final Integer id) {
        final Product product = this.productRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.productRepository.delete(product);
        return Constant.REMOVE;
    }
}


