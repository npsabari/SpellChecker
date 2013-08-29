package dsBuilder;

import dataStructure.SoundexTrie;
import fileReader.DictionaryLoader;

public class SoundexTrieBuilder {
	public static SoundexTrie trieObj;
	public static void builder(){
		trieObj = new SoundexTrie();
		long startTime = System.nanoTime();
		trieObj.addAllWords(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("Soundex Trie Building time: "+(duration*1.0/1000000000));
	}
}
