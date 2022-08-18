package com.homedecor.app.service;

import com.homedecor.app.dto.Wallet;
import com.homedecor.app.exception.WalletException;

public interface WalletService {

	public boolean addWallet(Wallet wallet)throws WalletException;
	
	public Wallet updateWallet(Wallet wallet)throws WalletException;
	
	public boolean deleteWallet(Integer walletId)throws WalletException;
	
	public Wallet getWalletById(Integer walletId)throws WalletException;
}
