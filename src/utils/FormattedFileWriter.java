package utils;

import java.io.FileWriter;
import java.io.IOException;

public class FormattedFileWriter {

	private FileWriter writer;
		
		public FormattedFileWriter(String filename) throws IOException {
			writer = new FileWriter(filename);
		}
		
		public void writeDataToFile(String[] data) throws IOException {
			String mergedData = mergeDataByNewLine(data);
			writer.write(mergedData);
			writer.close();
		}
		
		private String mergeDataByNewLine(String[] data) {
			String result = "";
			
			for (String string : data) {
				result+= string + "\n";
			}
			
			return result;
		}

}
