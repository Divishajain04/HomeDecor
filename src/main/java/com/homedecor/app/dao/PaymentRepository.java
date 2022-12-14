package com.homedecor.app.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
}
