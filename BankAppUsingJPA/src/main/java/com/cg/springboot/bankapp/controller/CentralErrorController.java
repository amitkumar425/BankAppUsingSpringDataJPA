package com.cg.springboot.bankapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.springboot.bankapp.exception.AccountNotFoundException;
import com.cg.springboot.bankapp.exception.InvalidAccountNumberException;
import com.cg.springboot.bankapp.pojo.ErrorResponse;

@ControllerAdvice
public class CentralErrorController {
	private ErrorResponse errorResponse = new ErrorResponse();

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException exception){
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(exception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidAccountNumberException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountNumberException(InvalidAccountNumberException exception){
		errorResponse.setErrorCode(400);
		errorResponse.setErrorMessage(exception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
}
