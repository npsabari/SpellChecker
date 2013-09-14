package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class TestSetLoader {
	public static List<String> testSet;
	public static void loadTestData() throws IOException{
		BufferedReader infile;
		try {
			infile = new BufferedReader(new FileReader("./Input/testSet"));
			String line;
			testSet = new Vector<String>();
			while((line = infile.readLine()) != null ) {
				testSet.add(line.toLowerCase());
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not found!");
			e.printStackTrace();
		}
	}
}
