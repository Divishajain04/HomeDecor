package com.example.homedecor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.homedecor.exception.CustomerException;

@RestControllerAdvice
public class CustomerControllerAdvice {
	
	@ExceptionHandler(CustomerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleCustomerException(CustomerException e) {
		return e.getMessage();
	}

}
