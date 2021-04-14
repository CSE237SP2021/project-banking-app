package main;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;

	/**
	 * Person Constructor
	 * 
	 * initialize accounts ArrayList as empty
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

		this.accounts = new ArrayList<Account>();
	}

	/**
	 * creates a new Account object, and adds it to the accounts ArrayList
	 * 
	 * @param accountName
	 *            name for new account to be added
	 * 
	 * @return true if new Account successfully added, false if account with the
	 *         same name is held in accounts
	 */
	public boolean addAccount(String accountName) {
		if (accountName.isBlank())
			return false;

		Account newAccountToAdd = new Account(accountName);

		for (Account acc : this.accounts) {
			if (acc.getAccountName().equals(accountName)) {
				return false;
			}
		}

		accounts.add(newAccountToAdd);

		return true;
	}

	/**
	 * accounts ArrayList getter
	 * 
	 * @return this.accounts
	 */
	public ArrayList<Account> getAccounts() {
		return this.accounts;
	}

	/**
	 * firstName getter
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * lastName getter
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

}
