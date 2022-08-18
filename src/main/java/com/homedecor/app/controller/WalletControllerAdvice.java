package com.homedecor.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homedecor.app.exception.WalletException;

@RestControllerAdvice
public class WalletControllerAdvice {
	
	@ExceptionHandler(WalletException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleWalletException(WalletException e) {
		return e.getMessage();
	}

}
