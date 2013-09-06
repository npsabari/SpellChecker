package dsBuilder;

import dataStructure.CountTrie;
import fileReader.CorpusLoader;

public class CorpusWordCountTrieBuilder {
	public static CountTrie trieObj;
	public static int totalWords;
	public static void builder(){
		trieObj = new CountTrie();
		totalWords = CorpusLoader.corpusWords.size();
		long startTime = System.nanoTime();
		trieObj.addAllWords(CorpusLoader.corpusWords);
		long duration = System.nanoTime() - startTime;
		System.out.println("Word Corpus Count Trie Building time: "+(duration*1.0/1000000000));
		System.out.println("Total Words in Word Corpus "+totalWords);
	}
}
