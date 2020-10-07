package item;

import java.util.Date;

import main.Main;
import utils.Util;

public class Player extends Person{
	private int money;
	private Date lastDate;
	private boolean isPlaying = false;
	public Player(Passport passport, Biodata biodata, String email, String password, int money, Date lastDate) {
		super(passport, biodata, email, password, 0, true);
		this.money = money;
		this.lastDate = lastDate;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public void describe() {
		System.out.println("Email : "+email);
		System.out.println("Pass : "+password);
		System.out.println("patience : "+patience);
		System.out.println("money : "+money);
		System.out.println("lastDate: "+Util.formatDate.format(lastDate));

		biodata.describe();
		passport.describe();
	}
	public void checkMoney() {
		if(money<=0) {
			Main.stopAllThread();
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
//            System.out.println(++count);
//            if(count>=10) stop();
//            System.out.println("asd");
//            syso
            Main.addTime();
//            checkMoney();
            
         } 
	}
}
