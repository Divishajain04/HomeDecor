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
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.service.PaymentService;


@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("payment")
	public String addPayment(@RequestBody Payment payment) throws PaymentException {
		try {
			this.paymentService.addPayment(payment);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage());
		}
		return "payment added successfully";
	}

	@GetMapping("payment/{paymentId}")
	public Optional<Payment> getPaymentById(@PathVariable("paymentId") Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment;
		try {
			foundPayment = this.paymentService.getPaymentById(paymentId);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage());
		}
		return foundPayment;
	}

	@PatchMapping("payment")
	public Payment updatePayment(@RequestBody Payment payment) throws PaymentException {
		Payment foundPayment = null;
		try {
			foundPayment = this.paymentService.updatePayment(payment);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage());
		}
		return foundPayment;
	}

	@GetMapping("payment/")
	public List<Payment> getAllPayment() throws PaymentException {
		List<Payment> foundPayment = null;
		try {
			foundPayment = this.paymentService.getAllPayments();
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage());
		}
		return foundPayment;
	}

	@DeleteMapping("payment/{paymentId}")
	public String deletePaymentById(@PathVariable("paymentId") Integer paymentId) throws PaymentException {
		try {
			this.paymentService.deletePaymentById(paymentId);
		} catch (PaymentException e) {
			throw new PaymentException(e.getMessage());
		}
		return "payment deleted successfully";
	}

}
