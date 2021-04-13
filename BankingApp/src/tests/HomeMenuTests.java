package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.Account;
import main.HomeMenu;
import main.Person;

class HomeMenuTests {

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


}
