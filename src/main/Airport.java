package main;

import java.util.Date;

import item.Biodata;
import item.Passport;
import item.Player;
import utils.Util;

public class Airport {
	private Player p=null;
	
	public Player getP() {
		return p;
	}


	public void setP(Player p) {
		this.p = p;
	}


	private void printTravelerMenu() {
		//add passport & person info
		System.out.println("Passport_Please\nTraveler Menu");
		System.out.println("1. Update Passport");
		System.out.println("2. Enter Airport");
		System.out.println("3. Back");
		System.out.print(" >> ");
	}
	
	
	protected boolean login() {
		System.out.println("Press R and [Enter] to Register!");
		System.out.print("Please enter email : ");
		String email = Util.sc.nextLine();
		if(email.equalsIgnoreCase("R")) {
			p = register();
			System.out.println("Welcome "+p.getBiodata().getName()+" !!!");
			Main.pd.addToList(p);
			Util.sc.nextLine();
			return true;
		} else {			
			System.out.print("Please enter password : ");
			String pwd = Util.sc.nextLine();
			p = Main.pd.validateCredential(email, pwd);
			if(p==(null)) {
				System.out.println("WRONG CREDENTIAL!");
				Util.sc.nextLine();
				return false;
			} else if (p.getMoney()<=0){
				System.out.println("YOU RAN OUT OF MONEY!\n PLEASE CREATE A NEW ACCOUNT");
				Util.sc.nextLine();
				return false;
			}
			else {
				System.out.println("Welcome "+p.getBiodata().getName()+" !!!");
				Util.sc.nextLine();
				return true;
			}
		}
	}
	
	boolean validateLogin(String username, String password) {
		return true;
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
	private Player register() {
		Date curr = new Date();
		Date expired = Util.addYear(curr, 10);
		String name,dob,placeOfBirth,gender,email;
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
		age = Util.getYearDiff(Util.getDateFromString(dob), curr);
		do {
			System.out.print("Enter your email [must ends with @mail.com] : ");
			email = Util.sc.nextLine();
		} while (!email.endsWith("@mail.com"));
		String pass = name+"123";
		System.out.printf("Your password is %s123\n Press [Enter] to continue",name);
		Util.sc.nextLine();
		Biodata b = new Biodata(name, gender.charAt(0), age, Util.getDateFromString(dob), "Jakarta", "Indonesia", placeOfBirth);
		Passport p = new Passport(name,gender.charAt(0),age,Util.getDateFromString(dob),"Jakarta","Indonesia",placeOfBirth,expired);
		return new Player(p, b, email, pass, 100, Util.getDateFromString("31-12-2020"));
	}
}
