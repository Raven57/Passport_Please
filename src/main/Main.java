package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import data.Data;
import data.PlayerData;
import data.factory.CityFactory;
import data.factory.CountryFactory;
import data.factory.NameFactory;
import data.factory.PlayerDataFactory;
import department.Queue;
import item.Player;
import utils.Util;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	public static int maxHOUR = 24;
    protected final static AtomicBoolean isPlaying = new AtomicBoolean(false);
	public static Data name,city,country;
	public static PlayerData pd;
	public static Player currPlayer;
	public static int H=0;
	public static int M=0;
	static Airport a;
	public static Getter get;
	public static int choice=0,min=1,max=6;
	public static Queue q;
	public static boolean justEnteredAMenu = false;
    public static void endGame() {
        stopAllThread();
        Util.saveData(pd.getPlayerList(), "player_data.csv");
        System.out.println("YOU LOSE");
        System.exit(0);
    }
    private static void restart()
    {
        
        get = new Getter();
        get.start();
    }
	private void loadDataFromFile() {
		try {
			final String filename = "data.csv";
			name = new NameFactory().loadData(filename, ";", 2);
			city = new CityFactory().loadData(filename, ";", 1);
			country = new CountryFactory().loadData(filename, ";", 0);
			pd = new PlayerDataFactory().loadData("player_data.csv", ";");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	private void welcome() {
		boolean b;
		do {
			Util.cls();
			System.out.println("Welcome to Passport_Please");
			b = a.login();
			currPlayer = a.getP();
		} while (!b);
	}
	public static int day=1;
	public static int numberOfPeople = 1;

	protected static void initializeQueue() {
		q = new Queue(1);
		
		numberOfPeople*=13;
		numberOfPeople/=10;
		if(day==1) {
			numberOfPeople=20;
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
				wrongInfo = Util.randomInt(1, 1+day);
			}
			q.addRandomPerson(correct, wrongInfo);
		}
	}
	public static void printTime() {
		if(currPlayer!=null) {
			String h,m;
			if(H<10) {
				h="0"+Integer.toString(H);
			} else {
				h=Integer.toString(H);
			}
			if(M<10) {
				m="0"+Integer.toString(M);
			} else {
				m = Integer.toString(M);
			}
			System.out.println(Util.formatDate.format(currPlayer.getLastDate()));
			System.out.printf("%s:%s\n",h,m);
			System.out.printf("Money : %d\n",currPlayer.getMoney());
		}
	}
	public static void printMainMenu() {
		System.out.println();
		System.out.println("LOBBY");
		System.out.println("=====");
		System.out.println("1. Travel");
		System.out.println("2. Work");
		System.out.println("3. Update Passport");
		System.out.println("4. See Scoreboard");
		System.out.println("5. Sign Out");
//		System.out.println("6. See Database");
		System.out.println("6. Exit");
		System.out.print(" >> ");
	}
	public static int menuChoice;
	public static int money =100;
	static Getter2 g2;
	static Thread t = new Thread();
	public static MenuOne menuOne = new MenuOne();
	public static void menu1() {
		
		if(get!=null) {			
			get.stop();
			get = null;
		}
		g2 = new Getter2();
		if(g2.running.get()==false) {
			g2.start();
		}
		if(menuOne!=null) {			
			menuOne.start();
		}
		
		return;
	}

	public static void stopAllThread() {
		if(currPlayer!=null) {			
			currPlayer.stop();
			currPlayer =null;
		}
		if(get!=null) {			
			get.stop();
			get=null;
		}
		if(g2!=null) {
			g2.stop();
			g2=null;
		}
		if(menuOne!=null) {
			menuOne.stop();
			menuOne=null;
		}
	}
	public static void menu4() {
		int chc=0;
		while(chc!=3) {
			Util.cls();
			do {
				System.out.println("1. Previous");
				System.out.println("2. Next");
				System.out.println("3. Back to menu");
				System.out.print(" >> ");
				chc = Util.scanInt(1, 3);
			} while (chc<1||chc>3);
			
			if(chc==1) {
				
			} else if (chc==2) {
				
			} else if(chc==3) {
				
//				break;
			}
		}
	}
	void menu5() {
		
	}
void menu2() {
		
	}
void menu3() {
	
}
	public Main() {
		choice = 0;
		a = new Airport();
		get = new Getter();
		loadDataFromFile();
//		currPlayer = a.getP();
		currPlayer = pd.getPlayerList().get(0);
		a.setP(currPlayer);
		if(currPlayer==(null))
			welcome();
			
//		currPlayer.start();
//		get.start();
		isPlaying.set(true);
		
		int mainMenuChoice = 0;
		do {
			do {
				Util.cls();
				printMainMenu();
				mainMenuChoice = Util.scanInt(1, 6);
			} while (mainMenuChoice<1||mainMenuChoice>6);
			
			switch (mainMenuChoice) {
			case 1:
				Main.menu1();
				break;
			case 2:
				System.out.println("Menu 2");
				Util.sc.nextLine();
				break;
			case 3:
				System.out.println("Menu 3");
				Util.sc.nextLine();
				break;
			case 4:
				menu4();
				break;
			case 5:
				System.out.println("Menu 5");
				Util.sc.nextLine();
				break;
			case 6:
				System.out.println("Menu 6");
				System.exit(0);
				break;
			default:
				break;
			}
		} while (mainMenuChoice!=6);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
