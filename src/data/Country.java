package data;

import java.util.ArrayList;

public class Country extends Data{

	public Country(ArrayList<String> list) {
		super(list);
	}
	public boolean validCountry(String string) {
		
			if(string.equalsIgnoreCase("Indonesia")) return true;
			else if(string.equalsIgnoreCase("England")) return true;
			else if(string.equalsIgnoreCase("USA")) return true;
			else if(string.equalsIgnoreCase("Japan")) return true;
			else if(string.equalsIgnoreCase("China")) return true;
			else if(string.equalsIgnoreCase("Russia")) return true;
		
		return false;
	}
	public boolean validCityMatch(String city,String country) {
//		for (String country : list) {
			if(country.equalsIgnoreCase("Indonesia")) {
				if(city.equalsIgnoreCase("Surabaya")||city.equalsIgnoreCase("Jakarta")||city.equals("Semarang")||city.equals("Bandung")||city.equals("Palembang")) return true;
			}
			else if(country.equalsIgnoreCase("England")) {
				if(city.equalsIgnoreCase("Cambridge")||city.equalsIgnoreCase("York")||city.equals("Oxford")||city.equals("Nottingham")||city.equals("Brighton")) return true;			
			}
			else if(country.equalsIgnoreCase("USA")) {
				if(city.equalsIgnoreCase("Denver")||city.equalsIgnoreCase("Austin")||city.equals("Dallas")||city.equals("Houston")||city.equals("Boston")) return true;			
			}
			else if(country.equalsIgnoreCase("Japan")) {
				if(city.equalsIgnoreCase("Tokyo")||city.equalsIgnoreCase("Kyoto")||city.equals("Osaka")||city.equals("Yokohama")||city.equals("Sapporo")) return true;			
			}
			else if(country.equalsIgnoreCase("China")) {
				if(city.equalsIgnoreCase("Beijing")||city.equalsIgnoreCase("Shanghai")||city.equals("Shenzhen")||city.equals("Tianjin")||city.equals("Chongqing")) return true;			
			}
			else if(country.equalsIgnoreCase("Russia")) {
				if(city.equalsIgnoreCase("Moscow")||city.equalsIgnoreCase("St. Petersburg")||city.equals("Kaliningrad")||city.equals("Krasnodar")||city.equals("Korenovsk")) return true;			
			}
//		}
		return false;
	}
	
}
