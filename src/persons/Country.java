package persons;

import java.util.ArrayList;

public class Country {
	private ArrayList<City> cities = new ArrayList<>();
	private ArrayList<Phrase> phrases = new ArrayList<>();
	private String countryName;
	public Country(String countryName) {
		this.countryName = countryName;
	}
	public void addCity(City c) {
		cities.add(c);
	}
	public void addPhrase(Phrase p) {
		phrases.add(p);
	}
	
	
	
}
