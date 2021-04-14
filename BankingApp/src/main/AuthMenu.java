package main;

import java.util.Scanner;

public class AuthMenu extends Menu {

	private final String LOGIN = "login";
	private final String REGISTER = "register";
	private final String EXIT = "exit";

	Scanner scanner;

	public void start(Scanner _scanner) {
		System.out.println("BANKING APP");
		scanner = _scanner;

		while (true) {
			mainLoop: while (true) {
				System.out.println("Welcome to your banking app! To login, type 'login'. To create a new account, type 'register'.");
				String userInput = scanner.next().toLowerCase();
				
				switch (userInput) {
				case LOGIN:
					if (!loginAttempt()) {
						System.out.println("Error: unable to login.");
					}
					break;
				case REGISTER:
					registerAttempt();
					break;
				default:
					System.out.println("Command not recognized. Acceptable commands are: " + LOGIN + ", " + REGISTER + ", " + EXIT);
				}
			}
		}
	}

	/**
	 * Takes in user input from scanner and attempts to login
	 * 
	 * @return success of login attempt
	 */
	private boolean loginAttempt() {
		boolean isSuccessful = false;

		System.out.print("Please enter your username: ");
		String username = this.inputAlphanumericString(scanner);
		System.out.print("Please enter your password: ");
		String password = this.inputAlphanumericString(scanner);
		
		isSuccessful = Auth.validateLogin(username, password);
		
		if (isSuccessful) {
			Person person = Auth.createPersonFromDirectory(username);
			HomeMenu homeMenu = new HomeMenu(person);
			homeMenu.start(scanner);
		}
		
		return isSuccessful;
	}

	/**
	 * Takes in user input from scanner and attempts to register
	 * 
	 * @return success state of register attempt
	 */
	private boolean registerAttempt() {
		boolean isSuccessful = false;
		
		System.out.print("Please enter your username: ");
		String username = this.inputAlphanumericString(scanner);
		System.out.print("Please enter your password: ");
		String password = this.inputAlphanumericString(scanner);
		System.out.print("Please enter your first name: ");
		String firstName = this.inputAlphanumericString(scanner);
		System.out.print("Please enter your last name: ");
		String lastName = this.inputAlphanumericString(scanner);

		System.out.println("Attempting to register account with id: " + username + "...");
		isSuccessful = Auth.register(username, password, firstName, lastName);

		if (isSuccessful) {
			System.out.println("Account created successfully!");
		} else {
			System.out.println("Error: failed to create account!");
		}
		
		return isSuccessful;
	}
}
