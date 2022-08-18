package com.homedecor.app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	private Integer walletId;
	private Double balance;
	public Wallet(Integer walletId, Double balance) {
		super();
		this.walletId = walletId;
		this.balance = balance;
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
