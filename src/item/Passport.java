package item;

import java.util.Date;

import utils.Util;

public class Passport extends Biodata{
	public Passport(String name, char gender, int age, Date dob, String city, String country, String placeOfBirth, Date expired) {
		super(name, gender, age, dob, city, country, placeOfBirth);
		this.expiredDate=expired;
	}

	private Date expiredDate;

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public void describe() {
		System.out.println("Passport\n======");
		System.out.println("Name : "+name);
		System.out.println("Gender : "+gender);
		System.out.println("age : "+age);
		System.out.println("dob : "+Util.formatDate.format(dob));
		System.out.println("city : "+city);
		System.out.println("country : "+country);
		System.out.println("place : "+placeOfBirth);
		System.out.println("Expired : "+Util.formatDate.format(expiredDate));
		System.out.println("\n\n\n");
	}
}
