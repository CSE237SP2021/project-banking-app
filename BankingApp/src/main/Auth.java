package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Auth {
	final String BASE_PATH = "./data/";
	final String PASSWORD_NAME = "/password.txt";
	final String FIRSTNAME_NAME = "/firstname.txt";
	final String LASTNAME_NAME = "/lastname.txt";

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
	public boolean register(String id, String password, String firstName, String lastName) {
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
	public boolean validateLogin(String idGuess, String passwordGuess) {
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
}
