import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import algorithm.Soundex;
import dataStructure.BKTree;
import dataStructure.Trie;
import distance.Dameraulevenshtein;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Checking Edit Distance Algorithm
		System.out.println(Dameraulevenshtein.getEditDistance("aba", "bac"));

		// Checking the Soundex Algorithm

		System.out.println(Soundex.getSoundex("euler"));
		
		//Checking BK-Tree
		String[] wordList = new String[] { "remote", "barn", "book", "glass",
				"chair", "closet", "skull", "giraffe", "boat", "soda", "safari" };

		BKTree bkTree = new BKTree();

		bkTree.addAllStrings(new Vector<String>(Arrays.asList(wordList)));

		HashMap<String, Integer> queryMap = bkTree.query("bokr", 2);
		System.out.println(queryMap);

		String searchTerm = "oba";
		System.out.println("Best match for search '" + searchTerm + "' = "
				+ bkTree.bestMatchWord(searchTerm));

		searchTerm = "bar";
		System.out.println("Best match for search '" + searchTerm + "' = "
				+ bkTree.bestMatchWord(searchTerm));
		
		//Checking Trie
		Trie obj = new Trie();
		
		obj.addAllWords(new Vector<String>(Arrays.asList(wordList)));
		System.out.println(obj.getSimilarSoundexWords("sabari"));
	}

}
