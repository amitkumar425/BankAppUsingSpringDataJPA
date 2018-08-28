package com.cg.springboot.bankapp.exception;

public class FailedTransactionException extends Exception {
	private static final long serialVersionUID = 1861647055611605521L;
	public FailedTransactionException(String message) {
		super(message);
	}

}
