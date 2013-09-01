package algorithm;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import dsBuilder.DictionaryBKTreeBuilder;
import dsBuilder.SoundexTrieBuilder;

public class GenerateCandidates {

	public static List<String> getCandidates(String queryWord){
		Set<String> editCandidates =  DictionaryBKTreeBuilder.bkTree.query(queryWord, 1).keySet();
		List<String> soundexCandidates = SoundexTrieBuilder.trieObj.getSimilarSoundexWords(queryWord);
		for(String i : soundexCandidates){
			editCandidates.add(i);
		}
		List<String> candidates = new Vector<String>(editCandidates.size());
		for (String i : editCandidates) {
			candidates.add(i);
		}
		return candidates;
	}
}
