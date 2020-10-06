package main;

import java.io.IOException;
import java.util.ArrayList;
import data.Data;
import data.factory.CityFactory;
import data.factory.NameFactory;
import department.Queue;
import item.Person;
import utils.Util;

public class Main {
	ArrayList<Person> personList = new ArrayList<>();
	public static Data name,city,country;
	private void loadDataFromFile() {
		try {
			final String filename = "data.csv";
			name = new NameFactory().loadData(filename, ";", 2);
			city = new CityFactory().loadData(filename, ";", 1);
			country = new CityFactory().loadData(filename, ";", 0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	private void printMainMenu() {
		System.out.println("Passport_Please");
		System.out.println("1. Traveler");
		System.out.println("2. Immigration Officer");
		System.out.println("3. Exit");
		System.out.print(" >> ");
	}
	void travelerOption() {
		if(validateLogin("us", "pwd")) {
		int choice  = 0;
		do {
			Util.cls();
			printTravelerMenu();
			choice = Util.scanInt(1, 3);
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
	private void printTravelerMenu() {
		//add passport & person info
		System.out.println("Passport_Please\nTraveler Menu");
		System.out.println("1. Update Passport");
		System.out.println("2. Enter Airport");
		System.out.println("3. Back");
		System.out.print(" >> ");
	}
	void login() {
		System.out.println("Press R and [Enter] to Register!");
		System.out.print("Please enter email : ");
		String email = Util.sc.nextLine();
		if(email.equalsIgnoreCase("R")) {
			register();
		} else {			
			System.out.print("Please enter password : ");
			String pwd = Util.sc.nextLine();
			//kasi validate
		}
	}
	void register() {
		String name,dob,placeOfBirth,gender;
		int age;
		do {
			System.out.print("Enter your name [5-20 characters] : ");
			name = Util.sc.nextLine();
		} while (name.length()>20||name.length()<5);
		do {			
			System.out.print("Enter your date of birth [dd-mm-yyyy] : ");
			dob = Util.sc.nextLine();
		} while (!Util.validateDate(Util.splitString(dob, "-"), 2020, 1920));
		do {			
			System.out.print("Enter your gender [F/M] : ");
			gender = Util.sc.nextLine();
		} while (!gender.equalsIgnoreCase("F")&&!gender.equalsIgnoreCase("M"));
		do {
			System.out.print("Enter your place of birth  : ");
			placeOfBirth= Util.sc.nextLine();
		} while (placeOfBirth.matches(".*\\d.*"));
		do {
			System.out.print("Enter your age [can't be 0 or lower] : ");
			age= Util.sc.nextInt();
			Util.sc.nextLine();
		} while (age<=0);
		//kasi add data
		System.out.printf("Your email is %s1@mail.com",name);
		System.out.printf("Your password is %s123",name);
	}
	boolean validateLogin(String username, String password) {
		return true;
	}
	public Main() {
		int choice = 0;
		loadDataFromFile();
		Queue q = new Queue(1);
		for (int i = 0; i < 10; i++) {
			if(i%2==0) {
				q.addRandomPerson(true, i);
			}else {
				q.addRandomPerson(false, i);
			}
		}
		do {
			do {
				Util.cls();
				printMainMenu();
				q.describe();
				choice = Util.scanInt(1, 3);
			} while (choice<1||choice>3);
			
			switch (choice) {
			case 1:
				Util.cls();
				login();
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
