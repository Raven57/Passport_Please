package utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import item.PlayerTime;
import item.charm.Charm;
import item.charm.money.BookOfTolerance;
import item.charm.money.FourLeafClover;
import item.charm.money.NoPenalty;
import item.charm.productivity.BePatience;
import item.charm.productivity.Cheat;
import item.charm.productivity.LessPatience;
import item.charm.time.HappyHour;
import item.charm.time.TimeStone;
import item.charm.weird.DayDreaming;
import item.charm.weird.FanaticFan;
import item.charm.weird.LovePotion;
import item.Biodata;
import item.Person;
import item.Player;
import main.Airport;
import main.Main;

public class Util {
	private static Random rand = new Random();
	public static Scanner sc = new Scanner(System.in);
	public static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyy");
	public static boolean cheatMode=false;
	public static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
//	public static Charm cloneCharm(Charm c) {
//		Charm x = c;
//		return x;
//	}
	public static void printPerson(ArrayList<Person> personList) {
		int num = 5;
		while(personList.size()<num) {
			num--;
		}
		if(personList.size()==0) {
//			System.out.println("WAW HABIS");
			return;
		}
		for (int i = 0; i < num; i++) {
			Person p = personList.get(i);
			if(!p.getRunning().get()) {
				p.start();
			}
		}
//		for (Person person : personList) {
//			if(!person.getRunning().get()) {				
//				person.start();
//			}
//		}
		if(Airport.constraint!=null) {
			System.out.println("\nDO NOT ALLOW PERSON WITH THE FOLLOWING INFORMATION:");
			Airport.constraint.describe();
		}
		System.out.printf("|------------------------------------------------------------------------------------------------------------------------------------|\n");
		
		if(cheatMode) {
			System.out.printf("| Correct        |");
			for (int i = 0; i < num; i++) {
				System.out.printf("| %-20b ",personList.get(i).isCorrect());
			}
			System.out.println("|");
		}
		System.out.printf("| Patience       |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20d ",personList.get(i).getPatience());
		}
		System.out.println("|");
		System.out.printf("|------------------------------------------------------------------------------------------------------------------------------------|\n");
		System.out.printf("|    Biodata     |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20d ",i+1);
		}
		System.out.println("|");
		System.out.printf("|----------------||----------------------|----------------------|----------------------|----------------------|----------------------|\n");
		System.out.printf("| Name           |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getBiodata().getName());
		}
		System.out.println("|");
		System.out.printf("| Gender         |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20c ",personList.get(i).getBiodata().getGender());
		}
		System.out.println("|");		
		System.out.printf("| Age            |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20d ",personList.get(i).getBiodata().getAge());
		}
		System.out.println("|");
		System.out.printf("| Date of Birth  |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",Util.formatDate.format(personList.get(i).getBiodata().getDob()));
		}
		System.out.println("|");		
		System.out.printf("| Issuing City   |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getBiodata().getCity());
		}
		System.out.println("|");			
		System.out.printf("| Issuing Country|");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getBiodata().getCountry());
		}
		System.out.println("|");				
		System.out.printf("| Place of birth |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getBiodata().getPlaceOfBirth());
		}
		System.out.println("|");			
		System.out.printf("|----------------||----------------------|----------------------|----------------------|----------------------|----------------------|\n");
		System.out.printf("|    Passport    |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20d ",i+1);
		}
		System.out.println("|");		
		System.out.printf("|----------------||----------------------|----------------------|----------------------|----------------------|----------------------|\n");
		System.out.printf("| Name           |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getPassport().getName());
		}
		System.out.println("|");
		System.out.printf("| Gender         |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20c ",personList.get(i).getPassport().getGender());
		}
		System.out.println("|");		
		System.out.printf("| Age            |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20d ",personList.get(i).getPassport().getAge());
		}
		System.out.println("|");
		System.out.printf("| Date of Birth  |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",Util.formatDate.format(personList.get(i).getPassport().getDob()));
		}
		System.out.println("|");		
		System.out.printf("| Issuing City   |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getPassport().getCity());
		}
		System.out.println("|");			
		System.out.printf("| Issuing Country|");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getPassport().getCountry());
		}
		System.out.println("|");				
		System.out.printf("| Place of birth |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",personList.get(i).getPassport().getPlaceOfBirth());
		}
		System.out.println("|");			
		System.out.printf("| Expired        |");
		for (int i = 0; i < num; i++) {
			System.out.printf("| %-20s ",Util.formatDate.format(personList.get(i).getPassport().getExpiredDate()));
		}
		System.out.println("|");	
		System.out.printf("|------------------------------------------------------------------------------------------------------------------------------------|\n");
		System.out.print("ONLY ALLOW PERSON WITH CORRECT INFORMATION [press 6 to exit]\n >> ");
	}
//	public PlayerTime getTimeFromString(String time) {
//		int hour, minute,day;
//		String []str = time.split(":");
//		hour = Integer.parseInt(str[0]);
//		minute = Integer.parseInt(str[1]);
//		day = Integer.parseInt(str[2]);
//		return new PlayerTime(hour, minute,day);
//	}
	public static void saveData(ArrayList<Player> playerList, String filename) {
		String [] data = playerListToStringArr(playerList);
		try {
			FormattedFileWriter fw = new FormattedFileWriter(filename);
			fw.writeDataToFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void load(int i) {
//		do {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			i--;
//		} while (i>0);
//	}
	public static String[] playerListToStringArr(ArrayList<Player> playerList) {
//		String [] str = null;
		String [] str = new String [100];
		for (int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			str[i]=convertPlayerToString(p);
		}
		return str;
	}
	public static String convertPlayerToString(Player p) {
		Biodata b = p.getBiodata();
		PlayerTime pt = p.getPt();
		String charmList = "";
		if(p.getCharmList()!=null) {
			for (Charm c : p.getCharmList()) {
				charmList = charmList.concat(";").concat(c.getName()).concat(";").concat(Integer.toString(c.getQty()));
			}
		}
		return p.getEmail()+";"+p.getPassword()+";"+p.getMoney()+";"+
	formatDate.format(pt.getLastDate())+";"+b.getName()+";"+b.getGender()+
	";"+b.getAge()+";"+formatDate.format(b.getDob())+";"+b.getCity()+";"+
	b.getCountry()+";"+b.getPlaceOfBirth()+";"+formatDate.format(p.getPassport().getExpiredDate())+";"+p.getPt().getStringTime()+";"+pt.getDay()+charmList;
	}
    public static Date addYear(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }
    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }
	public static int scanInt(int min, int max) {
		int i = 0;
		try {
			i = sc.nextInt();
		} catch (Exception e) {
			i = 0;
		} 
		if(i<min||i>max) {
			System.out.println("Please enter correct number!");
			sc.nextLine();
			sc.nextLine();
			return 0;
		}
		sc.nextLine();
		return i;
	}
	public static String[] splitString(String str, String separator) {
		return str.split(separator);
	}
	public static int randomInt(int min, int max) {
		int n= 0;
		int dif = max-min;
		n= rand.nextInt(dif)+min;
//		System.out.println(n);
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
	public static Date getRandomDate(int maxYear) {
		String str;
		int year = randomInt(1920, maxYear);
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
	public static Charm cloneAndDeduct(Charm x, int qty) {
		String name = x.getName();
		x.deductQty(qty);
		if(x.getQty()==0)
			Main.currPlayer.getCharmList().remove(x);
		Charm c=null;
		switch (name) {
		case "Love Potion":
			c = new LovePotion(name,qty);
			break;
		case "Fanatic Fan":
			c = new FanaticFan(name,qty);
			break;
		case "Day Dreaming":
			c = new DayDreaming(name,qty);
			break;
		case "Be patience":
			c = new BePatience(name,qty);
			break;
		case "Less patience":
			c = new LessPatience(name,qty);
			break;
		case "Cheat":
			c = new Cheat(name,qty);
			break;
		case "Four leaf clover":
			c = new FourLeafClover(name,qty);
			break;
		case "Book of tolerance":
			c = new BookOfTolerance(name,qty);
			break;
		case "No penalty":
			c = new NoPenalty(name,qty);
			break;
		case "Time stone":
			c = new TimeStone(name,qty);
			break;
		case "Happy hour":
			c = new HappyHour(name,qty);
			break;
		}
		return c;
	}
	public static Charm clonecharm(Charm x) {
		String name = x.getName();
		int qty = x.getQty();
//		x.setQty(0);
		Charm c=null;
		switch (name) {
		case "Love Potion":
			c = new LovePotion(name,qty);
			break;
		case "Fanatic Fan":
			c = new FanaticFan(name,qty);
			break;
		case "Day Dreaming":
			c = new DayDreaming(name,qty);
			break;
		case "Be patience":
			c = new BePatience(name,qty);
			break;
		case "Less patience":
			c = new LessPatience(name,qty);
			break;
		case "Cheat":
			c = new Cheat(name,qty);
			break;
		case "Four leaf clover":
			c = new FourLeafClover(name,qty);
			break;
		case "Book of tolerance":
			c = new BookOfTolerance(name,qty);
			break;
		case "No penalty":
			c = new NoPenalty(name,qty);
			break;
		case "Time stone":
			c = new TimeStone(name,qty);
			break;
		case "Happy hour":
			c = new HappyHour(name,qty);
			break;
		}
		return c;
	}
}
