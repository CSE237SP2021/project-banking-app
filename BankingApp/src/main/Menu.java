package main;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

	/**
	 * Gets input until a valid BigDecimal is input by the user
	 * @param scanner
	 * @return valid BigDecimal input
	 */
	protected BigDecimal inputBigDecimal(Scanner scanner) {
		String inputValue = new String();
		
		while (!scanner.hasNextBigDecimal()) {
			inputValue = scanner.next();
			System.out.println("Please enter a valid decimal value.");	
		}
		return scanner.nextBigDecimal();
	}
}
