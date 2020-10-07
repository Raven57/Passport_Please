package main;

import java.util.concurrent.atomic.AtomicBoolean;

import item.Person;
import utils.Util;

public class MenuOne implements Runnable{
	protected Thread worker;
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected int interval=1000;
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
			Main.currPlayer.checkMoney();
			if(Main.q==null) {			
				Main.initializeQueue();
				Main.menuChoice=-1;
			}
			Util.cls();
			Main.printTime();
			System.out.println("menu choice "+Main.menuChoice);
//				if(q!=null) {				
					Util.printPerson(Main.q.getPersonList());
					
					int idx = Main.menuChoice-1;
					if(Main.menuChoice>=0&&Main.menuChoice!=6&&idx>=0) {	
						if(Main.q.getPersonList().isEmpty()) {
							Main.addDate(true);
							continue;
						}
						Person p = Main.q.getPersonList().get(idx);
						if (p.isCorrect()) {
							Main.currPlayer.addMoney(Main.money);;
						} 
						else {
							Main.currPlayer.deductMoney(Main.money*2);
						}
						p.stop();
						Main.q.getPersonList().remove(p);
				}
				Main.menuChoice = -1;
         } 
	}
	public void start() { 	
        worker = new Thread(this);
        worker.start();
    }
    public void stop() {
    	running.set(false);
    }
}
