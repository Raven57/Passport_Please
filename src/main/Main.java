package main;

import utils.Util;

public class Main {

	private void printMainMenu() {
		System.out.println("Passport_Please");
		System.out.println("1. Traveler");
		System.out.println("2. Immigration Officer");
		System.out.println("3. Exit");
		System.out.print(" >> ");
	}
	void travelerOption() {
		login();
		if(validateLogin("us", "pwd")) {
		int choice  = 0;
		do {
			Util.cls();
			printTravelerMenu();
			try {
				choice = Util.sc.nextInt();
			} catch (Exception e) {
				choice = 0;
			} 
			if(choice<1||choice>3) {
				System.out.println("Please enter correct number!");
				Util.sc.nextLine();
			}
			Util.sc.nextLine();
		} while (choice<1||choice>3);
		
		switch (choice) {
		case 1:
			Util.cls();
			System.out.println("Menu satu");
			Util.sc.nextLine();
			break;
		case 2:
			Util.cls();
			System.out.println("Menu 2");
			Util.sc.nextLine();
			break;
		case 3:
			Util.cls();
			System.out.println("Menu 3");
			Util.sc.nextLine();
			break;
		}
		}else {
			System.out.println("Wrong Credential");
		}
	}
//	private void travelerLogin() {
//		
//	}
	private void printTravelerMenu() {
		//add passport & person info
		System.out.println("Passport_Please\nTraveler Menu");
		System.out.println("1. Update Passport");
		System.out.println("2. Enter Airport");
		System.out.println("3. Back");
		System.out.print(" >> ");
	}
	void login() {
		
	}
	boolean validateLogin(String username, String password) {
		return true;
	}

	public Main() {
		int choice = 0;
		do {
			do {
				Util.cls();
				printMainMenu();
				try {
					choice = Util.sc.nextInt();
				} catch (Exception e) {
					choice = 0;
				} 
				if(choice<1||choice>3) {
					System.out.println("Please enter correct number!");
					Util.sc.nextLine();
				}
				Util.sc.nextLine();
			} while (choice<1||choice>3);
			
			switch (choice) {
			case 1:
				Util.cls();
				System.out.println("Menu satu");
				Util.sc.nextLine();
				break;
			case 2:
				Util.cls();
				System.out.println("Menu 2");
				Util.sc.nextLine();
				break;
			case 3:
				Util.cls();
				System.out.println("Menu 3");
				Util.sc.nextLine();
				break;
			}
		} while (choice!=3);
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
