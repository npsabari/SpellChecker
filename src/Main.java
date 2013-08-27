import java.io.IOException;
import java.util.HashMap;

import dsBuilder.BKTreeBuilder;
import dsBuilder.DictionaryLoader;
import dsBuilder.TrieBuilder;
import tester.DSTester;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DSTester testObject = new DSTester(true);
		DictionaryLoader.loadDictionary();
		BKTreeBuilder.builder();
		HashMap<String, Integer> queryMap = BKTreeBuilder.bkTree.query("mad", 1);
		System.out.println(queryMap);
		TrieBuilder.builder();
		System.out.println(TrieBuilder.trieObj.getSimilarSoundexWords("mad"));
	}
}
