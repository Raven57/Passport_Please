package data;

import java.util.ArrayList;

import item.Player;

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
