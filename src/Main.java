import java.io.IOException;

import dsBuilder.CorpusCountTrieBuilder;
import dsBuilder.DictionaryBKTreeBuilder;
import dsBuilder.CorpusWordCountTrieBuilder;
import dsBuilder.SegmentCountTrieBuilder;
import dsBuilder.SoundexTrieBuilder;
import fileReader.CorpusLoader;
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
		CorpusLoader.loadCorpus();
		DictionaryBKTreeBuilder.builder();
		SoundexTrieBuilder.builder();
		SegmentCountTrieBuilder.builder();
		CorpusWordCountTrieBuilder.builder();
		CorpusCountTrieBuilder.builder();
		//DSTester testObject = new DSTester(true);
	}
}
