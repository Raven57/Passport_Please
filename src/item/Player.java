package item;

import java.util.ArrayList;
import java.util.Date;

import item.charm.Charm;
import main.Main;
import utils.Util;

public class Player extends Person{
	private int money;
	private boolean isPlaying = false;
	private PlayerTime pt;
	private ArrayList<Charm> charmList;
//	private Charm activeCharm;
	public ArrayList<Charm> getCharmList() {
		return charmList;
	}
	public void addCharm(Charm c) {
		if(charmList==null) {
			charmList = new ArrayList<>();
		}
		this.charmList.add(c);
	}
	public void printCharm() {
		if(charmList!=null) {			
			for (Charm charm : charmList) {
				for (Charm c : Main.s.getCharmList()) {
					if(charm.getName().equals(c.getName())) {
						charm.setAvailableFrom(c.getAvailableFrom());
						charm.setDescription(c.getDescription());
						charm.setPrice(c.getPrice());
					}
				}
				charm.describe();
			}
		} else {
			System.out.println("Your inventory is empty!");
		}
	}
	public void setCharmList(ArrayList<Charm> charmList) {
		this.charmList = charmList;
	}
//	public Charm getActiveCharm() {
//		return activeCharm;
//	}
//	public void setActiveCharm(Charm activeCharm) {
//		this.activeCharm = activeCharm;
//	}
	//kasi dia inventory
	public Player(Passport passport, Biodata biodata, String email, String password, int money, PlayerTime pt) {
		super(passport, biodata, email, password, 0, true);
		this.money = money;
		this.pt = pt;
	}
	public void printInformation() {
		pt.printTimeAndDate();
		System.out.println("Money : "+money);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public void describe() {
			
		System.out.printf("\n| %-20s ",email);
		System.out.printf("| %-20s ",this.biodata.name);
		System.out.printf("| %-20d ",money);
		System.out.printf("| %-20d ",pt.getDay());
		System.out.printf("| %-20s ",this.biodata.city);
		System.out.printf("| %-20s |",this.biodata.country);
	}
	public void checkMoney() {
		if(money<=0) {
//			Main.stopAllThread();
			Main.endGame();
		}
	}
	public void deductMoney(int amount) {
		setMoney(money-amount);
	}
	public void addMoney(int amount) {
		setMoney(money+amount);
	}
	@Override
	public void run() {
		running.set(true);
        while (running.get()) {
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
         } 
	}


	public PlayerTime getPt() {
		return pt;
	}


	public void setPt(PlayerTime pt) {
		this.pt = pt;
	}
}
