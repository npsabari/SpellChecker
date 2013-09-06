package mainModules;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import algorithm.GenerateCandidates;
import auxiliaryDataStructure.StringDoublePair;
import dsBuilder.DictionaryBuilder;
import fileReader.TestSetLoader;

public class SpellCheckSolver {
	protected List<String> queryWords;
	protected  Map<String, LinkedHashMap<String, Double> > correctWords;
	
	protected void getTestWords() throws IOException {
		queryWords = TestSetLoader.loadTestData();
	}
	
	public void solve() throws IOException{
		this.getTestWords();
		correctWords = new LinkedHashMap<String, LinkedHashMap<String, Double> >();
		for(String i : queryWords) {
			correctWords.put(i, this.getSolution(i));
		}
	}
	
	protected LinkedHashMap<String, Double> getSolution(String queryWord) {
		List<String> candidates = GenerateCandidates.getCandidates(queryWord);
		PriorityQueue<StringDoublePair> pq = new PriorityQueue<StringDoublePair>();
		
		for(String i : candidates) {
			pq.add(new StringDoublePair(i, getScore(queryWord, i)));
		}
		
		if (DictionaryBuilder.trieObj.isStringPresent(queryWord)) {
			pq.add( new StringDoublePair(queryWord, 1.0));
		}
		
		LinkedHashMap<String, Double> toRet = new LinkedHashMap<String, Double>();
		for(int i = 0; i < 3; ++i) {
			StringDoublePair tmp = pq.poll();
			toRet.put(tmp.word, tmp.score);
		}
		return toRet;
	}
	
	protected Double getScore(String misspelledWord, String candidate){
		return 0.0;
	}
}
