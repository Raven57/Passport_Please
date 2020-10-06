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
	public Data loadData(String filename, String separator, int idx) throws FileNotFoundException {
		FormattedFileReader reader = new FormattedFileReader(filename, separator);
//		while() {
		do {
//			if(reader.getNextData()[idx].equals(null)||reader.getNextData()[idx].equals("")) break;
			this.list.add(reader.getNextData()[idx]);
		} while (reader.hasNextData());
//		}
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
