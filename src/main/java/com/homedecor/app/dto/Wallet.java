package com.homedecor.app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Wallet {

	@Id
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer walletId;
	private Double balance;

	public Wallet(Integer walletId, Double balance) {
		super();
		this.walletId = walletId;
		this.balance = balance;
	}
	
	public Wallet(Integer walletId) {
		super();
		this.walletId = walletId;
	}


	public Wallet() {
		super();
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
