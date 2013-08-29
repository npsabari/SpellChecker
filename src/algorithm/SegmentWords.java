package algorithm;

import java.util.Map;

import dataStructure.SegmentTrie;
import dataStructure.StringPair;
import distance.Levenshtein;
import fileReader.TrainingSetLoader;

/**
 * To Segment a given pair<Correct, Wrong> word into series of pair<alpha, beta> with maximum of 2 adjacent edits
 * @author sabari
 *
 */
public class SegmentWords {
	public static SegmentTrie countStore;
	
	public static void segmentTrainingData(){
		countStore = new SegmentTrie();
		for(Map.Entry<String, String> wordPair : TrainingSetLoader.TrainingSet.entrySet()){
			segmentPair(wordPair.getKey(), wordPair.getValue());
		}
	}
		
	private static void segmentPair(String source, String target){
		Levenshtein.getEditDistance(source, target);
		StringPair stringAlignment = Levenshtein.getStringAlignment();
		String correctWordAlignment = stringAlignment.first;
		String misspelledWordAlignment = stringAlignment.second;
		for(int i = 0, j = 0; i < correctWordAlignment.length() && j < misspelledWordAlignment.length(); ++i, ++j) {
			if (correctWordAlignment.charAt(i) != misspelledWordAlignment.charAt(j)
					&& correctWordAlignment.charAt(i) != '*'
					&& misspelledWordAlignment.charAt(j) != '*') {
				for(int k = i; k >= Math.max(0, i-2); --k){
					for(int l = i; l < Math.min(i+3, correctWordAlignment.length()); ++l){
						StringPair toInsert = new StringPair(correctWordAlignment.substring(k, l+1), misspelledWordAlignment.substring(k, l+1));
						if (isEditDistanceValid(toInsert, 3)) {
							countStore.addWordPair(getParsedStringPair(toInsert));
						}
					}
				}
			}
		}
	}
	
	private static StringPair getParsedStringPair(StringPair wordPair) {
		return new StringPair(getParsedString(wordPair.first), getParsedString(wordPair.second));
	}
	
	private static String getParsedString(String word) {
		StringBuilder wordBuilder = new StringBuilder();
		for(char i : word.toCharArray()) {
			if (i != '*')
				wordBuilder.append(i);
		}
		return wordBuilder.toString();
	}
	
	private static boolean isEditDistanceValid(StringPair pair, int threshold) {
		int mismatch = 0;
		for(int i = 0; i < pair.first.length(); ++i) {
			if (pair.first.charAt(i) != pair.second.charAt(i)) {
				++mismatch;
				if (threshold < mismatch )
					return false;
			}
		}
		return true;
	}
}
