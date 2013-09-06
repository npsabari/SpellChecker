package dsBuilder;

import dataStructure.CountTrie;
import fileReader.CorpusLoader;

public class CorpusCountTrieBuilder {
	public static CountTrie trieObj;
	public static int totalWords;
	public static void builder(){
		trieObj = new CountTrie();
		totalWords = CorpusLoader.corpus.size();
		long startTime = System.nanoTime();
		trieObj.addAllWords(CorpusLoader.corpus);
		long duration = System.nanoTime() - startTime;
		System.out.println("Corpus Count Trie Building time: "+(duration*1.0/1000000000));
		System.out.println("Total Words in Corpus "+totalWords);
	}
}
