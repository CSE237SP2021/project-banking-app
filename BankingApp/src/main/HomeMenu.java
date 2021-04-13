package main;

import java.util.List;
import java.util.Scanner;

public class HomeMenu {

	//All available commands under HomeMenu
	private final String NEW_ACCOUNT = "newaccount";
	private final String CANCEL = "cancel";
	private final String EXIT = "exit";
	private final String SELECT = "selectaccount";
	
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
			case SELECT:
				selectAccountHandler(scanner);
				break;
			default:
				System.out.println("Command not recognized");
				
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
			
			if(input.toLowerCase().equals(CANCEL)) return;
			
			success = this.person.addAccount(input);
			error = true;
			
		} while(success == false);
		
		System.out.println("A new account was created with name: " + input);	
	}
	/**
	 * Handles selecting account functionality
	 * 
	 * @param scanner
	 */
	private void selectAccountHandler(Scanner scanner) {
		String input;
		boolean success = false;
		do {
			System.out.println("Available accounts are: ");
			
			for(Account acc : person.getAccounts()) {
				System.out.print(acc + " ");
			}
			
			System.out.println("");
			System.out.println("Enter account name to select that account");
			
			input = scanner.next();
			int index = findAccount(input);
			if(index == -1) {
				System.out.println("Error, account \"" + input + "\" not found");
			}else {
				Account selectedAccount = person.getAccounts().get(index);
				AccountsMenu accountsMenu = new AccountsMenu(selectedAccount);
				accountsMenu.start(scanner);
				success = true;
			}
			
		} while(success == false);
		
	}
	/**
	 * 
	 * @param accountName
	 * @return index of account associated with accountName in the person accounts list, or -1 if not found
	 */
	private int findAccount(String accountName) {
		List<Account> accounts = person.getAccounts();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountName().equals(accountName)) {
				return i;
			}
		}
		return -1;
	}
	
	
}
