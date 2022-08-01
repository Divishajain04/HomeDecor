package com.example.homedecor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.homedecor.exception.ProductException;

@RestControllerAdvice
public class ProductControllerAdvice {

	@ExceptionHandler(ProductException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String ProductException(ProductException e) {
		return e.getMessage();
	}
}
