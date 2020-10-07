package item;

import java.util.concurrent.atomic.AtomicBoolean;

import main.Main;

public class Person implements Runnable{
	protected Thread worker;
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected int interval=2000;
	protected Passport passport;
	protected Biodata biodata;
	protected String email;
	protected String password;
	protected int patience;
	protected boolean correct;
	public Person(Passport passport, Biodata biodata, String email, String password, int patience, boolean correct) {
		this.passport = passport;
		this.biodata = biodata;
		this.email = email;
		this.password = password;
		this.patience = patience;
		this.correct = correct;
	}
    public void start() { 	
        worker = new Thread(this);
        worker.start();
    }

	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public AtomicBoolean getRunning() {
		return running;
	}
	public void stop() {
        running.set(false);
    }
 
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public void describe() {
		System.out.println("Email : "+email);
		System.out.println("Pass : "+password);
		System.out.println("patience : "+patience);
		
		biodata.describe();
		passport.describe();
	}
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	public Biodata getBiodata() {
		return biodata;
	}
	public void setBiodata(Biodata biodata) {
		this.biodata = biodata;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPatience() {
		return patience;
	}
	public void setPatience(int patience) {
		this.patience = patience;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		int count = 0;
		running.set(true);
        while (running.get()) {
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
            // do something here 
//            System.out.println(++count);
//            if(count>=10) stop();
            patience--;
            if(patience<=0) {
            	stop();
            	this.biodata.setName("HABIS KESABARANKU");
            	Main.q.getPersonList().remove(this);
            }
         } 
	}
	
}
