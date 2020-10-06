package item;

import java.util.Date;

import data.City;
import data.Country;
import utils.Util;

public class Biodata {
	protected String name;
//	private String photo;
	protected char gender;
	protected int age;
	protected Date dob;
	protected String city;
	protected String country;
	protected String placeOfBirth;
	
	public Biodata(String name, char gender, int age, Date dob, String city, String country, String placeOfBirth) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.dob = dob;
		this.city = city;
		this.country = country;
		this.placeOfBirth = placeOfBirth;
	}

	public void describe() {
		System.out.println("BIODATA\n======");
		System.out.println("Name : "+name);
		System.out.println("Gender : "+gender);
		System.out.println("age : "+age);
		System.out.println("dob : "+Util.formatDate.format(dob));
		System.out.println("city : "+city);
		System.out.println("country : "+country);
		System.out.println("place : "+placeOfBirth);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	
}
