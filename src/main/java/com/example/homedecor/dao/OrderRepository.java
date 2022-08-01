package com.example.homedecor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.homedecor.dto.OrderByCustomer;

@Repository
public interface OrderRepository extends JpaRepository<OrderByCustomer, Integer>{

}
