package tester;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import algorithm.Soundex;
import dataStructure.BKTree;
import dataStructure.Trie;
import distance.Dameraulevenshtein;

public class DSTester {
	private final String[] wordList = 
			new String[] { "remote", "barn", "book", "glass", "chair", "closet", 
			"skull", "giraffe", "boat", "soda", "safari", "sapari" };
	private final Vector<String> wordVector = 
			new Vector<String>(Arrays.asList(wordList));
	
	
	public DSTester(boolean testAll) {
		if (testAll) {
			this.testDamerauDistance();
			this.testSoundex();
			this.testBKTree();
			this.testTrie();
		}
	}
	
	public void testDamerauDistance() {
		System.out.println(Dameraulevenshtein.getEditDistance("mad", "mad"));
	}
	
	public void testSoundex() {
		System.out.println(Soundex.getSoundex("euler"));
	}
	
	public void testBKTree() {
		BKTree bkTree = new BKTree();
		bkTree.addAllStrings(this.wordVector);

		HashMap<String, Integer> queryMap = bkTree.query("soda", 2);
		System.out.println(queryMap);

		String searchTerm = "oba";
		System.out.println("Best match for search '" + searchTerm + "' = "
				+ bkTree.bestMatchWord(searchTerm));

		searchTerm = "bar";
		System.out.println("Best match for search '" + searchTerm + "' = "
				+ bkTree.bestMatchWord(searchTerm));
	}
	
	public void testTrie() {
		Trie obj = new Trie();
		obj.addAllWords(this.wordVector);
		System.out.println(obj.getSimilarSoundexWords("sabari"));
	}
}
