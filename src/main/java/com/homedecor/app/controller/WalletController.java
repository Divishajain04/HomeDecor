package com.homedecor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Wallet;
import com.homedecor.app.exception.WalletException;
import com.homedecor.app.service.WalletService;

@RestController
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@PostMapping("wallet")
	public String addWallet(@RequestBody Wallet wallet) throws WalletException {
		this.walletService.addWallet(wallet);
		return "Wallet added Successfully";
	}
	
	@PutMapping("wallet")
	public Wallet updateWallet(@RequestBody Wallet wallet) throws WalletException {
		
		return this.walletService.updateWallet(wallet);
	}
	
	@GetMapping("wallet/{walletId}")
	public Wallet getWalletById(@PathVariable ("walletId") Integer walletId) throws WalletException {
		return this.walletService.getWalletById(walletId);
	}
	
	@DeleteMapping("wallet/{walletId}")
	public String deleteWalletById(@PathVariable ("walletId") Integer walletId) throws WalletException {
		this.walletService.deleteWallet(walletId);
		return "Wallet deleted successfully";
	}

}
