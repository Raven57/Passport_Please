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
	public Biodata(String []str) {
		this.name = str[4];
		this.gender = str[5].charAt(0);
		this.age = Integer.parseInt(str[6]);
		this.dob = Util.getDateFromString(str[7]);
		this.city = str[8];
		this.country = str[9];
		this.placeOfBirth = str[10];
	}
	public void describe() {
		System.out.println("BIODATA\n======");
		if(name!=null) {			
			System.out.printf("| %-20s ","Name");
		}
		if(city!=null) {		
			System.out.printf("| %-20s ","City");
		}
		if(country!=null){
			System.out.printf("| %-20s ","Country");
		}
		if(placeOfBirth!=null){
			System.out.printf("| %-20s ","Place Of Birth");
		}
		if(gender!='X'){
			System.out.printf("| %-20c ","Gender");
		}
		if(age!=0){
			System.out.printf("| %-20d ","Age");
		}
		if(dob!=null){
			System.out.printf("| %-20s ","Date of Birth");
		}
		System.out.println("|");
		if(name!=null) {			
			System.out.printf("| %-20s ",name);
		}
		if(city!=null) {		
			System.out.printf("| %-20s ",city);
		}
		if(country!=null){
			System.out.printf("| %-20s ",country);
		}
		if(placeOfBirth!=null){
			System.out.printf("| %-20s ",placeOfBirth);
		}
		if(gender!='X'){
			System.out.printf("| %-20c ",gender);
		}
		if(age!=0){
			System.out.printf("| %-20d ",age);
		}
		if(dob!=null){
			System.out.printf("| %-20s ",Util.formatDate.format(dob));
		}
		System.out.println("|");
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
