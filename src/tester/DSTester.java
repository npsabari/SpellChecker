package tester;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import mainModules.SpellCheckBrillModel;
import mainModules.SpellCheckSolver;
import algorithm.Soundex;
import auxiliaryDataStructure.StringPair;
import dataStructure.BKTree;
import dataStructure.SoundexTrie;
import distance.Dameraulevenshtein;
import distance.Levenshtein;
import dsBuilder.SegmentCountTrieBuilder;
import fileReader.TestSetLoader;

public class DSTester {
	private final String[] wordList = 
			new String[] { "remote", "barn", "book", "glass", "chair", "closet", 
			"skull", "giraffe", "boat", "soda", "safari", "sapari" };
	private final Vector<String> wordVector = 
			new Vector<String>(Arrays.asList(wordList));
	
	
	public DSTester(boolean testAll) throws IOException {
		if (testAll) {
			/*this.testDamerauDistance();
			this.testSoundex();
			this.testBKTree();
			this.testSoundexTrie();
			this.testLevenshtein();
			this.testSegmentation();*/
			this.testBrill();
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
		System.out.println(SegmentCountTrieBuilder.countStore.getStringPairCount(new StringPair("wh", "wa")));
	}
	
	public void testBrill() throws IOException{
		SpellCheckSolver testObj = new SpellCheckBrillModel();
		testObj.solve();
		for(String i : TestSetLoader.testSet){
			System.out.println(testObj.correctWords.get(i));
		}
	}
}