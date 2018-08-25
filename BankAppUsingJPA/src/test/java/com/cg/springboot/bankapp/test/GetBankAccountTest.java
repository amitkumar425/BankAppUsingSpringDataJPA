package com.cg.springboot.bankapp.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.springboot.bankapp.exception.AccountNotFoundException;
import com.cg.springboot.bankapp.exception.InvalidAccountNumberException;
import com.cg.springboot.bankapp.pojo.BankAccount;
import com.cg.springboot.bankapp.repository.BankAppRepositiry;
import com.cg.springboot.bankapp.service.BankAppServiceLayer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetBankAccountTest {
	
	@Mock
	private BankAppRepositiry bankAppRepository;
	@InjectMocks
	private BankAppServiceLayer bankAppServiceLayer;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	//testing with Negative Account Number.
	@Test
	public void testWithNegativeBankAccountNumber() {
		//Value of this variable is used to check if the expected Exception is thrown by the method invoked inside the try block.
		int flag=0;
		try {
			bankAppServiceLayer.getBankAccount(-1);
		}
		catch(InvalidAccountNumberException invalidAccountNumberException){
			flag++;
		} catch (AccountNotFoundException accountNotFoundException) {
			accountNotFoundException.printStackTrace();
		}
		Assert.assertEquals(1, flag, 0);
	}
	
	//testing with Not Available  Account Number.
	@Test
	public void testWithNotAvailableBankAccountNumber() {
		//Value of this variable is used to check if the expected Exception is thrown by the method invoked inside the try block.
		int flag=0;
		try {
			bankAppServiceLayer.getBankAccount(100);
		}
		catch(InvalidAccountNumberException invalidAccountNumberException){
			invalidAccountNumberException.printStackTrace();
			
		} catch (AccountNotFoundException accountNotFoundException) {
			flag++;
			accountNotFoundException.printStackTrace();
		}
		Assert.assertEquals(1, flag, 0);
	}
	
	//testing with Non-integer Account Number.
	@Test
	public void testWithNonIntegerCharacterAccountNumber() {
		//Value of this variable is used to check if the expected Exception is thrown by the method invoked inside the try block.
		int flag=0;
		char accountNumber = '$';
		try {
			bankAppServiceLayer.getBankAccount(accountNumber);
		}
		catch(InvalidAccountNumberException invalidAccountNumberException){
			invalidAccountNumberException.printStackTrace();
		} catch (AccountNotFoundException accountNotFoundException) {
			flag++;
			accountNotFoundException.printStackTrace();
		}
		Assert.assertEquals(1, flag, 0);
	}
	
	
//	//testing with Valid Account Number.
//	@Test
//	public void testWithValidAccountNumber() {
//		char accountNumber = '$';
//		
//		bankAppServiceLayer.addBankAccount(new BankAccount("Amit Kumar", 70_000, "savingAccount"));
//		
//		Assert.assertEquals(expected ,actual , 0);
//	}
}
