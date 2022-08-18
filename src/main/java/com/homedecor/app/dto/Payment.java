package com.homedecor.app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment {

	@Id
    @JsonIgnore
	private Integer paymentId;
	private String paymentMode;
	private Double paymentAmount;
	private String paymentStatus;

	public Payment() {
		super();
	}

	public Payment(Integer paymentId, String paymentMode, Double paymentAmount, String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
