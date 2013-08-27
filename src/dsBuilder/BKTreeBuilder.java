package dsBuilder;

import dataStructure.BKTree;

public class BKTreeBuilder {
	public static BKTree bkTree;
	public static void builder(){
		bkTree = new BKTree();
		long startTime = System.nanoTime();
		bkTree.addAllStrings(DictionaryLoader.wordList);
		long duration = System.nanoTime() - startTime;
		System.out.println("BKTree building time: "+(duration*1.0/1000000000));
	}
}
