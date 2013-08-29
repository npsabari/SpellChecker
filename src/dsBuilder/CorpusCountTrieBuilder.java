package dsBuilder;

import dataStructure.CountTrie;
import fileReader.DictionaryLoader;

public class CorpusCountTrieBuilder {
	public static CountTrie trieObj;
	public static void builder(){
		trieObj = new CountTrie();
		long startTime = System.nanoTime();
		trieObj.addAllWords(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("Corpus Count Trie Building time: "+(duration*1.0/1000000000));
	}
}
