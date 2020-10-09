package main;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import data.City;
import data.Country;
import item.Biodata;
import item.Passport;
import item.Person;
import item.Player;
import item.PlayerTime;
import utils.Util;

public class Airport implements Runnable{
	protected Thread worker;
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected int interval=1000;
	private Player p=null;
	public static Biodata constraint;
	public static Queue q;
	private int money;
	protected boolean isPlaying;
	public static Queue getQ() {
		return q;
	}
	public static void setQ(Queue q) {
		Airport.q = q;
	}
	public AtomicBoolean getRunning() {
		return running;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	private Biodata createConstraint(int limit) {
		String name=null,city=null,country=null,placeOfBirth=null;
		char gender='X';
		int age=0;
		Date dob=null;
		switch (limit) {
			case 6:
				int ran = Util.randomInt(1, 10);
				if(ran%2==0)
					gender='F';
				else gender ='M';
			case 5:
				do {
					dob = Util.getRandomDate(2000);
				} while (p.getPt().getLastDate().compareTo(dob)<=0);
			case 4:
				do {
					placeOfBirth = Main.city.getRandom();
				} while (!((City)Main.city).validCity(city));
			case 3:
				do {
					country = Main.country.getRandom();
				} while (!((Country)Main.country).validCountry(country));			
			case 2:
				if(limit<=2) {
					do {
						city = Main.city.getRandom();
					} while (!((City)Main.city).validCity(city));
				}else {
					do {
						city = Main.city.getRandom();
					} while (!((Country)Main.country).validCityMatch(city, country));
				}
			case 1:
				name=Main.name.getRandom();
			default:
				break;
		}
		
		return new Biodata(name, gender, age, dob, city, country, placeOfBirth);
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
	void initializeQueue(int day) {
		//create constraint
		q = new Queue(1);
		int numberOfPeople=20;
		money = 100;
		int temp = day;
		while(temp>1) {			
			numberOfPeople*=13;
			numberOfPeople/=10;
			temp--;
			money*=3;
			money/=2;
		}
		int randomer = 5;
		if(Util.randomInt(1, 10)%2==0) {				
			if(randomer>2) {				
				randomer--;
			}
		}
		for (int i = 0; i < numberOfPeople; i++) {
			boolean correct = true;
			int wrongInfo=0;
			if(Util.randomInt(1, 100)%randomer==0) {
				correct=false;
			}
			if(correct==false) {
				System.out.println(day);
				wrongInfo = Util.randomInt(1, 1+day);
			}
			q.addRandomPerson(correct, wrongInfo);
		}
		constraint = createConstraint(Util.randomInt(1, day+1));
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
		PlayerTime pt = new PlayerTime(0, 0, 1, curr);
		return new Player(p, b, email, pass, 100, pt);
	}
	public void play() {
		if(running.get()==false||worker==null) {
			this.start();
			isPlaying=true;
		}
	}
	@Override
	public void run() {
		running.set(true);
		int day = p.getPt().getDay();
		int sumOfPpl=1;
        while (running.get()) {

        	if(q==null||day!=p.getPt().getDay()) {
        		if(q!=null) {        			
        			p.deductMoney(q.getPersonList().size()*15);
        		}
        		day = p.getPt().getDay();
        		initializeQueue(day);
        		sumOfPpl = q.getPersonList().size();
        	}
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
			p.checkMoney();
			Util.cls();
			p.printInformation();		
					Util.printPerson(q.getPersonList());
					System.out.println("Sum : "+sumOfPpl);
		        	if(!isPlaying) {
		        		System.out.println("Please press enter once again to exit");
		        	}
					if(q.getPersonList().isEmpty()) {
						p.addMoney(sumOfPpl*10);
						p.getPt().addDate(true);
					}
				p.getPt().addTime();
         } 
	}
	public void start() { 	
        worker = new Thread(this);
        worker.start();
    }
    public void stop() {
    	running.set(false);
    }
	public void inputChoice(int idx) {
		if(idx<1||idx>5) {
			if(idx==6) {
				isPlaying=false;
				this.stop();
			}
			return;
		}
		idx--;
		Person p = q.getPersonList().get(idx);
		
		//validate
		if(p.checkValid(constraint)) {			
			this.p.addMoney(money);;
		}
		else {
			this.p.deductMoney(money*2);
		}
		p.stop();
		q.getPersonList().remove(p);
	}
}
