package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Auth {
	final static String BASE_PATH = "./data/";
	final static String PASSWORD_NAME = "/password.txt";
	final static String FIRSTNAME_NAME = "/firstname.txt";
	final static String LASTNAME_NAME = "/lastname.txt";
	final static String BALANCE_NAME = "/balance.txt";
	final static String TRANSACTION_NAME = "/transactions.csv";

	/**
	 * Creates a directory containing .txt files to represent a Person instance.
	 * 
	 * @param id
	 *            -- user ID used to login. should be unique.
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @return true if directory and all files were created with correct contents.
	 *         False otherwise
	 */
	public static boolean register(String id, String password, String firstName, String lastName) {
		boolean isSuccessful = false;
		String userDirectoryPath = BASE_PATH + id;

		try {
			File userDirectory = new File(userDirectoryPath);
			if (userDirectory.mkdir()) {
				String passwordPath = userDirectoryPath + PASSWORD_NAME;
				File passwordFile = new File(passwordPath);
				passwordFile.createNewFile();
				FileWriter passwordWriter = new FileWriter(passwordPath);
				passwordWriter.append(password);
				passwordWriter.close();

				String firstnamePath = userDirectoryPath + FIRSTNAME_NAME;
				File firstnameFile = new File(firstnamePath);
				firstnameFile.createNewFile();
				FileWriter firstnameWriter = new FileWriter(firstnamePath);
				firstnameWriter.append(firstName);
				firstnameWriter.close();

				String lastnamePath = userDirectoryPath + LASTNAME_NAME;
				File lastnameFile = new File(lastnamePath);
				lastnameFile.createNewFile();
				FileWriter lastnameWriter = new FileWriter(lastnamePath);
				lastnameWriter.append(lastName);
				lastnameWriter.close();

				isSuccessful = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isSuccessful;
	}

	/**
	 * Called when a user attempts to login. Returns true if credentials are
	 * correct, false otherwise.
	 * 
	 * @param idGuess
	 *            - userId provided by the user
	 * @param passwordGuess
	 *            - password provided by the user
	 * @return true if it was a valid login attempt -- the user should progress past
	 *         the login screen. false if credentials were incorrect.
	 */
	public static boolean validateLogin(String idGuess, String passwordGuess) {
		boolean isSuccessful = false;

		String userDirectoryPath = BASE_PATH + idGuess;
		File userDirectory = new File(userDirectoryPath);

		// this checks if the userID was valid -- if it was, there should be a directory
		// in the data directory named the id.
		if (userDirectory.isDirectory()) {
			try {
				Scanner passwordScanner = new Scanner(new File(userDirectoryPath + PASSWORD_NAME));
				while (passwordScanner.hasNextLine()) {
					String password = passwordScanner.nextLine();

					if (password.equals(passwordGuess)) {
						isSuccessful = true;
					}
				}
				passwordScanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return isSuccessful;
	}
	
	/**
	 * Create a Person and their accounts from a directory path
	 * @return null if unable to correctly create a Person. Otherwise, the person specified by the ID.
	 */
	public static Person createPersonFromDirectory(String id) {
		Person personToReturn = null;
		String userDirectoryPath = BASE_PATH + id;
		String firstName = "";
		String lastName = "";
		
		try {
			File userDirectory = new File(userDirectoryPath);
			if (userDirectory.isDirectory()) {
				Scanner firstNameScanner = new Scanner(new File(userDirectoryPath + FIRSTNAME_NAME));
				while (firstNameScanner.hasNextLine()) {
					firstName = firstNameScanner.nextLine();
				}
				firstNameScanner.close();
				
				Scanner lastNameScanner = new Scanner(new File(userDirectoryPath + LASTNAME_NAME));
				while (lastNameScanner.hasNextLine()) {
					lastName = lastNameScanner.nextLine();
				}
				lastNameScanner.close();
				
				//if person exists, finish creating object by adding accounts to accounts ArrayList
				if (!firstName.equals("") && !lastName.equals("")) {
					Person tempPerson = new Person(id, firstName, lastName);
					
					//https://stackoverflow.com/questions/5125242/java-list-only-subdirectories-from-a-directory-not-files/5125258
					File[] accounts = new File(userDirectoryPath).listFiles(File::isDirectory);
					for (File account : accounts) {
						//get balance value of account
						BigDecimal balance = new BigDecimal(0);
						Scanner balanceScanner = new Scanner(new File(account.toString() + BALANCE_NAME));
						if (balanceScanner.hasNextBigDecimal()) {
							balance = balanceScanner.nextBigDecimal();
						}
						balanceScanner.close();
						
						//create transactions array list
						ArrayList<Transaction> transactions = new ArrayList<Transaction>();
						ArrayList<Transaction> tempTransactions = new ArrayList<Transaction>();
						
						Scanner transactionScanner = new Scanner(new File(account.toString() + TRANSACTION_NAME));
						while (transactionScanner.hasNextLine()) {
							Transaction transaction;
							String line = transactionScanner.nextLine();
							
							String[] data = line.split(",");
							if (data.length != 4) {
								System.out.println("error generating transaction history");
								break;
							} else {
								String timestamp = data[0];
								Transaction.transactionTypes transactionType = Transaction.transactionTypes.valueOf(data[1]);
								BigDecimal amount = new BigDecimal(data[2]);
								BigDecimal newBalance= new BigDecimal(data[3]);
								transaction = new Transaction(timestamp, transactionType, amount, newBalance);
							}
							
							tempTransactions.add(transaction);
						}
						transactionScanner.close();
						
						//reverse the transactions so that the most recent appear first
						for (int i = 0; i < tempTransactions.size(); i++) {
							transactions.add(tempTransactions.get(tempTransactions.size() - i - 1));
						}
						
						Account newAccount = new Account(account.getName(), balance, transactions);
						tempPerson.addAccount(newAccount);
					}
					
					personToReturn = tempPerson;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		//this seems kind of bad ... null if not created correctly
		return personToReturn;
	}
}
