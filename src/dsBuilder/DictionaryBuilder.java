package dsBuilder;

import dataStructure.BKTree;
import dataStructure.Trie;
import fileReader.DictionaryLoader;

public class DictionaryBuilder {
	public static BKTree bkTree;
	public static Trie trieObj;
	public static void builder(){
		
		bkTree = new BKTree();
		long startTime = System.nanoTime();
		bkTree.addAllStrings(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("Dictionary BKTree building time: "+(duration*1.0/1000000000));
		
		trieObj = new Trie();
		startTime = System.nanoTime();
		trieObj.addAllWords(DictionaryLoader.wordList);
		duration = System.nanoTime() - startTime;
		System.out.println("Dictionary Trie Building time: "+(duration*1.0/1000000000));
	}
}
