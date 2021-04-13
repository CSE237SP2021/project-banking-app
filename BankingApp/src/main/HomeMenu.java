package main;

import java.util.Scanner;

public class HomeMenu {

	//All available commands under HomeMenu
	private final String NEW_ACCOUNT = "newaccount";
	private final String CANCEL = "cancel";
	private final String EXIT = "exit";
	
	private Person person;
	
	public HomeMenu(Person person) {
		this.person = person;
	}
	
	/**
	 * Starts loop that accepts all user input for command execution
	 * 
	 * @param scanner (to make unit testing easier)
	 */
	public void start(Scanner scanner) {
		System.out.println("Welcome, " + this.person.getFirstName() + " " + this.person.getLastName());
		
		mainLoop: while(true) {
			String userInput = scanner.next().toLowerCase();
			
			switch(userInput) {
			case NEW_ACCOUNT:
				newAccountHandler(scanner);
				break;
			case EXIT:
				System.out.println("Thank you for banking with us, goodbye");
				break mainLoop;
				
			}
		}
	}
	/**
	 * Handles add account functionality
	 * 
	 * @param scanner (to make unit testing easier)
	 */
	private void newAccountHandler(Scanner scanner) {
		String input;
		boolean success = false;
		boolean error = false;
		do {
			System.out.println(error ? "Error: unable to create an account with that name, enter a new name or \"cancel\" to cancel" 
					: "Please enter a unique name for your new account or \"cancel\" to cancel");
					
			input = scanner.next();
			System.out.println("INPUT" + input);
			if(input.toLowerCase().equals(CANCEL)) return;
			
			success = this.person.addAccount(input);
			error = true;
			
		} while(success == false);
		
		System.out.println("A new account was created with name: " + input);
		
	}
	
	
}
