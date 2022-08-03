package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.PaymentRepository;
import com.homedecor.app.dto.Payment;
import com.homedecor.app.exception.PaymentException;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Boolean addPayment(Payment payment) throws PaymentException {
		if (payment == null) {
			throw new PaymentException("Payment not added ! Please fill the mandatory field");
		}
		Optional<Payment> foundPayment = this.paymentRepository.findById(payment.getPaymentId());
		if (foundPayment.isPresent()) {
			throw new PaymentException("This Payment Id already exist");
		}
		else {
			this.paymentRepository.save(payment);
		}
		return true;
	}

	@Override
	public Optional<Payment> getPaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(paymentId);
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}
		return foundPayment; 
	}

	@Override
	public List<Payment> getAllPayments() throws PaymentException {
		List<Payment> paymentList = this.paymentRepository.findAll();
		if (paymentList.isEmpty()) {
			throw new PaymentException("Payment list not present");
		}
		return paymentList;
	}

	@Override
	public Boolean deletePaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(paymentId);
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}
		this.paymentRepository.deleteById(paymentId);
		return true;
	}

	@Override
	public Payment updatePayment(Payment payment) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(payment.getPaymentId());
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}

		return this.paymentRepository.save(payment);
	}
	
	
}
