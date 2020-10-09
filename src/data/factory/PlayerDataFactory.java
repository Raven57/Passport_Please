package data.factory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import data.Data;
import data.PlayerData;
import item.Biodata;
import item.Passport;
import item.Player;
import item.PlayerTime;
import item.charm.Charm;
import item.charm.money.BookOfTolerance;
import item.charm.money.FourLeafClover;
import item.charm.money.NoPenalty;
import item.charm.productivity.BePatience;
import item.charm.productivity.Cheat;
import item.charm.productivity.LessPatience;
import item.charm.time.HappyHour;
import item.charm.time.TimeStone;
import item.charm.weird.DayDreaming;
import item.charm.weird.FanaticFan;
import item.charm.weird.LovePotion;
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
	public Charm createCharm(String name, int qty) {
		Charm c = null;
		switch (name) {
		case "Love Potion":
			c = new LovePotion(name,qty);
			break;
		case "Fanatic Fan":
			c = new FanaticFan(name,qty);
			break;
		case "Day Dreaming":
			c = new DayDreaming(name,qty);
			break;
		case "Be patience":
			c = new BePatience(name,qty);
			break;
		case "Less patience":
			c = new LessPatience(name,qty);
			break;
		case "Cheat":
			c = new Cheat(name,qty);
			break;
		case "Four leaf clover":
			c = new FourLeafClover(name,qty);
			break;
		case "Book of tolerance":
			c = new BookOfTolerance(name,qty);
			break;
		case "No penalty":
			c = new NoPenalty(name,qty);
			break;
		case "Time stone":
			c = new TimeStone(name,qty);
			break;
		case "Happy hour":
			c = new HappyHour(name,qty);
			break;
		}
		return c;
	}
	public PlayerData loadData(String filename, String separator) throws FileNotFoundException {
		FormattedFileReader reader = new FormattedFileReader(filename, separator);
		do {
			String[] str = reader.getNextData();
			Passport p = new Passport(str);
			Biodata b = new Biodata(str);
			PlayerTime pt = new PlayerTime(str);
			Player player = new Player(p, b, str[0], str[1], Integer.parseInt(str[2]),pt);
			for(int i = 14; i<str.length;i+=2) {
				Charm c = createCharm(str[i], Integer.parseInt(str[i+1]));
				player.addCharm(c);
			}
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
