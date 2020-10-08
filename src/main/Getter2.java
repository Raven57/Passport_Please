package main;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import utils.Util;

public class Getter2 implements Runnable{
	protected Thread worker;
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected int interval=1000;
    Scanner sc = new Scanner(System.in);
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
        	Main.menuChoice= getChoice();
        	choice = 0;
//        	if(Main.menuChoice==6) {
//        		Main.menuOne.stop();
//        		System.out.println("exit");
//        		Main.get = new Getter();
//        		Main.get.start();
//        		choice = 0;
//        		Main.g.start();
//        		this.stop();
//        		Main.g2=null;
//        	}
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
//    		Main.currPlayer.stop();
//    		Main.g.stop();
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
    			try {
					choice = sc.nextInt();
				} catch (Exception e) {
					choice = 0;
				}
    			if(choice<Main.min||choice>Main.max) {
    				System.out.println("asdasdas");
    				this.stop();
    				break;
    			}
    			
    		} while (choice<Main.min||choice>Main.max);
    	}
//		choice = 0;
//		if(Main.m.running.get()) {			
//			Main.m.stop();
//		}
		return choice;
    }
}
