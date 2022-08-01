package com.example.homedecor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.homedecor.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
