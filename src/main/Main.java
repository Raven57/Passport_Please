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
import item.charm.ActiveCharm;
import item.charm.ActiveCharmBuilder;
import item.charm.Charm;
import item.charm.money.MoneyCharm;
import item.charm.productivity.ProductivityCharm;
import item.charm.time.TimeCharm;
import item.charm.weird.WeirdCharm;
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
			s = new ShopFactory().loadData("charm_data.csv", ";");
			name = new NameFactory().loadData(filename, ";", 2);
			city = new CityFactory().loadData(filename, ";", 1);
			country = new CountryFactory().loadData(filename, ";", 0);
			pd = new PlayerDataFactory().loadData("player_data.csv", ";");
			//
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
		currPlayer.describeLobby();
		System.out.println("=====");
		System.out.println("LOBBY");
		System.out.println("=====");
		System.out.println("1. Work");
		System.out.println("2. Market");
		System.out.println("3. View Inventory");
		System.out.println("4. View Officer");
		System.out.println("5. Exit");
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
		int max = 0;
		int code = 0;
		do {
			do {
				Util.cls();
				s.showCharms(currPlayer.getPt().getDay(),page);
				System.out.println("----MARKET----");
				System.out.println("==============\n   Options    \n==============");
				if((page-4)<0&&page+4>s.getTemp().size()) {
					System.out.println("1. Choose a charm\n"
							+ "2. Exit");
					max =2;
					code=1;
				}
				else if((page-4)>=0&&page+4>s.getTemp().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Previous page");
					System.out.println("3. Exit");
					max=3;
					code =2;
				} 
				else if ((page-4)>=0&&page+4<s.getTemp().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Previous page");
					System.out.println("3. Next page");
					System.out.println("4. Exit");
					max=4;
					code =3;
				}
				else if ((page-4)<0&&page+4<s.getTemp().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Next page");
					System.out.println("3. Exit");
					max=3;
					code =4;
				}
				else {
					System.out.println("WTF");
					Util.sc.nextLine();
				}
				System.out.print(" >> ");
				choice = Util.scanInt(1, max);
			} while (choice<1||choice>max);
			switch (code) {
			case 2:
				if(choice==2) {
					page-=4;
				}
				break;
			case 3:
				if(choice==2) {
					page-=4;
				}
				else if(choice==3) {
					page+=4;
				}
				break;
			case 4:
				if(choice==2) {
					page+=4;
				}
				break;
			}
			if(choice==1) {
				shop=true;
				break;
			}
		} while (choice!=max);
		if(!shop)
			return;
		else {
			int start = 1;
			do {
				s.createTemp2(page);
				int end = s.getTemp2().size();
				do {
					Util.cls();
					s.chooseCharm();
					System.out.print("CHOOSE YOUR POTION! [" + (start) + "-" + (end) + "]\nPress 5 to exit\n >> ");
					
					choice = Util.scanInt(start, 5);
				} while (choice < start || choice > 5);
				if (choice > end) {
					if(choice==5) {
						return;
					}
					System.out.println("Please choose available options");
					Util.sc.nextLine();
				}
				else {
					s.getTemp2().get(--choice).buyCharm(currPlayer);
					return;
				} 
			} while (choice!=5);
		}
		
	}
	void menu3() {
		int choice = 0;
		int page = 0;
		boolean chosen=false;
		boolean notEmpty = true;
		int max=0,code=0;
		if(currPlayer.getCharmList()==null||currPlayer.getCharmList().isEmpty()) {
			Util.cls();
			System.out.println("YOUR INVENTORY IS EMPTY!");
			Util.sc.nextLine();
			return;
		}
		do {
			do {
				Util.cls();
				currPlayer.printCharm(page);
				System.out.println("---INVENTORY---");
				System.out.println("==============\n    Options    \n==============");

//					if((page-4)>=0) {
//						System.out.println("1. Previous page");
//					}
//					System.out.println("2. Choose a charm");
//					if(page+4<currPlayer.getCharmList().size()) {
//						System.out.println("3. Next page");
//					}
//					System.out.print("4. Exit\n >> ");			
				if((page-4)<0&&page+4>currPlayer.getCharmList().size()) {
					System.out.println("1. Choose a charm\n"
							+ "2. Exit");
					max =2;
					code=1;
				}
				else if((page-4)>=0&&page+4>currPlayer.getCharmList().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Previous page");
					System.out.println("3. Exit");
					max=3;
					code =2;
				} 
				else if ((page-4)>=0&&page+4<currPlayer.getCharmList().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Previous page");
					System.out.println("3. Next page");
					System.out.println("4. Exit");
					max=4;
					code =3;
				}
				else if ((page-4)<0&&page+4<currPlayer.getCharmList().size()) {
					System.out.println("1. Choose a charm");
					System.out.println("2. Next page");
					System.out.println("3. Exit");
					max=3;
					code =4;
				}
				else {
					System.out.println("WTF");
					Util.sc.nextLine();
				}
				System.out.print(" >> ");
					choice = Util.scanInt(1, max);
			} while (choice<1||choice>max);
			switch (code) {
			case 2:
				if(choice==2) {
					page-=4;
				}
				break;
			case 3:
				if(choice==2) {
					page-=4;
				}
				else if(choice==3) {
					page+=4;
				}
				break;
			case 4:
				if(choice==2) {
					page+=4;
				}
				break;
			}
			if(choice==1) {
				chosen=true;
				break;
			}
//			if(choice==1) {
//				if((page-4)<0) {
//					System.out.println("This is first page");
//					Util.sc.nextLine();
//				} else {
//					page-=4;
//				}
//			} else if (choice==2) {
//				chosen=true;
//				break;
//			} else if(choice ==3) {
//				if(page+4>=currPlayer.getCharmList().size()) {
//					System.out.println("This is last page");
//					Util.sc.nextLine();
//				}else {
//					page+=4;
//				}
//			}
		} while (choice!=max);
		if(!chosen)
			return;
		else {
			int start = 1;
			do {
				currPlayer.createTemp2();
				int end = currPlayer.getTemp2().size();
				do {
					Util.cls();
					currPlayer.chooseCharm();
					System.out.print("CHOOSE YOUR POTION! [" + (start) + "-" + (end) + "]\nPress 5 to exit\n >> ");
					choice = Util.scanInt(start, 5);
				} while (choice < start || choice > 5);
				if (choice > end) {
					if(choice==5) {
						return;
					}
					System.out.println("Please choose available options");
					Util.sc.nextLine();
				}
				else {
					Charm c = currPlayer.getCharm(--choice);
					
					do {
//						c.describe();
						System.out.println("======\nOptions\n======");
						System.out.print("1. Activate\n" + "2. Sell\n" + "3. Back" + "\n >> ");
						choice = Util.scanInt(1, 3);
					} while (choice < 1 || choice > 3);
					if (choice == 1 || choice == 2) {
						int qty = 0;
						do {
							System.out.printf("Input qty [1-%d] : ", c.getQty());
							qty = Util.scanInt(1, c.getQty());
						} while (qty < 1 || qty > c.getQty());
						if (choice == 1) {
							ActiveCharm ac = currPlayer.getActiveCharm();
							if(c instanceof MoneyCharm) ac.setMc(c,qty);
							else if (c instanceof WeirdCharm) ac.setWc(c,qty);
							else if (c instanceof ProductivityCharm) ac.setPc(c,qty);
							else if (c instanceof TimeCharm) ac.setTc(c,qty);
							return;
						} else {
							currPlayer.sellCharm(c, qty);
							return;
						}
					}
				}
			} while (choice!=5);
		}
	}
	public Main() {
		a = new Airport();
		loadDataFromFile();
		currPlayer = a.getP();
//		currPlayer = pd.getPlayerList().get(1);
//		a.setP(currPlayer);
		if(currPlayer==(null))
			welcome();
		
		int mainMenuChoice = 0;
		while(mainMenuChoice!=5) {
			do {
				Util.cls();
				printMainMenu();
				mainMenuChoice = Util.scanInt(1, 5);
			} while (mainMenuChoice<1||mainMenuChoice>5);
			
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
