package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;


import com.example.homedecor.dto.Payment;

import com.example.homedecor.exception.PaymentException;


public interface PaymentService {

	public Boolean addPayment(Payment payment)throws PaymentException; 
	
	public Optional<Payment> getPaymentById(Integer paymentId)throws PaymentException;
	
	public List<Payment> getAllPayments() throws PaymentException;
	
	public Boolean deletePaymentById(Integer paymentId)throws PaymentException;
	
	public Payment updatePayment(Payment payment)throws PaymentException;

}
