package com.cg.springboot.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springboot.bankapp.exception.AccountNotFoundException;
import com.cg.springboot.bankapp.exception.FailedTransactionException;
import com.cg.springboot.bankapp.exception.InvalidAccountNumberException;
import com.cg.springboot.bankapp.pojo.BankAccount;
import com.cg.springboot.bankapp.repository.BankAppRepositiry;

@Service
public class BankAppServiceLayer {

	@Autowired
	private BankAppRepositiry bankAppRepository;

	public BankAccount getBankAccount(int accountNumberToBeSearched)
			throws InvalidAccountNumberException, AccountNotFoundException {
		if ((!((Object) accountNumberToBeSearched instanceof Integer)) || accountNumberToBeSearched <= 0) {
			throw new InvalidAccountNumberException("Invalid Account number");
		} else if (bankAppRepository.findById(accountNumberToBeSearched).isPresent() == false) {
			throw new AccountNotFoundException("Account Not Present");
		}
		return bankAppRepository.findById(accountNumberToBeSearched).get();
	}

	public List<BankAccount> getAllBankAccount() {
		return bankAppRepository.findAll();
	}

	public BankAccount updateBankAccount(BankAccount bankAccountToBeUpdated) {
		return bankAppRepository.save(bankAccountToBeUpdated);
	}

	public BankAccount addBankAccount(BankAccount bankAccountToBeAdded) {
		BankAccount bankAccount = bankAppRepository.save(bankAccountToBeAdded);
		System.out.println(bankAccount);
		return bankAccount;
	}

	public void deleteBankAccount(int bankAccountToBeDeleted) {
		bankAppRepository.deleteById(bankAccountToBeDeleted);
	}

	public double depositAmount(int accountToBeDepositedIn, double amountToBeDeposited)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		if ((Object) amountToBeDeposited instanceof Double && amountToBeDeposited > 0) {

			getBankAccount(accountToBeDepositedIn).setAccountBalance(
					getBankAccount(accountToBeDepositedIn).getAccountBalance() + amountToBeDeposited);
			updateBankAccount(getBankAccount(accountToBeDepositedIn));
			return amountToBeDeposited;
		} else
			throw new FailedTransactionException("Transaction Failed Due to Invalid Credentials");
	}

	public double withdrawAmount(int accountToBeWithdrawnFrom, double amountToBeWithdrawn)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		if ((Object) amountToBeWithdrawn instanceof Double && amountToBeWithdrawn > 0
				&& getBankAccount(accountToBeWithdrawnFrom).getAccountBalance() >= amountToBeWithdrawn) {
			getBankAccount(accountToBeWithdrawnFrom).setAccountBalance(
					getBankAccount(accountToBeWithdrawnFrom).getAccountBalance() - amountToBeWithdrawn);
			updateBankAccount(getBankAccount(accountToBeWithdrawnFrom));
			return amountToBeWithdrawn;
		} else {
			throw new FailedTransactionException("Transaction Failed due to Invalid Credentials");
		}
	}

	public void fundTransfer(int accountToBeWithdrawnFrom, int accountToBeDepositedIn, double amountToBeTransferred)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		if ((Object) amountToBeTransferred instanceof Double && amountToBeTransferred > 0
				&& getBankAccount(accountToBeWithdrawnFrom).getAccountBalance() >= amountToBeTransferred) {
			depositAmount(accountToBeDepositedIn, withdrawAmount(accountToBeWithdrawnFrom, amountToBeTransferred));
		}else {
			throw new FailedTransactionException("Transaction Failed.\n Either of the provided credentials may be wrong. ");
		}
	}
}
