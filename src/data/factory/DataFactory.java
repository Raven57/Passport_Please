package data.factory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import data.Data;
import utils.FormattedFileReader;

public abstract class DataFactory {
	private ArrayList<String> list;
	public DataFactory() {
		this.list = new ArrayList<>();
	}
	private boolean checkExistInList(String str) {
		for (String string : list) {
			if(string.equalsIgnoreCase(str)) return true;
		}
		return false;
	}
	public Data loadData(String filename, String separator, int idx) throws FileNotFoundException {
		FormattedFileReader reader = new FormattedFileReader(filename, separator);
		do {
			String str = reader.getNextData()[idx];
			if(!checkExistInList(str)) {				
				this.list.add(str);
			}
		} while (reader.hasNextData());
		return this.getData();
	}
	protected void addData(String str) {
		this.list.add(str);
	}
	protected ArrayList<String> getList(){
		ArrayList<String> clone = list;
		return clone;
	}
	protected abstract Data getData();
}
