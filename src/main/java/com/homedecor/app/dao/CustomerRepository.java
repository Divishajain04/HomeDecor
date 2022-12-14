package com.homedecor.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByCustomerEmailAndPassword(String customerEmail, String password);
	

}
