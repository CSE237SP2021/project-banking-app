package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Auth {
	final String BASE_PATH = "./data/";
	final String PASSWORD_NAME = "/password.txt";
	final String FIRSTNAME_NAME = "/firstname.txt";
	final String LASTNAME_NAME = "/lastname.txt";
	
	/**
	 * Creates a directory containing .txt files to represent a Person instance.
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @return success
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
}
