package algorithm;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import dsBuilder.DictionaryBuilder;
import dsBuilder.SoundexTrieBuilder;

public class GenerateCandidates {

	private static int editDistanceThreshold = 3;
	public static List<String> getCandidates(String queryWord){
		Set<String> editCandidates = DictionaryBuilder.bkTree.query(queryWord, editDistanceThreshold).keySet();
		List<String> soundexCandidates = SoundexTrieBuilder.trieObj.getSimilarSoundexWords(queryWord);
		List<String> candidates = new Vector<String>();
		for(String i : editCandidates) {
			candidates.add(i);
		}
		for(String i : soundexCandidates){
			/*System.out.println(i.toUpperCase());*/
			if(!candidates.contains(i.toUpperCase()))
				candidates.add(i.toUpperCase());
		}
		return candidates;
	}
}