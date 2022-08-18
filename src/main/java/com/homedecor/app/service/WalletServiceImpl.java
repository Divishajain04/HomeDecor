package com.homedecor.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.WalletRepository;
import com.homedecor.app.dto.Wallet;
import com.homedecor.app.exception.WalletException;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Override
	public boolean addWallet(Wallet wallet) throws WalletException {
		if (wallet == null)
			throw new WalletException("Please fill the mandatory feilds");
		Optional<Wallet> foundWallet = this.walletRepository.findById(wallet.getWalletId());
		if (foundWallet.isPresent())
			throw new WalletException("This Id Already exist please choose another id");
		this.walletRepository.save(wallet);
		return true;
	}

	@Override
	public Wallet updateWallet(Wallet wallet) throws WalletException {
		Optional<Wallet> foundWallet = this.walletRepository.findById(wallet.getWalletId());
		if (foundWallet.isEmpty())
			throw new WalletException("This Id not exist in the record for updation");

		return this.walletRepository.save(wallet);
	}

	@Override
	public boolean deleteWallet(Integer walletId) throws WalletException {
		Optional<Wallet> foundWallet = this.walletRepository.findById(walletId);
		if (foundWallet.isEmpty())
			throw new WalletException(walletId + " wallet Id not exist in the record for updation");
		this.walletRepository.deleteById(walletId);
		return true;
	}

	@Override
	public Wallet getWalletById(Integer walletId) throws WalletException {
		Optional<Wallet> foundWallet = this.walletRepository.findById(walletId);
		if (foundWallet.isEmpty())
			throw new WalletException(walletId + " wallet Id not exist in the record for updation");

		return this.walletRepository.getById(walletId);

	}

}
