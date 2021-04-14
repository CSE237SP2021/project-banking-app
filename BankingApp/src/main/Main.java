package main;

import java.util.Scanner;

public class Main {

	//Entry point of program
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AuthMenu authMenu = new AuthMenu();
		authMenu.start(scanner);
		scanner.close();
	}

}