package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Util {
	private static Random rand = new Random();
	public static Scanner sc = new Scanner(System.in);
	public static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyy");
	public static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
	public static int scanInt(int min, int max) {
		int i = 0;
		try {
			i = Util.sc.nextInt();
		} catch (Exception e) {
			i = 0;
		} 
		if(i<min||i>max) {
			System.out.println("Please enter correct number!");
			Util.sc.nextLine();
		}
		Util.sc.nextLine();
		return i;
	}
	public static String[] splitString(String str, String separator) {
		return str.split(separator);
	}
	public static int randomInt(int min, int max) {
		int n= 0;
		n= rand.nextInt(max)+min;
		return n;
	}
	public static String randomStringFromArrayList(ArrayList<String> strList) {
		return strList.get(randomInt(0, strList.size()));
	}
	public static Date getDateFromString(String str) {
		Date d=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	public static Date getRandomDate() {
		String str;
		int year = randomInt(1920, 2020);
		int month = randomInt(1,12);
		int date;
		int max=0;
		if(month==1) {
			max=30;
		} else if(month==2) {
			if(year%4==0) {
				max=29;
			} else {
				max = 28;
			}
		} else if(month>2&&month<8) {					
			if(month%2!=0) max=31;
			else if (month%2==0) max=30;
		} else if (month>7&&month<13) {
			if(month%2==0) max=31;
			else if (month%2!=0) max=30;
		}
		date = randomInt(1, max);
		str=Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year);
		return getDateFromString(str);
	}
	public static int getYearDiff(Date dateOne, Date dateTwo) {
	 
	    long diffInMillies = Math.abs(dateTwo.getTime() - dateOne.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    return (int) (diff/365);
	}
	public static boolean validateDate(String str[],int max,int min) {
		int date,mon,year;
		if(str.length!=3) return false; //validate format
		try {
			date = Integer.parseInt(str[0]);
			mon = Integer.parseInt(str[1]);
			year = Integer.parseInt(str[2]);
		} catch (NumberFormatException e) {
			return false;
		}
	
		if(str[2].length()>4 || year>max||year<min) return false;
		if(str[1].length()>2) return false;
		if(str[0].length()>2||date>31) return false;
		else {
			if(mon==1&&date>30) return false;
			else if(mon==2) {
				if(year%4==0&&date>29) return false;
				if(year%4!=0&&date>28) return false;
			}
			else if(mon>2&&mon<8) {					
				if(mon%2!=0&&date>31) return false;
				else if (mon%2==0&&date>30) return false;
			}
			else if (mon>7&&mon<13) {
				if(mon%2==0&&date>31) return false;
				else if (mon%2!=0&&date>30) return false;
			}
			return true;
		
		}
	}
}
