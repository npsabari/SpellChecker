import java.io.IOException;

import dsBuilder.CorpusCountTrieBuilder;
import dsBuilder.DictionaryBuilder;
import dsBuilder.CorpusWordCountTrieBuilder;
import dsBuilder.SegmentCountTrieBuilder;
import dsBuilder.SoundexTrieBuilder;
import fileReader.ConfusionMatrixLoader;
import fileReader.CorpusLoader;
import fileReader.DictionaryLoader;
import fileReader.TestSetLoader;
import fileReader.TrainingSetLoader;
import tester.DSTester;

public class Main {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/**
		 * Loading wordlist, corpus, training data, test data and Base model Matrices 
		 */
		DictionaryLoader.loadDictionary();
		CorpusLoader.loadCorpus();
		TrainingSetLoader.loadTrainingData();
		TestSetLoader.loadTestData();
		ConfusionMatrixLoader.loadMatrices();
		
		/**
		 * Build the Following data structures
		 * 1. Word List Trie
		 * 2. Corpus Word Trie
		 * 3. Corpus (all)-Substrings Trie (for alpha)
		 * 4. Soundex Encoding Trie
		 * 5. (alpha -> beta) segment Trie
		 */
		DictionaryBuilder.builder();
		SegmentCountTrieBuilder.builder();
		SoundexTrieBuilder.builder();
		CorpusWordCountTrieBuilder.builder();
		CorpusCountTrieBuilder.builder();
		
		/**
		 * Test unit for the individual daa structures and the models
		 */
		DSTester testObject = new DSTester(true);
	}
}
