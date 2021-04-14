package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import main.Auth;
import main.Person;

public class AuthTests {
	private String basePath;
	
	@BeforeEach
	public void setupTestingObjects() {
		basePath = "./data/";
	}
	
	@Test
	/*
	 * creates a user and tests if the directory exists. then tears down the test
	 */
	public void testRegister() {
		String id = "000000";
		String password = "testpw";
		String firstName = "John";
		String lastName = "Doe";
		
		Auth.register(id, password, firstName, lastName);
		
		File userDirectory = new File(basePath + id);
		assertTrue(userDirectory.isDirectory());
		
		//teardown
		for(File file: userDirectory.listFiles()) {
			file.delete();
		}
		userDirectory.delete();
	}
	
	@Test
	/*
	 * Tests that login attempt with correct credentials works successfully
	 */
	public void testValidateLogin() {
		String id = "000000";
		String password = "testpw";
		String firstName = "John";
		String lastName = "Doe";
		File userDirectory = new File(basePath + id);
		Auth.register(id, password, firstName, lastName);
		
		boolean loginSuccess = Auth.validateLogin(id, password);
		
		assertTrue(loginSuccess);
		
		//teardown
		for(File file: userDirectory.listFiles()) {
			file.delete();
		}
		userDirectory.delete();
	}
	
	@Test
	/**
	 * Tests that wrong password but correct ID returns invalid login attempt
	 */
	public void testValidateLoginFalse() {
		String id = "000000";
		String password = "testpw";
		String firstName = "John";
		String lastName = "Doe";
		File userDirectory = new File(basePath + id);
		Auth.register(id, password, firstName, lastName);
		
		boolean loginSuccess = Auth.validateLogin(id, "incorrect password");
		
		assertTrue(!loginSuccess);
		
		//teardown
		for(File file: userDirectory.listFiles()) {
			file.delete();
		}
		userDirectory.delete();
	}
	
	@Test
	/**
	 * Tests Auth.CreatePersonFromDirectroy with valid credentials.
	 */
	public void testCreatePersonFromDirectoryGood() {
		String id = "000000";
		String password = "testpw";
		String firstName = "John";
		String lastName = "Doe";
		File userDirectory = new File(basePath + id);
		Auth.register(id, password, firstName, lastName);
		
		Person person = Auth.createPersonFromDirectory(id);
		
		if (person != null) {
			assertTrue(person.getFirstName().equals(firstName));
		}
		
		//teardown
		for(File file: userDirectory.listFiles()) {
			file.delete();
		}
		userDirectory.delete();
	}
	
	@Test
	/**
	 * Tests Auth.CreatePersonFromDirectroy with bad credentials.
	 */
	public void testCreatePersonFromDirectoryBad() {
		String id = "000000";
		String password = "testpw";
		String firstName = "John";
		String lastName = "Doe";
		File userDirectory = new File(basePath + id);
		
		Person person = Auth.createPersonFromDirectory(id);
		
		assertTrue(person == null);
	}
}
