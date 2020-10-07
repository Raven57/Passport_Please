package data.factory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import data.Data;
import data.PlayerData;
import item.Biodata;
import item.Passport;
import item.Player;
import utils.FormattedFileReader;
import utils.Util;

public class PlayerDataFactory {
	private ArrayList<Player> list;
	public PlayerDataFactory() {
		this.list = new ArrayList<>();
	}
//	private boolean checkExistInList(Player str) {
//		for (Player string : list) {
//			if(string.equalsIgnoreCase(str)) return true;
//		}
//		return false;
//	}
	public PlayerData loadData(String filename, String separator) throws FileNotFoundException {
		FormattedFileReader reader = new FormattedFileReader(filename, separator);
		do {
			String[] str = reader.getNextData();
			Passport p = new Passport(str);
			Biodata b = new Biodata(str);
			Player player = new Player(p, b, str[0], str[1], Integer.parseInt(str[2]), Util.getDateFromString(str[3]));
			this.list.add(player);
		} while (reader.hasNextData());
		return this.getPlayerData();
	}
	
	protected void addData(Player str) {
		this.list.add(str);
	}
	protected ArrayList<Player> getList(){
		ArrayList<Player> clone = list;
		return clone;
	}
	protected PlayerData getPlayerData() {
		return new PlayerData(list);
	}
}
