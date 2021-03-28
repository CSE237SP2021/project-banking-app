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
		account = new Account();
	}
	
	@Test
	void testDeposit() {
		BigDecimal depositAmount = new BigDecimal("12.34");
		assertTrue(depositAmount.compareTo(account.getBalance()) == 0);
	}

}
