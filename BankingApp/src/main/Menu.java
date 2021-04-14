package main;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Parent class to all Menu classes -- all Menu classes should extend Menu
 * Contains helper methods
 */
public abstract class Menu {

	/**
	 * Gets input until a valid BigDecimal is input by the user
	 * Test coverage is covered by AccountsMenuTests, as most tests are reliant on this method working correctly anyways.
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
	
	protected String inputAlphanumericString(Scanner scanner) {
		String inputValue = new String("-");
		
		while (!inputValue.matches("^[a-zA-Z0-9]*$")) {
			inputValue = scanner.next();
			if (!inputValue.matches("^[a-zA-Z0-9]*$")) {
				System.out.println("Please enter a valid alphanumeric string.");
			}
		}
		
		return inputValue;
	}

	/**
	 * Entry point for all classes extending Menu
	 * @param _scanner
	 */
	public abstract void start(Scanner _scanner);

}
