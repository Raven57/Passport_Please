package data;

import java.util.ArrayList;

import utils.Util;

public abstract class Data {
	protected ArrayList<String> list;
	public Data(ArrayList<String> list) {
		this.list = list;
	}
	public void addToList(String str) {
		if(str.equals(null)||str.equals("")) return;
		list.add(str);
	}
	public Integer getSize() {
		return list.size();
	}
	public ArrayList<String> getList(){
		ArrayList<String> clone = list;
		return clone;
	}
	public String getRandom() {
		return Util.randomStringFromArrayList(list);
	}
}
