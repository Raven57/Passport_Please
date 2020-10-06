package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormattedFileReader {
	private final Scanner scan;
	private final String separator;
	
	public FormattedFileReader(String filename, String separator) throws FileNotFoundException {
		this.scan = new Scanner(new File(filename));
		this.separator = separator;
	}
	
	public String[] getNextData() {
		String formattedData = scan.nextLine();
		return formattedData.split(separator);
	}
	
	public Boolean hasNextData() {
		return scan.hasNextLine();
	}
}
