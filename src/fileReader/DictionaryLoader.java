package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class DictionaryLoader {
	public static List<String> wordList;
	public static void loadDictionary() throws IOException{
		BufferedReader infile;
		try {
			infile = new BufferedReader(new FileReader("./dictionary/wordList"));
			String line;
			wordList = new Vector<String>();
			while((line = infile.readLine()) != null ) {
				wordList.add(line);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not found!");
			e.printStackTrace();
		}
	}
}
