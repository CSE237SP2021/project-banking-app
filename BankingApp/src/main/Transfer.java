package main;

import java.io.File;
import java.math.BigDecimal;

public class Transfer {
	
	public static boolean checkIfAccountExists(String accountName) {
		String userDirectoryPath = Auth.BASE_PATH + accountName;
		
		File userDirectory = new File(userDirectoryPath);
		if(userDirectory.isDirectory()) {
			return true;
		}
		else { //user directory not found
			return false;
		}
	}

	public static boolean transferToUser(BigDecimal transferAmount, String account) {
		if(checkIfAccountExists(account)) {
			
		}
		
		return true;
	}
}
