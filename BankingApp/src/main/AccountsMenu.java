 package main;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountsMenu extends Menu {
	
	//All available commands used in start function;
	private final String DEPOSIT = "deposit";
	private final String WITHDRAW = "withdraw";
	private final String EXIT = "exit";
	private final String BALANCE = "balance";
	private final String TRANLIST = "transactions";
	private final String TRANSFERUSER = "transferuser";
	
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
		
		System.out.println("Welcome, enter commands to interact with your account, or type help");
		
		//Main loop that handles all user command inputs
		mainLoop: while(true) {
			String userInput = scanner.next().toLowerCase();
			
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
				case TRANLIST:
					transactionListHandler(scanner);
					break;
				case TRANSFERUSER:
					transferUserHandler();
				case EXIT:
					System.out.println("Exiting from " + this.account.getAccountName() + ", returning to home menu");
					break mainLoop; //https://stackoverflow.com/questions/22823395/java-how-can-i-break-a-while-loop-under-a-switch-statement
				default:
					System.out.println("Valid commands are: " + DEPOSIT + ", " + WITHDRAW + ", " + BALANCE + ", " + TRANLIST + ", " + EXIT);
			}
		}
		
	}
	
	/**
	 * 
	 */
	private void transferUserHandler(Scanner scanner) {
		System.out.println("Enter username of user to transfer to (or cancel to quit): ");
		String userInput = scanner.next().toString();
		while(!Transfer.checkIfAccountExists(userInput) && !userInput.equals("cancel")) {
			System.out.println("Error: User not found. Try again or type 'cancel' to exit: ");
			userInput = scanner.next().toString();
		}
		
		if(Transfer.checkIfAccountExists(userInput)) {
			System.out.print("Enter amount to transfer to " + userInput + ": ");
			BigDecimal transferAmount = this.inputBigDecimal(scanner);
			BigDecimal newAccountBalance = this.account.transfer(transferAmount);
		}
		else {
			
		}

		
		System.out.println("Your account now has a balance of: $" + newAccountBalance);
	}
	
	/**
	 * Handles printing the transaction list
	 */
	private void transactionListHandler() {
		this.account.printTransactionList();
		balanceHandler();
	}
	
	/**
	 * Handles the balance command
	 */
	private void balanceHandler() {
		System.out.println("Your current account balance is: $" + this.account.getBalance());
	}

	/**
	 * Handles depositing money into account instance variable
	 * 
	 * @param scanner
	 */
	private void depositHandler(Scanner scanner) {
		System.out.println("Enter amount to deposit:");
		
		BigDecimal depositAmount = this.inputBigDecimal(scanner);
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
		
		BigDecimal withdrawAmount = this.inputBigDecimal(scanner);
		BigDecimal newAccountBalance = this.account.withdraw(withdrawAmount);
		
		System.out.println("Your account now has a balance of: $" + newAccountBalance);
	}
	

	
}
