package com.homedecor.app.controller;

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

import com.homedecor.app.dto.Payment;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.service.PaymentServiceImpl;

@RestController
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentServiceImpl;

	@PostMapping("payment")
	public String addPayment(@RequestBody Payment payment) throws OrderException {
		try {
			this.paymentServiceImpl.addPayment(payment);
		} catch (PaymentException e) {
			throw new OrderException(e.getMessage());
		}
		return "payment added successfully";
	}

	@GetMapping("payment/{paymentId}")
	public Optional<Payment> getPaymentById(@PathVariable("paymentId") Integer paymentId) throws OrderException {
		Optional<Payment> foundPayment;
		try {
			foundPayment = this.paymentServiceImpl.getPaymentById(paymentId);
		} catch (PaymentException e) {
			throw new OrderException(e.getMessage());
		}
		return foundPayment;
	}

	@PatchMapping("payment")
	public Payment updatePayment(@RequestBody Payment payment) throws OrderException {
		Payment foundPayment = null;
		try {
			foundPayment = this.paymentServiceImpl.updatePayment(payment);
		} catch (PaymentException e) {
			throw new OrderException(e.getMessage());
		}
		return foundPayment;
	}

	@GetMapping("payment/")
	public List<Payment> getAllPayment() throws OrderException {
		List<Payment> foundPayment = null;
		try {
			foundPayment = this.paymentServiceImpl.getAllPayments();
		} catch (PaymentException e) {
			throw new OrderException(e.getMessage());
		}
		return foundPayment;
	}

	@DeleteMapping("payment/{paymentId}")
	public String deletePaymentById(@PathVariable("paymentId") Integer paymentId) throws OrderException {
		try {
			this.paymentServiceImpl.deletePaymentById(paymentId);
		} catch (PaymentException e) {
			throw new OrderException(e.getMessage());
		}
		return "payment deleted successfully";
	}

}
