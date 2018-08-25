package com.cg.springboot.bankapp.exception;

public class InvalidAccountNumberException extends Exception {
	private static final long serialVersionUID = -6879096742635829255L;

	public InvalidAccountNumberException(String message) {
		super(message);
	}
}
