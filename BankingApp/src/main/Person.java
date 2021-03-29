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
	 * @return true if new Account successfully added, false if it's not found in the ArrayList after .add() is called
	 */
	public boolean addAccount() {
		Account newAccountToAdd = new Account();
		accounts.add(newAccountToAdd);
		
		if(accounts.contains(newAccountToAdd)) {
			return true;
		}
		else {
			return false;
		}
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
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * lastName getter
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

}
