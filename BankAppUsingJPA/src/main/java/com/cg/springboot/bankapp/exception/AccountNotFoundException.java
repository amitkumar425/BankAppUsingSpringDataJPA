package com.cg.springboot.bankapp.exception;

public class AccountNotFoundException extends Exception {
	private static final long serialVersionUID = -8499608977084802068L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
