package com.microservice.product.repository;

import com.microservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByShowroomId(int showroomId);

    Long countByShowroomId(Long showroomId);

}
