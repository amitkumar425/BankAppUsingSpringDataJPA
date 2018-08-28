package com.cg.springboot.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springboot.bankapp.exception.AccountNotFoundException;
import com.cg.springboot.bankapp.exception.FailedTransactionException;
import com.cg.springboot.bankapp.exception.InvalidAccountNumberException;
import com.cg.springboot.bankapp.pojo.BankAccount;
import com.cg.springboot.bankapp.service.BankAppServiceLayer;

@RestController
public class BankController {

	@Autowired
	private BankAppServiceLayer bankAppServiceLayer;

	@RequestMapping("/hi")
	public String testController() {
		return "Hello";
	}

	@RequestMapping("/add")
	public  void addInitialAccount() {
		BankAccount bankAccount = new BankAccount("Amit Kumar", 50_000, "savingAccount");
		addAccount(bankAccount);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/account")
	public void addAccount(@RequestBody BankAccount bankAccountToBeAdded) {
		bankAppServiceLayer.addBankAccount(bankAccountToBeAdded);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accounts")
	public List<BankAccount> getAllBankAccounts() {
		return bankAppServiceLayer.getAllBankAccount();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountNumberToBeSearched}")
	public BankAccount getbankAccount(@PathVariable int accountNumberToBeSearched)
			throws InvalidAccountNumberException, AccountNotFoundException {
		return bankAppServiceLayer.getBankAccount(accountNumberToBeSearched);
	}

	@RequestMapping("/update/{accountNumberToBeUpdated}")
	public BankAccount updateRequest(@PathVariable int accountNumberToBeUpdated)
			throws InvalidAccountNumberException, AccountNotFoundException {
		return bankAppServiceLayer.getBankAccount(accountNumberToBeUpdated);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void writeUpdate(@RequestBody BankAccount bankAccountToBeUpdated) {
		bankAppServiceLayer.updateBankAccount(bankAccountToBeUpdated);
	}

	@RequestMapping(value = "/delete/{bankAccountToBeDeleted}", method = RequestMethod.DELETE)
	public void deleteBankAccount(@PathVariable int bankAccountToBeDeleted) {
		bankAppServiceLayer.deleteBankAccount(bankAccountToBeDeleted);
	}
	
	
	@RequestMapping("/deposit/{accountToBeDepositedIn}/{amountToBeDeposited}")
	public void depositAmount(@PathVariable int accountToBeDepositedIn, @PathVariable double amountToBeDeposited)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		bankAppServiceLayer.depositAmount(accountToBeDepositedIn, amountToBeDeposited);
	}
	
	@RequestMapping("/withdraw/{accountToBeWithdrawnFrom}/{amountToBeWithdrawn}")
	public void withdrawAmount(@PathVariable int accountToBeWithdrawnFrom,@PathVariable double amountToBeWithdrawn)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		bankAppServiceLayer.depositAmount(accountToBeWithdrawnFrom, amountToBeWithdrawn);
	}
	
	@RequestMapping("fundtransfer/{accountToBeWithdrawnFrom}/{accountToBeDepositedIn}/{amountToBeTransferred}")
	public void fundTransfer(int accountToBeWithdrawnFrom, int accountToBeDepositedIn, double amountToBeTransferred)
			throws InvalidAccountNumberException, AccountNotFoundException, FailedTransactionException {
		bankAppServiceLayer.fundTransfer(accountToBeWithdrawnFrom, accountToBeDepositedIn, amountToBeTransferred);
	}
}
