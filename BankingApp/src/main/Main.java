package main;

import java.util.Scanner;

public class Main {

	//Entry point of program
	public static void main(String[] args) {
		//initialize test person
		Person testPerson = new Person("Default", "User");

		
		Scanner scanner = new Scanner(System.in);
//		HomeMenu homeMenu = new HomeMenu(testPerson);
//		homeMenu.start(scanner);
		AuthMenu authMenu = new AuthMenu();
		authMenu.start(scanner);
	}

}