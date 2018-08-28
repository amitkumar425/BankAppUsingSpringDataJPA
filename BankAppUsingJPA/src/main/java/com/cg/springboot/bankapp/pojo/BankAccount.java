package com.cg.springboot.bankapp.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountNumber;
	private static int nextAccountNumber;
	private String accountHolderName;
	private double accountBalance;
	private String accountType;

	//No-Argument Constructor
	public BankAccount() {
		
	}


	//Parameterized Constructor
	public BankAccount(String accountHolderName, double accountBalance, String accountType) {
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}

	
	//Setters and getters
	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		System.out.println(accountBalance+"dddddddddd");
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public static int getNextAccountNumber() {
		return nextAccountNumber;
	}


	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", accountBalance=" + accountBalance + ", accountType=" + accountType + "]";
	}
	
	
	
}
