package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.Payment;
import com.homedecor.app.exception.PaymentException;


public interface PaymentService {

	public Boolean addPayment(Payment payment)throws PaymentException; 
	
	public Optional<Payment> getPaymentById(Integer paymentId)throws PaymentException;
	
	public List<Payment> getAllPayments() throws PaymentException;
	
	public Boolean deletePaymentById(Integer paymentId)throws PaymentException;
	
	public Payment updatePayment(Payment payment)throws PaymentException;

}
