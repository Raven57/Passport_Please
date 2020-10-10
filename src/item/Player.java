package item;

import java.util.ArrayList;
import java.util.Date;

import item.charm.ActiveCharm;
import item.charm.Charm;
import main.Main;
import utils.Util;

public class Player extends Person{
	private int money;
	private boolean isPlaying = false;
	private PlayerTime pt;
	private ArrayList<Charm> charmList;
	private ArrayList<Charm> temp;
	ArrayList<Charm> temp2;
//	private Charm activeCharm;
	private ActiveCharm activeCharm;
	public ActiveCharm getActiveCharm() {
		if(activeCharm==null) {
			activeCharm = new ActiveCharm();
		}
		return activeCharm;
	}
	public void setActiveCharm(ActiveCharm activeCharm) {
		this.activeCharm = activeCharm;
	}
	public ArrayList<Charm> getCharmList() {
		return charmList;
	}
	public void addCharm(Charm c) {
		if(charmList==null) {
			charmList = new ArrayList<>();
		}
		else {
			boolean exist = false;
			Charm existingCharm = null;
			for (Charm charm : charmList) {
				if(charm.getName().equalsIgnoreCase(c.getName())) {
					exist = true;
					existingCharm=charm;
					break;
				}
			}
			if(exist) {
				existingCharm.addQty(c.getQty());
				return;
			}
		}
		this.charmList.add(c);
	}
	public void sellCharm(Charm c, int qty) {
		if(c.getQty()==qty) {
			if (charmList.remove(c)) {
//				System.out.println("asdasdasdasdsadsadasdasdasda");
//				Util.sc.nextLine();
			}
			temp.remove(c);
//			for (Charm charm : temp) {
//				if(c.getName().equals(charm.getName())) {					
//					temp.remove(charm);
//				}
//			}
		} else {
			c.deductQty(qty);
		}
		addMoney(c.getPrice()*qty/2);
		System.out.println("Success sell charm!");
		Util.sc.nextLine();
	}
	public boolean printCharm(int start) {
		temp = new ArrayList<>();
		if(charmList!=null) {
			getCharmInfo();
			int i;
			for (i = start; i < start+4; i++) {
				if(i<charmList.size()) {				
					temp.add(charmList.get(i));
				}
			}
			if(!temp.isEmpty()) {				
				for (int j = 0; j < 4; j++) {
					if(j<temp.size()) {					
						System.out.print((j+1)+". ");
						temp.get(j).describe();	
					}
				}
			} else {
				return false;
			}
//			return i;
		} else {
			System.out.println("Your inventory is empty!");
		}
		return true;
//		return 0;
	}
	public ArrayList<Charm> getTemp() {
		return temp;
	}
	public ArrayList<Charm> getTemp2() {
		return temp2;
	}
	public void chooseCharm() {
		for (int i = 0; i < 4; i++) {
			if(i<temp2.size()) {
				System.out.print((i+1)+". ");
				temp2.get(i).describe();
			}
		}
	}
	public void createTemp2() {
		temp2 = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			if(i<temp.size())
				temp2.add(temp.get(i));
		}
	}
	public void setTemp(ArrayList<Charm> temp) {
		this.temp = temp;
	}
	private void getCharmInfo() {
		if(charmList!=null) {			
			for (Charm charm : charmList) {
				for (Charm c : Main.s.getCharmList()) {
					if(charm.getName().equals(c.getName())) {
						charm.setAvailableFrom(c.getAvailableFrom());
						charm.setDescription(c.getDescription());
						charm.setPrice(c.getPrice());
					}
				}
			}
		}
	}
	public void setCharmList(ArrayList<Charm> charmList) {
		this.charmList = charmList;
	}
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
	public void describeLobby() {
		System.out.println("Name : "+biodata.name);
		System.out.println("Money : "+money);
		System.out.println("Day count : "+pt.getDay());
		System.out.println("Date "+Util.formatDate.format(pt.getLastDate()));
		printActiveCharm();
		System.out.println();
	}
	public void printActiveCharm() {
		if(activeCharmActivated()) {
			System.out.println("\n===========\nActive charm\n===========");
			if(activeCharm.getMc()!=null)
				System.out.println("Money Charm : "+((Charm)activeCharm.getMc()).getName()+" "+((Charm)activeCharm.getMc()).getQty());
			if(activeCharm.getTc()!=null)
				System.out.println("Time Charm : "+((Charm)activeCharm.getTc()).getName()+" "+((Charm)activeCharm.getTc()).getQty());
			if(activeCharm.getPc()!=null)
				System.out.println("Productivity Charm : "+((Charm)activeCharm.getPc()).getName()+" "+((Charm)activeCharm.getPc()).getQty());
			if(activeCharm.getWc()!=null)
				System.out.println("Weird Charm : "+((Charm)activeCharm.getWc()).getName()+" "+((Charm)activeCharm.getWc()).getQty());
		}
	}
	private boolean activeCharmActivated() {
		int count = 0;
		if(activeCharm!=null) {
			if(activeCharm.getMc()==null&&activeCharm.getTc()==null&&activeCharm.getPc()==null&&activeCharm.getWc()==null) return false;
			return true;
		}
		return false;
	}
	public void checkMoney() {
		if(money<0) {
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
	public Charm getCharm(int idx) {
		return temp2.get(idx);
	}

	public PlayerTime getPt() {
		return pt;
	}


	public void setPt(PlayerTime pt) {
		this.pt = pt;
	}
}
