import java.io.IOException;

import dsBuilder.BKTreeBuilder;
import dsBuilder.CorpusCountTrieBuilder;
import dsBuilder.SegmentCountTrieBuilder;
import dsBuilder.SoundexTrieBuilder;
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
		BKTreeBuilder.builder();
		SoundexTrieBuilder.builder();
		SegmentCountTrieBuilder.builder();
		CorpusCountTrieBuilder.builder();
		
		//DSTester testObject = new DSTester(true);
	}
}
