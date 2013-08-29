import java.io.IOException;

import dsBuilder.BKTreeBuilder;
import dsBuilder.TrieBuilder;
import fileReader.DictionaryLoader;
import fileReader.TrainingSetLoader;
import tester.DSTester;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DictionaryLoader.loadDictionary();
		TrainingSetLoader.loadTrainingData();
		DSTester testObject = new DSTester(true);
		//BKTreeBuilder.builder();
		//TrieBuilder.builder();
	}
}
