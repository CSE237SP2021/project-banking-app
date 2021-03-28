package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.Account;
import main.AccountsMenu;

class AccountsMenuTests {

	private final BigDecimal EPSILON = new BigDecimal(.001);
	
	@Test
	void testDeposit() {
		Account testAccount = new Account();
		
		AccountsMenu am = new AccountsMenu(testAccount);
		String[] strings = {"deposit", "100.5", "exit"};
		//https://stackoverflow.com/questions/18949813/how-to-convert-a-string-array-to-inputstream-in-java
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(strings)).getBytes());
		am.start(new Scanner(inputStream));
		
		BigDecimal ans = new BigDecimal(100.5);
		
		assertTrue(ans.subtract(testAccount.getBalance()).compareTo(EPSILON) == -1);
	}
	
	@Test
	void testWithdraw() {
		Account testAccount = new Account();
		
		AccountsMenu am = new AccountsMenu(testAccount);
		String[] strings = {"withdraw", "100.79", "exit"};

		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(strings)).getBytes());
		am.start(new Scanner(inputStream));
		
		BigDecimal ans = new BigDecimal(-100.79);
		
		assertTrue(ans.subtract(testAccount.getBalance()).compareTo(EPSILON) == -1);
	}
	
	@Test
	void testMultipleActions() {
		Account testAccount = new Account();
		
		AccountsMenu am = new AccountsMenu(testAccount);
		String[] strings = {"withdraw", "100.5", "deposit", "150.6","balance","exit"};

		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(strings)).getBytes());
		am.start(new Scanner(inputStream));
		
		BigDecimal ans = new BigDecimal(50.1);
		
		assertTrue(ans.subtract(testAccount.getBalance()).compareTo(EPSILON) == -1);
	}

}
