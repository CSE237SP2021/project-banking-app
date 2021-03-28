package main;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountsMenu {
	
	//All available commands used in start function;
	private final String DEPOSIT = "deposit";
	private final String WITHDRAW = "withdraw";
	private final String EXIT = "exit";
	private final String BALANCE = "balance";
	
	
	Account account;
	
	public AccountsMenu(Account account) {
		this.account = account;
	}
	
	/**
	 * Starts loop that accepts all user input for command execution
	 * 
	 * @param scanner (to make unit testing easier)
	 */
	public void start(Scanner scanner) {
		
		System.out.println("Welcome, enter commands to interact with your account");
		
		//Main loop that handles all user command inputs
		mainLoop: while(true) {
			String userInput = scanner.nextLine().toLowerCase();
			
			switch (userInput) {
				case DEPOSIT:
					depositHandler(scanner);
					break;
				case WITHDRAW:
					withdrawHandler(scanner);
					break;
				case BALANCE:
					balanceHandler();
					break;
				case EXIT:
					System.out.println("Thank you for banking with us, goodbye");
					break mainLoop; //https://stackoverflow.com/questions/22823395/java-how-can-i-break-a-while-loop-under-a-switch-statement
				default:
					System.out.println("Error bad command: "+ userInput);
			}
		}
		
	}
	/**
	 * Handles the balance command
	 */
	private void balanceHandler() {
		System.out.println("Your account balance is: $" + this.account.getBalance());
	}

	/**
	 * Handles depositing money into account instance variable
	 * 
	 * @param scanner
	 */
	private void depositHandler(Scanner scanner) {
		System.out.println("Enter amount to deposit:");
		
		BigDecimal depositAmount = scanner.nextBigDecimal();
		BigDecimal newAccountBalance = this.account.deposit(depositAmount);
		
		System.out.println("Your account now has a balance of: $" + newAccountBalance);
	}
	
	/**
	 * Handles withdrawing money from account instance variable
	 * 
	 * @param scanner
	 */
	private void withdrawHandler(Scanner scanner) {
		System.out.println("Enter amount to withdraw:");
		
		BigDecimal withdrawAmount = scanner.nextBigDecimal();
		BigDecimal newAccountBalance = this.account.withdraw(withdrawAmount);
		
		System.out.println("Your account now has a balance of: $" + newAccountBalance);
	}
}
