package com.homedecor.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
    List<Product> findAllByOrderByProductPriceDesc();
    
    List<Product> findAllByOrderByProductPriceAsc();
    
    List<Product> findByProductNameStartingWith(String prefix);


}
