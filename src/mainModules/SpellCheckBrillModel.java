package mainModules;

import auxiliaryDataStructure.StringPair;
import distance.Levenshtein;
import dsBuilder.CorpusCountTrieBuilder;
import dsBuilder.SegmentCountTrieBuilder;

public class SpellCheckBrillModel extends SpellCheckSolver{
	
	protected Double getScore(String misspelledWord, String candidate){
		return segmentStringPair(misspelledWord, candidate);
	}
	
	/**
	 * This function calculates the score of the correct word for a given misspelled word
	 * Score = for i=0 to edit_distance(src, target) product of (max(prob of individual segments))
	 * @param source misspelled string
	 * @param target correct string
	 * @return score
	 */
	private Double segmentStringPair(String source, String target){
		Levenshtein.getEditDistance(source, target);
		StringPair stringAlignment = Levenshtein.getStringAlignment();
		String correctWordAlignment = stringAlignment.first;
		String misspelledWordAlignment = stringAlignment.second;
		double score = 1.0;
		StringPair goodone = null;
		for(int i = 0, j = 0; i < correctWordAlignment.length() && j < misspelledWordAlignment.length(); ++i, ++j) {
			if (correctWordAlignment.charAt(i) != misspelledWordAlignment.charAt(j) ){
				double max_prob = Double.MIN_VALUE;
				double tmp_prob;
				for(int k = i; k >= Math.max(0, i-2); --k){
					for(int l = i; l < Math.min(i+3, correctWordAlignment.length()); ++l){
						StringPair toInsert = new StringPair(correctWordAlignment.substring(k, l+1), misspelledWordAlignment.substring(k, l+1));
						if (isEditDistanceValid(toInsert, 3)) {
							toInsert = getParsedStringPair(toInsert);
							if(!(toInsert.first.length() > 0 && toInsert.second.length() > 0)) {
								continue;
							}
							tmp_prob = SegmentCountTrieBuilder.countStore.getStringPairCount(getParsedStringPair(toInsert));
							tmp_prob /= (CorpusCountTrieBuilder.trieObj.getStringCount(toInsert.first) + 1.0);
							if (max_prob < tmp_prob)
								goodone = toInsert;	
							max_prob = Math.max(max_prob, tmp_prob);
						}
					}
				}
				score *= max_prob;
			}
		}
		/*System.out.println(goodone.first+ " "+ goodone.second);*/
		return goodone == null ? 0.0 : score;
	}
	
	private StringPair getParsedStringPair(StringPair wordPair) {
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
				if (threshold < mismatch)
					return false;
			}
		}
		return true;
	}
}