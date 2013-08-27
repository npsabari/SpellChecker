package dataStructure;

import java.util.HashMap;
import java.util.List;

import distance.Dameraulevenshtein;

public class BKTree {
	private Node rootElement;
	private HashMap<String, Integer> bestMatches;
	private final int maxEditDistance = 2;
	private String bestMatch;

	public BKTree() {
		// TODO Auto-generated constructor stub
		rootElement = null;
	}

	public void addAllStrings(List<String> words) {
		for (String word : words) {
			this.addElement(word.toUpperCase());
		}
	}

	public void addElement(String word) {
		word = word.toUpperCase();
		if (rootElement == null) {
			rootElement = new Node(word);
		} else {
			rootElement.addChild(word);
		}
	}

	public HashMap<String, Integer> query(String queryWord, int threshold) {
		queryWord = queryWord.toUpperCase();
		this.bestMatches = new HashMap<String, Integer>();
		this.rootElement.query(queryWord, Math.min(maxEditDistance, threshold),
				this.bestMatches);
		return this.bestMatches;
	}

	public HashMap<String, Integer> bestMatchWord(String queryWord) {
		queryWord = queryWord.toUpperCase();
		int editDistance = this.rootElement.findBestMatch(queryWord,
				Integer.MAX_VALUE);
		HashMap<String, Integer> returnVal = new HashMap<String, Integer>();
		returnVal.put(this.rootElement.getBestMatch(), editDistance);
		return returnVal;
	}

	private class Node {
		String word;
		HashMap<Integer, Node> children;
		
//		public Node() {
//			// TODO Auto-generated constructor stub
//			word = new String("");
//			children = new HashMap<Integer, Node>();
//		}

		public Node(String word) {
			this.word = word;
			this.children = new HashMap<Integer, Node>();
		}

		public void addChild(String target) {
			int editDistance = Dameraulevenshtein.getEditDistance(this.word,
					target);
			if (this.children.containsKey(editDistance)) {
				this.children.get(editDistance).addChild(target);
			} else {
				this.children.put(editDistance, new Node(target));
			}
		}

		public int findBestMatch(String queryWord, int bestDistance) {
			int editDistance = Dameraulevenshtein.getEditDistance(this.word,
					queryWord);
			if (editDistance < bestDistance) {
				bestDistance = editDistance;
				bestMatch = this.word;
			}

			int tmpBestDistance = bestDistance;

			for (Integer distance : this.children.keySet()) {
				if (distance < editDistance + tmpBestDistance) {
					tmpBestDistance = this.children.get(distance)
							.findBestMatch(queryWord, bestDistance);
					if (tmpBestDistance < bestDistance) {
						bestDistance = tmpBestDistance;
					}
				}
			}
			return bestDistance;
		}

		public String getBestMatch() {
			return bestMatch;
		}

		public void query(String queryWord, int threshold,
				HashMap<String, Integer> queryResults) {
			int editDistance = Dameraulevenshtein.getEditDistance(this.word,
					queryWord);

			if (editDistance <= threshold) {
				queryResults.put(this.word, editDistance);
			}

			for (int score = editDistance - threshold; score <= threshold
					+ editDistance; ++score) {
				Node child = children.get(score);
				if (child != null) {
					child.query(queryWord, threshold, queryResults);
				}
			}
		}
	}
}
