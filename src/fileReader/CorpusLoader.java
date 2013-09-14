package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class CorpusLoader {

	public static List<String> corpusWords, corpus;
	public static void loadCorpus() throws IOException{
		BufferedReader infile;
		try {
			String line;
			corpus = new Vector<String>();
			corpusWords = new Vector<String>();
			infile = new BufferedReader(new FileReader("./dictionary/allWords.corpus"));
			while((line = infile.readLine()) != null ) {
				corpusWords.add(line.toLowerCase());
				for(int i = 0; i < line.length(); ++i){
					for(int j = i+1; j <= Math.min(line.length(), i+6); ++j){
						corpus.add(line.substring(i, j).toLowerCase());
					}
				}
			}
			infile.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not found!");
			e.printStackTrace();
		}
	}
}
