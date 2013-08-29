package tester;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import algorithm.SegmentWords;
import algorithm.Soundex;
import dataStructure.BKTree;
import dataStructure.SoundexTrie;
import dataStructure.StringPair;
import distance.Dameraulevenshtein;
import distance.Levenshtein;

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
			this.testSoundexTrie();
			this.testLevenshtein();
			this.testSegmentation();
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
	
	public void testSoundexTrie() {
		SoundexTrie obj = new SoundexTrie();
		obj.addAllWords(this.wordVector);
		System.out.println(obj.getSimilarSoundexWords("sabari"));
	}
	
	public void testLevenshtein() {
		Levenshtein.getEditDistance("sabru", "safari");
		for(int[] i : Levenshtein.dp){
			for(int j : i)
				System.out.print((j)+" ");
			System.out.println();
		}
		System.out.println(Levenshtein.getOperations());
		StringPair pa = Levenshtein.getStringAlignment();
		System.out.println(pa.first+"\n"+ pa.second);
	}
	
	public void testSegmentation() {
		SegmentWords.segmentTrainingData();
		System.out.println(SegmentWords.countStore.getStringPairCount(new StringPair("wh", "wa")));
	}
}