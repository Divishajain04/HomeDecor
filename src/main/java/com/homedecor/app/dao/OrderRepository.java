package com.homedecor.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.OrderByCustomer;

@Repository
public interface OrderRepository extends JpaRepository<OrderByCustomer, Integer>{

}
