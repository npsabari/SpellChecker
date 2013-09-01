package dsBuilder;

import dataStructure.Trie;
import fileReader.DictionaryLoader;

public class DictionaryTrieBuilder {
	public static Trie trieObj;
	public static void builder(){
		trieObj = new Trie();
		long startTime = System.nanoTime();
		trieObj.addAllWords(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("Corpus Count Trie Building time: "+(duration*1.0/1000000000));
	}
}
