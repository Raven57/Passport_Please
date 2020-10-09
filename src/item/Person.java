package item;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import main.Airport;
import main.Main;
import main.Queue;

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
	public boolean checkValid(Biodata constraint) {
		
		String consName,consCity,consCountry,consPlaceOfBirth;
		
		
		String bioName,bioCity,bioCountry,bioPlaceOfBirth;
		String passName,passCity,passCountry,passPlaceOfBirth;
		char bioGender,passGender,consGender;
		int bioAge,passAge,consAge;
		Date bioDob,passDob,expired,consDob;
		
		bioName = biodata.name;
		bioCity = biodata.city;
		bioCountry = biodata.country;
		bioPlaceOfBirth = biodata.placeOfBirth;
		bioGender = biodata.gender;
		bioAge = biodata.age;
		bioDob = biodata.dob;
		
		passName = passport.name;
		passCity = passport.city;
		passCountry = passport.country;
		passPlaceOfBirth = passport.placeOfBirth;
		passGender = passport.gender;
		passAge = passport.age;
		passDob = passport.dob;
		if(constraint!=null) {
			
		if(constraint.name!=null) {			
			consName = constraint.name;
			if(bioName.equals(consName)) return false;
		}
		if(constraint.city!=null) {			
			consCity = constraint.city;
			if(bioCity.equals(consCity)) return false;
		}
		if(constraint.country!=null){
			consCountry = constraint.country;
			if(bioCountry.equals(consCountry)) return false;
		}
		if(constraint.placeOfBirth!=null){
			consPlaceOfBirth = constraint.placeOfBirth;
			if(bioPlaceOfBirth.equals(consPlaceOfBirth)) return false;
		}
		if(constraint.gender!='X'){
			consGender = constraint.gender;
			if(consGender==bioGender) return false;
		}
		if(constraint.age!=0){
			consAge = constraint.age;
			if(bioAge==consAge) return false;
		}
		if(constraint.dob!=null){
			consDob = constraint.dob;
			if(bioDob.compareTo(consDob)==0) return false;
		}
		}
		
		expired = passport.dob;
		
		if(!bioName.equals(passName)) return false;
		if(!bioCity.equals(passCity)) return false;
		if(!bioCountry.equals(passCountry)) return false;
		if(!bioPlaceOfBirth.equals(passPlaceOfBirth)) return false;
		if(passGender!=bioGender) return false;
		if(bioAge!=passAge) return false;
		if(!bioName.equals(passName)) return false;
		if(bioDob.compareTo(passDob)!=0) return false;
		
		if(expired.compareTo(Main.currPlayer.getPt().getLastDate())>0) return false;
		return true;
		
	}
	@Override
	public void run() {
		running.set(true);
		Queue q =Airport.q;
        while (running.get()) {
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
            patience--;
            if(patience<=0) {
            	stop();
            	this.biodata.setName("HABIS KESABARANKU");
            	q.getPersonList().remove(this);
            }
         }
	}
	
}
