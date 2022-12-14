package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Wallet;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.WalletException;
import com.homedecor.app.service.WalletService;

@SpringBootTest
 class WalletServiceTest {

	@Autowired
	private WalletService walletService;

	@Test
	void deleteWalletByIdTest() throws WalletException {
		Wallet wallet = new Wallet(111, 100.00);
		assertTrue(this.walletService.addWallet(wallet));
		assertEquals(true, this.walletService.deleteWallet(111));
		assertThrows(WalletException.class, () -> this.walletService.deleteWallet(111));
	}

	@Test
	void addWalletTest() throws WalletException {
		assertThrows(WalletException.class, () -> this.walletService.addWallet(null));
		Wallet wallet = new Wallet(111, 100.00);
		assertTrue(this.walletService.addWallet(wallet));
		assertNotNull(this.walletService.getWalletById(111));
		assertThrows(WalletException.class, () -> this.walletService.addWallet(wallet));
		assertEquals(true, this.walletService.deleteWallet(111));
	}

	@Test
	void getWalletByIdTest() throws WalletException {
		Wallet wallet = new Wallet(111, 100.00);
		assertTrue(this.walletService.addWallet(wallet));
		assertNotNull(this.walletService.getWalletById(111));
		assertEquals(true, this.walletService.deleteWallet(111));
		assertThrows(WalletException.class, () -> this.walletService.getWalletById(111));
	}

	@Test
	void updateWalletTest() throws WalletException {
		Wallet wallet = new Wallet(111, 100.00);
		assertTrue(this.walletService.addWallet(wallet));
		assertNotNull(this.walletService.updateWallet(new Wallet(111, 1000.00)));
		assertEquals(true, this.walletService.deleteWallet(111));
		assertThrows(WalletException.class, () -> this.walletService.updateWallet(new Wallet(111,1000.00)));
	}

}
