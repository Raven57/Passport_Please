package main;

import java.util.concurrent.atomic.AtomicBoolean;

import utils.Util;

public class Gameplay implements Runnable{
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
        	play(Main.choice);
         } 
	}
	public void start() { 	
        worker = new Thread(this);
        worker.start();
    }
    public void stop() {
        running.set(false);
    }

	private void play(int choice) {
		if(Main.isPlaying.get()) {			
			Util.cls();
			Main.printTime();
			switch (choice) {
			case 0:
				Main.printMainMenu();
				break;
			case 1:
				Main.justEnteredAMenu=true;
				Main.menu1();
				break;
			case 2:
				Main.justEnteredAMenu=true;
				System.out.println("Menu 2");
				break;
			case 3:
				Main.justEnteredAMenu=true;
				System.out.println("Menu 3");
				break;
			case 4:
				Main.justEnteredAMenu=true;
				Main.menu4();
				break;
			case 5:
				Main.justEnteredAMenu=true;
				System.out.println("Menu 5");
				break;
			case 6:
				System.out.println("Menu 6");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

 
}
