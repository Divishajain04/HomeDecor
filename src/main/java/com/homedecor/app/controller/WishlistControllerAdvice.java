package com.homedecor.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homedecor.app.exception.WishlistException;

@RestControllerAdvice
public class WishlistControllerAdvice {

	@ExceptionHandler(WishlistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleWishlistException(WishlistException e) {
		return e.getMessage();
	}
}
