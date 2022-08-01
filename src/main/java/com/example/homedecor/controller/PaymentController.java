package com.example.homedecor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homedecor.dto.Payment;
import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.PaymentException;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.service.PaymentServiceImpl;
import com.example.homedecor.service.ProductService;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@PostMapping("payment")
	public String addPayment(@RequestBody Payment payment) throws PaymentException {
		this.paymentServiceImpl.addPayment(payment);
		return "payment added successfully";
	}
	
	@GetMapping("payment/{paymentId}")
	public Optional<Payment> getPaymentById(@PathVariable ("paymentId") Integer paymentId) throws PaymentException {
		return this.paymentServiceImpl.getPaymentById(paymentId);
	}
	
	@PatchMapping("payment")
	public Payment updatePayment(@RequestBody Payment payment) throws PaymentException {
		return this.paymentServiceImpl.updatePayment(payment);
	}
	
	@GetMapping("payment/")
	public List<Payment> getAllPayment() throws PaymentException {
		return this.paymentServiceImpl.getAllPayments();
	}
	
	@DeleteMapping("payment/{paymentId}")
	public String deletePaymentById(@PathVariable ("paymentId") Integer paymentId) throws PaymentException {
		this.paymentServiceImpl.deletePaymentById(paymentId);
		return "payment deleted successfully";
	}
	
}
