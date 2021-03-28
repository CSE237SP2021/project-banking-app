package main;

import java.util.Scanner;

public class Main {

	//Entry point of program
	public static void main(String[] args) {
		Account testAccount = new Account();
		
		AccountsMenu accountsMenu = new AccountsMenu(testAccount);
		
		Scanner scanner = new Scanner(System.in);
		
		accountsMenu.start(scanner);
	}

}