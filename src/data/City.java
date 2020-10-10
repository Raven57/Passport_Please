package data;

import java.util.ArrayList;

public class City extends Data{
	public City(ArrayList<String> list) {
		super(list);
	}
	public boolean validCity(String string) {
		if(string==null) return false;
		if(string.equalsIgnoreCase("Surabaya")) return true;
		else if(string.equalsIgnoreCase("Jakarta")) return true;
		else if(string.equalsIgnoreCase("Semarang")) return true;
		else if(string.equalsIgnoreCase("Bandung")) return true;
		else if(string.equalsIgnoreCase("Palembang")) return true;
		else if(string.equalsIgnoreCase("Cambridge")) return true;
		else if(string.equalsIgnoreCase("York")) return true;
		else if(string.equalsIgnoreCase("Oxford")) return true;
		else if(string.equalsIgnoreCase("Nottingham")) return true;
		else if(string.equalsIgnoreCase("Brighton")) return true;
		else if(string.equalsIgnoreCase("Denver")) return true;
		else if(string.equalsIgnoreCase("Austin")) return true;
		else if(string.equalsIgnoreCase("Dallas")) return true;
		else if(string.equalsIgnoreCase("Houston")) return true;
		else if(string.equalsIgnoreCase("Boston")) return true;
		else if(string.equalsIgnoreCase("Tokyo")) return true;
		else if(string.equalsIgnoreCase("Kyoto")) return true;
		else if(string.equalsIgnoreCase("Osaka")) return true;
		else if(string.equalsIgnoreCase("Yokohama")) return true;
		else if(string.equalsIgnoreCase("Sapporo")) return true;
		else if(string.equalsIgnoreCase("Beijing")) return true;
		else if(string.equalsIgnoreCase("Shanghai")) return true;
		else if(string.equalsIgnoreCase("Shenzhen")) return true;
		else if(string.equalsIgnoreCase("Tianjin")) return true;
		else if(string.equalsIgnoreCase("Chongqing")) return true;
		else if(string.equalsIgnoreCase("Moscow")) return true;
		else if(string.equalsIgnoreCase("St. Petersburg")) return true;
		else if(string.equalsIgnoreCase("Kaliningrad")) return true;
		else if(string.equalsIgnoreCase("Krasnodar")) return true;
		else if(string.equalsIgnoreCase("Korenovsk")) return true;
	return false;
}
}
