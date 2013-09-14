package mainModules;

import dsBuilder.CorpusCountTrieBuilder;
import dsBuilder.CorpusWordCountTrieBuilder;
import fileReader.ConfusionMatrixLoader;

public class SpellCheckBaseModel extends SpellCheckSolver {

	protected Double getScore(String misspelledWord, String candidate) {
		int i, j;
		double score = 1.0;
		for (i = 0, j = 0; i < misspelledWord.length()
				&& j < candidate.length(); ++i, ++j) {
			if (misspelledWord.charAt(i) != candidate.charAt(j)) {
				break;
			}
		}
		if (misspelledWord.length() < candidate.length()) {
			score = (j == 0 ? ConfusionMatrixLoader.deletionMatrix[26][candidate.charAt(0) - 'a']
					: ConfusionMatrixLoader.deletionMatrix[candidate.charAt(j - 1) - 'a'][candidate.charAt(j) - 'a']);
			score /= (CorpusCountTrieBuilder.trieObj.getStringCount(j == 0 ? ""
					+ candidate.charAt(j) : candidate.substring(j, j + 2)) + 1);
		} else if (misspelledWord.length() > candidate.length()) {
			score = j == 0 ? ConfusionMatrixLoader.additionMatrix[26][misspelledWord.charAt(0) - 'a']
					: ConfusionMatrixLoader.additionMatrix[candidate.charAt(i - 1) - 'a'][misspelledWord.charAt(j) - 'a'];
			score /= (j == 0 ? CorpusWordCountTrieBuilder.totalWords
					: CorpusCountTrieBuilder.trieObj.getStringCount(""
							+ candidate.charAt(j - 1)) + 1);
		} else if (i < misspelledWord.length() - 1
				&& misspelledWord.charAt(i + 1) == candidate.charAt(j)
				&& misspelledWord.charAt(i) == candidate.charAt(j + 1)) {
			score = ConfusionMatrixLoader.TranspositionMatrix[candidate.charAt(j) - 'a'][candidate.charAt(j + 1) - 'a'];
			score /= (CorpusCountTrieBuilder.trieObj.getStringCount(candidate.substring(j, j + 2)) + 1);
		} else if (i < misspelledWord.length()) {
			score = ConfusionMatrixLoader.substitutionMatrix[misspelledWord.charAt(i) - 'a'][candidate.charAt(j) - 'a'];
			score /= (CorpusCountTrieBuilder.trieObj.getStringCount(""
					+ candidate.charAt(j)) + 1);
		}
		return score
				* (CorpusWordCountTrieBuilder.trieObj.getStringCount(candidate) + 0.5)
				/ CorpusWordCountTrieBuilder.totalWords;
	}
}
