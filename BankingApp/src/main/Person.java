package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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
		if (AuthMenu.currentPersonName != null && !AuthMenu.currentPersonName.isBlank()) {
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
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		accounts.add(newAccountToAdd);

		return true;
	}
	
	public boolean addAccount(Account account) {
		return accounts.add(account);
	}
	
	/**
	 * code to traverse folder and delete entries borrowed from https://stackoverflow.com/questions/20281835/how-to-delete-a-folder-with-files-using-java
	 * 
	 * @param index
	 * @param accountName
	 * @return true if account removed successfully, false if not
	 */
	public boolean removeAccount(int index, String accountName) {
		if(this.accounts.get(index) != null) {
			Account accountToRemove = this.accounts.get(index);
			if(accountToRemove.getBalance().compareTo(new BigDecimal(0)) == 0) {	
				String userDirectoryPath = Auth.BASE_PATH + id;
				String accountFolderPath = userDirectoryPath + "/" + accountName;
				
				try {
					File accountFolder = new File(accountFolderPath);
					String[] accountEntries = accountFolder.list();
					for(String s : accountEntries) {
						File currentData = new File(accountFolder.getPath(),s);
						currentData.delete();
					}
					
					accountFolder.delete();
					this.accounts.remove(index);
					return true;
				} catch(Exception e) {
					e.printStackTrace();
					return false;
				}

			}
			else {
				return false; //balance is not zero, cannot remove account
			}
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
