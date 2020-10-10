package data;

import java.util.ArrayList;

import item.Player;
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

public class PlayerData {
	protected ArrayList<Player> playerList;
	public PlayerData(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public Player validateCredential(String email, String password) {
		for (Player player : playerList) {
			if(player.getEmail().equalsIgnoreCase(email)&&player.getPassword().equalsIgnoreCase(password)) 
				return player;
		}
		return null;
	}
	public boolean emailExisted(String email) {
		for (Player player : playerList) {
			if(player.getEmail().equalsIgnoreCase(email))return true;
		}
		return false;
	}
	
	public void addToList(Player p) {
		playerList.add(p);
	}
	public Integer getSize() {
		return playerList.size();
	}
	public ArrayList<Player> getPlayerList(){
		ArrayList<Player> clone = playerList;
		return clone;
	}
//	public String getRandom() {
//		return Util.randomStringFromArrayList(list);
//	}
}
