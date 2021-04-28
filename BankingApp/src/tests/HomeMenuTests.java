package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.Account;
import main.HomeMenu;
import main.Person;

class HomeMenuTests {

	private final BigDecimal EPSILON = new BigDecimal(.001);
	
	@Test
	void testAddSingleAccount() {
		Person person = new Person("first", "last");
		HomeMenu homeMenu = new HomeMenu(person);
		
		String[] commands = {"newaccount", "checking", "exit"};
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
		
		homeMenu.start(new Scanner(inputStream));

		assertTrue(person.getAccounts().size() == 1);
	}
	
	@Test
	void testAddMultipleAccountsWithoutDuplicate() {
		Person person = new Person("first", "last");
		HomeMenu homeMenu = new HomeMenu(person);
		
		String[] commands = {"newaccount", "checking", "newaccount", "savings","exit"};
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
		
		homeMenu.start(new Scanner(inputStream));
	
		assertTrue(person.getAccounts().size() == 2);
	}
	
	@Test
	void testAddMultipleAccountsWithDuplicate() {
		Person person = new Person("first", "last");
		HomeMenu homeMenu = new HomeMenu(person);
		
		String[] commands = {"newaccount", "checking", "newaccount", "checking","cancel","exit"};
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
		
		homeMenu.start(new Scanner(inputStream));
	
		assertTrue(person.getAccounts().size() == 1);
	}
	
	@Test 
	void testSelectAccountThatExists() {
		//tests that an account is selected by creating account, selecting it, depositing money, and then checking the balance
		//if anyone knows of a better way to check if an account is selected please lmk, this feels kinda sus
		
		Person person = new Person("first", "last");
		HomeMenu homeMenu = new HomeMenu(person);
		
		String accountName = "checking";
		
		String[] commands = {"newaccount", accountName,"selectaccount",accountName,"deposit","100","exit", "exit"};
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
		
		homeMenu.start(new Scanner(inputStream));
		int index = findAccount(accountName,person);
		BigDecimal accountBalance = person.getAccounts().get(index).getBalance();
		BigDecimal ans = new BigDecimal(100);
		
		assertTrue(ans.subtract(accountBalance).compareTo(EPSILON) == -1);
	}
	
	@Test
	void testTransferWithTwoValidAccounts() {
		Person person = new Person("first", "last");
		HomeMenu homeMenu = new HomeMenu(person);
		
		String accountName = "checking";
		
		String[] commands = {"newaccount", accountName,"selectaccount",accountName,"deposit","100","exit",
				"newaccount","b","transfer",accountName,"b","90","exit"};
		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
		
		homeMenu.start(new Scanner(inputStream));
		int withdrawIndex = findAccount(accountName,person);
		int depositIndex = findAccount("b", person);
		BigDecimal withdrawAccountBalance = person.getAccounts().get(withdrawIndex).getBalance();
		BigDecimal depositAccountBalance = person.getAccounts().get(depositIndex).getBalance();
		
		BigDecimal withdrawAns = new BigDecimal(10);
		BigDecimal depositAns = new BigDecimal(90);
		
		assertTrue(withdrawAns.subtract(withdrawAccountBalance).compareTo(EPSILON) == -1);
		assertTrue(depositAns.subtract(depositAccountBalance).compareTo(EPSILON) == -1);
	}
	
	
	
//	@Test 
//	void testSelectAccountThatDoesNotExist() {
//
//		
//		Person person = new Person("first", "last");
//		HomeMenu homeMenu = new HomeMenu(person);
//		
//		String accountName = "checking";
//		
//		String[] commands = {"selectaccount","checking"};
//		InputStream inputStream = new ByteArrayInputStream(String.join(System.lineSeparator(), Arrays.asList(commands)).getBytes());
//		
//		homeMenu.start(new Scanner(inputStream));
//		int index = findAccount(accountName,person);
//		BigDecimal accountBalance = person.getAccounts().get(index).getBalance();
//		BigDecimal ans = new BigDecimal(100);
//		
//		assertTrue(ans.subtract(accountBalance).compareTo(accountBalance) == -1);
//	}
	
	/**
	 * 
	 * @param accountName
	 * @return index of account associated with accountName in the person accounts list, or -1 if not found
	 */
	private int findAccount(String accountName, Person person) {
		List<Account> accounts = person.getAccounts();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountName().equals(accountName)) {
				return i;
			}
		}
		return -1;
	}


}
