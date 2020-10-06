package department;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import item.Biodata;
import item.Passport;
import item.Person;
import main.Main;
import utils.Util;

public class Queue {
	private ArrayList<Person> personList;
	private boolean peakHour;
	private double percentage;
	
	public Queue(double percentage) {
		this.personList = new ArrayList<>();
		this.peakHour = false;
		this.percentage = percentage;
	}
	public void comparePerson(Person p1, Person p2) {
		
	}
	public void addRandomPerson(boolean correct,int percentage) {
		
		Date dob, expired;
		Date curr = new Date();
		Passport p;
		Biodata b;
		String name,city,country,placeOfBirth,bioName,bioCity,bioCountry,email = null,password;
		char gender,fakeGender='X';
		name = Main.name.getRandom();
		city = Main.city.getRandom();
		country = Main.country.getRandom();
		placeOfBirth = Main.city.getRandom();
		email = createEmail(name);
		password = createPassword(name);
		int patience = Util.randomInt(1, 10);
		int rand = Util.randomInt(1, 100);
		int age;
		if(rand%2==0) {
			gender ='F';
		} else {
			gender ='M';
		}
		
		if(correct) {
			do {
				expired = Util.getRandomDate();
			} while (curr.compareTo(expired)>=0);
			do {
				dob = Util.getRandomDate();
			} while (curr.compareTo(dob)<=0);
			
			bioName=name;
			bioCity=city;
			bioCountry = country;
			age = Util.getYearDiff(dob, new Date());
			
		}else {
			age = Util.randomInt(0, 100);
			expired = Util.getRandomDate();
			dob = Util.getRandomDate();
			bioName=Main.name.getRandom();
			bioCity= Main.city.getRandom();
			bioCountry = Main.country.getRandom();
			if(gender=='F') {
				fakeGender='M';
			} else {
				fakeGender='F';
			}
			
		}
		if(correct) {
			p = new Passport(name, gender, age, dob, city, country, placeOfBirth, expired);
			b = new Biodata(name, gender, age, dob, city, country, placeOfBirth);
			
		}else {
			p = new Passport(name, gender, age, dob, city, country, placeOfBirth, expired);
			b = new Biodata(bioName, fakeGender, age, dob,bioCity, bioCountry, placeOfBirth);
			
		}
		

		Person pers = new Person(p, b, email, password, patience);
		personList.add(pers);
	}
	public String createEmail(String name) {
		String email="";
		for (int i = 0; i < 1000; i++) {
			email = name+Integer.toString(i)+"@mail.com";
			if(emailAvailable(email)) break;
		}
		return email;
	}
	public String createPassword(String name) {
		return name+"123";
	}
	private boolean emailAvailable(String email) {
		for (Person person : personList) {
			if(person.getEmail().equals(email))
				return false;
		}
		return true;
	}
	private void Register(String name, char gender, int age, String dob, String placeOfBirth, String email, String password) {
		
//		Passport p = new Passport(name, gender, age, dob, "Jakarta", "Indonesia", placeOfBirth, expired);
//		Biodata b = new Biodata(name, gender, age, dob, "Jakarta", "Indonesia", placeOfBirth);
//		Person pers = new Person(passport, biodata, email, password, patience);
//		personList.add(pers);
	}
	public void describe() {
		for (Person person : personList) {
			person.describe();
		}
	}
}
