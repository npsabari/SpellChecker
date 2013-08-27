package dsBuilder;

import dataStructure.Trie;

public class TrieBuilder {
	public static Trie trieObj;
	public static void builder(){
		trieObj = new Trie();
		long startTime = System.nanoTime();
		trieObj.addAllWords(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("Trie Building time: "+(duration*1.0/1000000000));
	}
}
