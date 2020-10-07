package department;

import java.util.ArrayList;
import java.util.Date;

import data.Country;
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
	public void addRandomPerson(boolean correct,int wrongInfo) {
		
		Date dob = null, bioDob = null,expired = null;
		Date curr = new Date();
		Passport p;
		Biodata b;
		String name,city = null,country = null,placeOfBirth = null,bioName = null,bioCity = null,bioCountry = null,bioPlaceOfBirth = null,email = null,password;
		char gender,bioGender='X';
		name = Main.name.getRandom();
		email = createEmail(name);
		password = createPassword(name);
		int patience = Util.randomInt(10, 20);
//		patience = 5;
		int age = 0,bioAge = 0;
		int rand = Util.randomInt(1, 100);
		if(rand%2==0) {
			gender ='F';
		} else {
			gender ='M';
		}
		
		do {
			expired = Util.getRandomDate(2030);
		} while (curr.compareTo(expired)>=0);
		do {
			dob = Util.getRandomDate(2020);
		} while (curr.compareTo(dob)<=0);
		
		do {
			country = Main.country.getRandom();
		} while (!((Country)Main.country).validCountry(country));
		
		do {
			city = Main.city.getRandom();
		} while (!((Country)Main.country).validCityMatch(city, country));
		
		String bornCountry;
		do {
			bornCountry=Main.country.getRandom();
		} while (!((Country)Main.country).validCountry(bornCountry));
		do {				
			placeOfBirth = Main.city.getRandom();
		} while (!((Country)Main.country).validCityMatch(placeOfBirth, bornCountry));
		
			age = Util.getYearDiff(dob, curr);
			bioName=name;
			bioCity=city;
			bioCountry = country;
			bioAge=age;
			bioGender=gender;
			bioDob=dob;
			bioPlaceOfBirth=placeOfBirth;
			
			switch (wrongInfo) {
			case 12:
				do {
					country = Main.country.getRandom();
				}while(((Country)Main.country).validCountry(country));
			case 11:
				do {
					city = Main.city.getRandom();
				}while(((Country)Main.country).validCityMatch(city, country));
			case 10:
				do {
					dob = Util.getRandomDate(2020);
				} while (curr.compareTo(dob)<=0);
			case 9:
				do {
					age = Util.randomInt(1, 100);
				} while (age==Util.getYearDiff(dob, curr));
			case 8:
				do {
					bioPlaceOfBirth=Main.city.getRandom();
				} while (bioPlaceOfBirth==placeOfBirth);
			case 7:
				do {
					bioName = Main.name.getRandom();
				} while (bioName==name);

			case 6:
				do {
					bioDob = Util.getRandomDate(2020);
				} while (bioDob==dob);
			case 5:
				if(gender=='F') {
					bioGender='M';
				}else
					bioGender='F';
			case 4:
				do {
					expired = Util.getRandomDate(2030);
				} while (curr.compareTo(expired)<=0);
			case 3:
				do {
					bioAge = Util.randomInt(1, 100);
				} while (bioAge==age);
			case 2:
				do {
					bioCity = Main.city.getRandom();
				}while(bioCity==city);
			case 1:
				do {
					bioCountry = Main.country.getRandom();
				}while(bioCountry==country);
			default:
				break;
			}
			if(!correct) {
				patience/=2;
			}
		p = new Passport(name, gender, age, dob, city, country, placeOfBirth, expired);
		b = new Biodata(bioName, bioGender, bioAge, bioDob,bioCity, bioCountry, bioPlaceOfBirth);
			
		Person pers = new Person(p, b, email, password, patience,correct);
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
	public ArrayList<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
	public boolean isPeakHour() {
		return peakHour;
	}
	public void setPeakHour(boolean peakHour) {
		this.peakHour = peakHour;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
}
