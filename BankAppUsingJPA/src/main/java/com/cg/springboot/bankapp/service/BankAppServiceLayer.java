package com.cg.springboot.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springboot.bankapp.exception.AccountNotFoundException;
import com.cg.springboot.bankapp.exception.InvalidAccountNumberException;
import com.cg.springboot.bankapp.pojo.BankAccount;
import com.cg.springboot.bankapp.repository.BankAppRepositiry;

@Service
public class BankAppServiceLayer {

	@Autowired
	private BankAppRepositiry bankAppRepositiry;

	public BankAccount getBankAccount(int accountNumberToBeSearched)
			throws InvalidAccountNumberException, AccountNotFoundException {
		if ((!((Object) accountNumberToBeSearched instanceof Integer)) || accountNumberToBeSearched <= 0) {
			throw new InvalidAccountNumberException("Invalid Account number");
		} else if (bankAppRepositiry.findById(accountNumberToBeSearched).isPresent() == false) {
			throw new AccountNotFoundException("Account Not Present");
		}
		return bankAppRepositiry.findById(accountNumberToBeSearched).get();
	}

	public List<BankAccount> getAllBankAccount() {
		return bankAppRepositiry.findAll();
	}

	public void updateBankAccount(BankAccount bankAccountToBeUpdated) {
		bankAppRepositiry.save(bankAccountToBeUpdated);
	}

	public void addBankAccount(BankAccount bankAccountToBeAdded) {
		bankAppRepositiry.save(bankAccountToBeAdded);
	}

	public void deleteBankAccount(int bankAccountToBeDeleted) {
		bankAppRepositiry.deleteById(bankAccountToBeDeleted);
		;
	}

}
