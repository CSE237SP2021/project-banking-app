package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import main.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTests {
	
	private Account account;
	
	@BeforeEach
	public void setupTestingObjects() {
		account = new Account("checking");
	}
	
	@Test
	void testGetBalance() {
		assertTrue(account.getBalance().compareTo(new BigDecimal(0)) == 0);
	}
	
	@Test
	void testDepositGood() {
		BigDecimal depositAmount = new BigDecimal("12.34");
		
		account.deposit(depositAmount);
		
		assertTrue(depositAmount.compareTo(account.getBalance()) == 0);
	}
	
	@Test
	void testDepositNegative() {
		BigDecimal depositAmount = new BigDecimal("-1");
		
		account.deposit(depositAmount);
		
		assertTrue(new BigDecimal(0).compareTo(account.getBalance()) == 0);
	}
	
	@Test
	void testWithdrawGood() {
		BigDecimal depositAmount = new BigDecimal("12.34");
		BigDecimal withdrawAmount = new BigDecimal("12.00");
		BigDecimal endAmount = depositAmount.subtract(withdrawAmount);
		
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
		
		assertTrue(account.getBalance().compareTo(endAmount) == 0);
	}
	
	@Test
	void testWithdrawNegative() {
		BigDecimal withdrawAmount = new BigDecimal("-1");
		
		account.withdraw(withdrawAmount);
		
		assertTrue(new BigDecimal(0).compareTo(account.getBalance()) == 0);
	}

}
