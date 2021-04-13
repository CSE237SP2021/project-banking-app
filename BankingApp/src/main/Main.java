package main;

import java.util.Scanner;

public class Main {

	//Entry point of program
	public static void main(String[] args) {
		//initialize test person
		Person testPerson = new Person("Default", "User");
		testPerson.addAccount("checking");
		AccountsMenu accountsMenu = new AccountsMenu(testPerson.getAccounts().get(0));
		Scanner scanner = new Scanner(System.in);
		
		accountsMenu.start(scanner);
	}

}