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
	public Map<String, LinkedHashMap<String, Double> > correctWords;
	
	public void solve() throws IOException{
		correctWords = new LinkedHashMap<String, LinkedHashMap<String, Double> >();
		for(String i : TestSetLoader.testSet) {
			correctWords.put(i, this.getSolution(i));
		}
	}
	
	protected LinkedHashMap<String, Double> getSolution(String queryWord) {
		List<String> candidates = GenerateCandidates.getCandidates(queryWord);
		/*System.out.println(candidates);*/
		PriorityQueue<StringDoublePair> pq = new PriorityQueue<StringDoublePair>();
		
		for(String i : candidates) {
			pq.add(new StringDoublePair(i, getScore(queryWord, i)));
			/*System.out.println("Score of the string "+i+" "+getScore(queryWord, i));*/
		}
		
		if (DictionaryBuilder.trieObj.getStringCount(queryWord) > 0) {
			pq.add( new StringDoublePair(queryWord, 1.0));
		}
		
		LinkedHashMap<String, Double> toRet = new LinkedHashMap<String, Double>();
		for(int i = 0; i < 3 && !pq.isEmpty(); ++i) {
			StringDoublePair tmp = pq.poll();
			toRet.put(tmp.word, tmp.score);
		}
		return toRet;
	}
	
	protected Double getScore(String misspelledWord, String candidate){
		return 0.0;
	}
}
