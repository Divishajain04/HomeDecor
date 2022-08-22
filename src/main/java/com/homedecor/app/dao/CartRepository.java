package com.homedecor.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
