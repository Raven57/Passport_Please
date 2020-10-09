package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import data.Data;
import data.PlayerData;
import data.Shop;
import data.factory.CityFactory;
import data.factory.CountryFactory;
import data.factory.NameFactory;
import data.factory.PlayerDataFactory;
import data.factory.ShopFactory;
import item.Player;
import utils.SortByDay;
import utils.SortByMoney;
import utils.SortByName;
import utils.Util;

public class Main {
    protected final static AtomicBoolean isPlaying = new AtomicBoolean(false);
	public static Data name,city,country;
	public static PlayerData pd;
	public static Player currPlayer;
	static Airport a;
	public static Shop s;
    public static void endGame() {
        Util.saveData(pd.getPlayerList(), "player_data.csv");
        System.out.println("YOU LOSE");
        System.exit(0);
    }
    
	private void loadDataFromFile() {
		try {
			final String filename = "data.csv";
			name = new NameFactory().loadData(filename, ";", 2);
			city = new CityFactory().loadData(filename, ";", 1);
			country = new CountryFactory().loadData(filename, ";", 0);
			pd = new PlayerDataFactory().loadData("player_data.csv", ";");
			//
			s = new ShopFactory().loadData("charm_data.csv", ";");
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
	
	public static void printMainMenu() {
		System.out.println();
		System.out.println("LOBBY");
		System.out.println("=====");
		System.out.println("1. Work");
		System.out.println("2. Market");
		System.out.println("3. View Inventory");
		System.out.println("4. View Officer");
//		System.out.println("5. Sell Item");
		System.out.println("6. Exit");
		System.out.print(" >> ");
	}
	private void menu4() {
		ArrayList<Player> playerList = pd.getPlayerList();
		int chc=0;
		while(chc!=4) {
			Util.cls();
			System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |","EMAIL","NAME","MONEY","DAY COUNT","CITY","COUNTRY");
			System.out.printf("\n|-----------------------------------------------------------------------------------------------------------------------------------------|");
			for (Player p : playerList) {
				p.describe();
			}
			System.out.printf("\n|-----------------------------------------------------------------------------------------------------------------------------------------|\n");
			do {
				System.out.println("1. Sort By Name");
				System.out.println("2. Sort By Money");
				System.out.println("3. Sort By Day Count");
//				System.out.println("2. Sort By Money");
				System.out.println("4. Back to menu");
				System.out.print(" >> ");
				chc = Util.scanInt(1, 4);
			} while (chc<1||chc>4);
			
			if(chc==1) {
				Collections.sort(playerList, new SortByName());
			} else if (chc==2) {
				Collections.sort(playerList, new SortByMoney());
			} else if(chc==3) {
				Collections.sort(playerList, new SortByDay());
			} else if (chc==4) {
				
			}
		}
	}
	void menu5() {
		
	}
	void menu2() {
		int choice = 0;
		int page = 0;
		boolean shop=false;
		do {
			do {
				Util.cls();
				s.showCharms(currPlayer.getPt().getDay(),page);
				System.out.println("Press 1 to go to previous page\nPress 2 to choose a charm from this page\nPress 3 to go to next page\nPress 5 to exit\n >> ");
				choice = Util.scanInt(1, 5);
			} while (choice<1||choice>5);
			if(choice==1) {
				if((page-4)<0) {
					System.out.println("This is first page");
					Util.sc.nextLine();
				} else {
					page-=4;
				}
			} else if (choice==2) {
				shop=true;
				break;
			} else if(choice ==3) {
				if(page+4>s.getSize()) {
					System.out.println("This is last page");
					Util.sc.nextLine();
				}else {
					page+=4;
				}
			} else if(choice==4) {
				System.out.println("INPUT CORRECT COMMAND!");
				Util.sc.nextLine();
			}
		} while (choice!=5);
		if(!shop)
			return;
		else {
			int start = page+1;
			int end = page+4;
			do {
				Util.cls();
				s.showCharms(100, page);
				System.out.print("CHOOSE YOUR POTION! [" + (start) + "-" + (end) + "]\n >> ");
				choice = Util.scanInt(start, end);
			} while (choice<start||choice>end);
			s.getCharmList().get(--choice).buyCharm(currPlayer);
		}
		
	}
	void menu3() {
		currPlayer.printCharm();
		Util.sc.nextLine();
	}
	public Main() {
		a = new Airport();
		loadDataFromFile();
//		currPlayer = a.getP();
		currPlayer = pd.getPlayerList().get(1);
		a.setP(currPlayer);
		if(currPlayer==(null))
			welcome();
		
		int mainMenuChoice = 0;
		while(mainMenuChoice!=6) {
			do {
				Util.cls();
				printMainMenu();
				mainMenuChoice = Util.scanInt(1, 6);
			} while (mainMenuChoice<1||mainMenuChoice>6);
			
			switch (mainMenuChoice) {
			case 1:
				a.play();
				while(a.isPlaying) {					
					a.inputChoice(Util.scanInt(1, 6));
				}
				Util.sc.nextLine();
				break;
			case 2:
				menu2();
				break;
			case 3:
				menu3();
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
				Util.saveData(pd.getPlayerList(), "player_data.csv");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
