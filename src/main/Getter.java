package main;

import java.util.concurrent.atomic.AtomicBoolean;

import utils.Util;

public class Getter implements Runnable{
	protected Thread worker;
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected int interval=1000;
	@Override
	public void run() {
		
		running.set(true);
        while (running.get()) {
//            try { 
//                Thread.sleep(interval); 
//            } catch (InterruptedException e){ 
//                Thread.currentThread().interrupt();
//                System.out.println(
//                  "Thread was interrupted, Failed to complete operation");
//            }
//        	checkPlaying();
        	Main.choice= getChoice();
        	choice = 0;
         } 
	}
	public void start() { 	
        worker = new Thread(this);
        worker.start();
    }
    public void stop() {
    	running.set(false);
//    	choice = 1;
//    	Util.sc.close();
//    	Util.sc=null;
//    	Util.sc.
    }
    private void checkPlaying() {
    	if(Main.isPlaying.get()==false) {
    		System.out.println("MASUK");
    		Main.currPlayer.stop();
    		Main.g.stop();
    		this.stop();
    		System.exit(0);
    	}
    }
    int choice;
    private int getChoice() {
//    	System.out.println(choice);
    	if(choice!=0) {
    		return 0;
    	}else {    		
    		do {
    			choice = Util.scanInt(Main.min, Main.max);
    		} while (choice<Main.min||choice>Main.max);
    	}
    	Main.justEnteredAMenu=false;
//		choice = 0;
//		if(Main.m.running.get()) {			
//			Main.m.stop();
//		}
		return choice;
    }
}
