package dataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import algorithm.Soundex;

public class Trie {
	private Trie.Node rootNode;
	
	public Trie() {
		rootNode = new Trie.Node('\0', "");
	}
	
	public void addAllWords(List<String> wordList) {
		for (String word : wordList) {
			addWord(word);
		}
	}
	
	private void addWord(String word) {
		Node currentNode = rootNode;
		String queryWord = Soundex.getSoundex(word);
		
		for(int i = 0; i < queryWord.length(); ++i) {
			if (!currentNode.containsChild(queryWord.charAt(i))) {
				currentNode.addChild(new Node(queryWord.charAt(i), currentNode.getValue() + queryWord.charAt(i)));
			}
			currentNode = currentNode.getChild(queryWord.charAt(i));
		}
		currentNode.addWord(word);
	}
	
	public List<String> getSimilarSoundexWords(String word) {
		Node queryNode = getNode(word);
		return queryNode != null ? queryNode.getWordsStored() : null;
	}
	
	public Node getNode(String word) {
		return getSoundexNode(Soundex.getSoundex(word));
	}
	
	public Node getSoundexNode(String word) {
		Node currentNode = rootNode;
		for(int i = 0; i < word.length() && currentNode != null; ++i) {
			currentNode = currentNode.getChild(word.charAt(i));
		}
		return currentNode;
	}
	
	private class Node{
		private final Character charKey;
		private final String value;
		// Store the actual words instead of Soundex hash
		private final List<String> words;
		private Map<String, Trie.Node> children;
		
		public Node(char _charKey, String _value) {
			charKey = _charKey;
			value = _value;
			words = new Vector<String>();
			children = new HashMap<String, Trie.Node>();
		}
		
		public void addChild(Node element) {
			if(!children.containsKey(Character.toString(element.charKey))) {
				children.put(Character.toString(element.charKey), element);
			}
		}
		
		public boolean containsChild(char c) {
			return children.containsKey(Character.toString(c));
		}
		
		public String getValue() {
			return this.value;
		}
		
		public Node getChild(char c) {
			return children.get(Character.toString(c));
		}
		
		public List<String> getWordsStored() {
			return this.words;
		}
		
		public void addWord(String word) {
			this.words.add(word);
		}
	}
}
