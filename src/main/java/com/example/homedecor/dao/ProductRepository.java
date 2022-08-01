package com.example.homedecor.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.homedecor.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
    List<Product> findAllByOrderByProductPriceDesc();
    
    List<Product> findAllByOrderByProductPriceAsc();
    
    Product findByProductNameStartingWith(String prefix);


}
