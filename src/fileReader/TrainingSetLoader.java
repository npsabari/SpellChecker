package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TrainingSetLoader {
	public static HashMap<String, String> TrainingSet;
	public static void loadTrainingData() throws IOException{
		BufferedReader infile;
		try {
			infile = new BufferedReader(new FileReader("./dictionary/trainingSet"));
			String line;
			TrainingSet = new HashMap<String, String>();
			while((line = infile.readLine()) != null ) {
				String[] words = line.split(" ");
				TrainingSet.put(words[0], words[1]);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not found!");
			e.printStackTrace();
		}
	}
}