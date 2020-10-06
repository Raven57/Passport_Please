package item;

public class Person{
	private Passport passport;
	private Biodata biodata;
	private String email;
	private String password;
	private int patience;
	public Person(Passport passport, Biodata biodata, String email, String password, int patience) {
		this.passport = passport;
		this.biodata = biodata;
		this.email = email;
		this.password = password;
		this.patience = patience;
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
	
}
