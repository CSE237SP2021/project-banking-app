package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Person {
	private String id;
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
	
	public Person(String userID, String firstName, String lastName) {
		this.id = userID;
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
		
		//create account directory and generate balance and transaction files
		String userDirectoryPath = Auth.BASE_PATH + id;
		String accountPath = userDirectoryPath + "/" + accountName;
		try {
			File accountWriter = new File(accountPath);
			if (accountWriter.mkdir()) {
				String balancePath = accountPath + "/" + "balance.txt";
				File balanceFile = new File(balancePath);
				balanceFile.createNewFile();
				FileWriter balanceWriter = new FileWriter(balanceFile);
				balanceWriter.append("0");
				balanceWriter.close();
				
				String transactionPath = accountPath + "/" + "transactions.csv";
				File transactionFile = new File(transactionPath);
				transactionFile.createNewFile();
				
				accounts.add(newAccountToAdd);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean addAccount(Account account) {
		return accounts.add(account);
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
