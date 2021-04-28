package main;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class HomeMenu extends Menu {

	// All available commands under HomeMenu
	private final String NEW_ACCOUNT = "newaccount";
	private final String CANCEL = "cancel";
	private final String EXIT = "exit";
	private final String SELECT = "selectaccount";
	private final String DELETE = "deleteaccount";
	private final String TRANSFER = "transfer";

	private Person person;

	public HomeMenu(Person person) {
		this.person = person;
	}

	/**
	 * Starts loop that accepts all user input for command execution
	 * 
	 * @param scanner
	 *            (to make unit testing easier)
	 */
	public void start(Scanner scanner) {
		System.out.println("Welcome, " + this.person.getFirstName() + " " + this.person.getLastName() + ". Enter commands (or type help).");

		mainLoop: while (true) {
			String userInput = scanner.next().toLowerCase();

			switch (userInput) {
			case NEW_ACCOUNT:
				newAccountHandler(scanner);
				break;
			case EXIT:
				System.out.println("Thank you for banking with us, goodbye");
				break mainLoop;
			case SELECT:
				selectAccountHandler(scanner);
				break;
			case DELETE:
				deleteAccountHandler(scanner);
				break;
			case TRANSFER:
				transferHandler(scanner);
				break;
			default:
				System.out.println("Command not recognized. Acceptable commands are: " + NEW_ACCOUNT + ", " + SELECT
						+ ", " + DELETE + ", " +TRANSFER+", "+ EXIT);

			}
		}
	}

	/**
	 * Handles add account functionality
	 * 
	 * @param scanner
	 *            (to make unit testing easier)
	 */
	private void newAccountHandler(Scanner scanner) {
		String input;
		boolean success = false;
		boolean error = false;
		do {
			System.out.println(error
					? "Error: unable to create an account with that name, enter a new name or \"cancel\" to cancel"
					: "Please enter a unique name for your new account or \"cancel\" to cancel");

			input = scanner.next();

			if (input.toLowerCase().equals(CANCEL))
				return;

			success = this.person.addAccount(input);
			error = true;

		} while (success == false);

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

			for (Account acc : person.getAccounts()) {
				System.out.print(acc + " ");
			}

			System.out.println("");
			System.out.println("Enter account name to select that account, or type cancel to return");

			input = scanner.next();
			int index = findAccount(input);
			if (input.toLowerCase().equals("cancel")) {
				success = true;
			}
			else if (index == -1) {
				System.out.println("Error, account \"" + input + "\" not found");
			}
			else {
				Account selectedAccount = person.getAccounts().get(index);
				AccountsMenu accountsMenu = new AccountsMenu(selectedAccount);
				accountsMenu.start(scanner);
				success = true;
			}

		} while (success == false);

	}

	/**
	 * 
	 * @param accountName
	 * @return index of account associated with accountName in the person accounts
	 *         list, or -1 if not found
	 */
	private int findAccount(String accountName) {
		List<Account> accounts = person.getAccounts();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountName().equals(accountName)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Handles deleting an account
	 * @param scanner
	 */
	private void deleteAccountHandler(Scanner scanner) {
		String input;
		boolean success = false;
		do {
			System.out.println("Available accounts are: ");

			for (Account acc : person.getAccounts()) {
				System.out.print(acc + " ");
			}

			System.out.println("");
			System.out.println("Enter account name to delete, or type cancel to return");
			
			input = scanner.next();
			int index = findAccount(input);
			if (input.toLowerCase().equals(CANCEL)) {
				success = true;
				return;
			}
			else if (index == -1) {
				System.out.println("Error, account \"" + input + "\" not found");
			}
			else {
				if(person.removeAccount(index, input)) {
					success = true;
				}
				else {
					System.out.println("Error: Account balance must be equal to 0 prior to removing it.");
				}
			}
		} while( success == false);
		
		System.out.println(input + " removed successfully!");
	}
	
	/**
	 * Handles logic for transferring money between accounts
	 * @param scanner
	 */
	private void transferHandler(Scanner scanner) {
		List<Account> accounts = this.person.getAccounts();
		
		if(accounts.size() < 2) {
			System.out.println("Error: Cannot transfer money, less than 2 acounts exist");
			return;
		}
		
		int withdrawAccountIndex = -1;
		String withdrawAccountName;
		int depositAccountIndex = -1;
		String depositAccountName;
		
		while(withdrawAccountIndex == -1) {
			System.out.println("Enter the account name to send money from, or 'cancel' to cancel the transfer:");
			withdrawAccountName = scanner.next();
			
			if(withdrawAccountName.equals(CANCEL)) {
				System.out.println("Transfer cancelled.");
				return;
			}
			
			withdrawAccountIndex = findAccount(withdrawAccountName);
		}
		
		while(depositAccountIndex == -1) {
			System.out.println("Enter the account name to transfer money to, or 'cancel' to cancel the transfer:");
			depositAccountName = scanner.next();
			
			if(depositAccountName.equals(CANCEL)) {
				System.out.println("Transfer cancelled.");
				return;
			}
			
			depositAccountIndex = findAccount(depositAccountName);
		}
		
		Account withdrawAccount = accounts.get(withdrawAccountIndex);
		Account depositAccount = accounts.get(depositAccountIndex);
		
		if(withdrawAccount.getAccountName().equals(depositAccount.getAccountName())) {
			System.out.println("Attempted to transfer money to same account, transfer cancelled");
			return;
		}
		
		BigDecimal transferAmount = new BigDecimal(0.0);
		
		while(transferAmount.doubleValue() <= 0.0) {
			System.out.println("Enter the amount of money to transfer (greater than $0)");
			
			transferAmount = scanner.nextBigDecimal();
			
			//case where transferAmount is > the withdraw account's balance
			if(withdrawAccount.getBalance().compareTo(transferAmount) ==- 1) {
				System.out.println("Error: transfer amount greater than account balance, transfer cancelled");
				return;
			}
			
		}
		
		System.out.println("Transferring $"+ transferAmount.toString() + " from " + withdrawAccount.getAccountName() + " to "
				+ depositAccount.getAccountName());
		
		withdrawAccount.withdraw(transferAmount);
		depositAccount.deposit(transferAmount);
		
		System.out.println("Transfer success");
		
		
		
	}

}
