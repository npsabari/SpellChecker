package dataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void addWord(String queryWord) {
		Node currentNode = rootNode;
		
		for(int i = 0; i < queryWord.length(); ++i) {
			if (!currentNode.containsChild(queryWord.charAt(i))) {
				currentNode.addChild(new Node(queryWord.charAt(i), currentNode.getValue() + queryWord.charAt(i)));
			}
			currentNode = currentNode.getChild(queryWord.charAt(i));
		}
		currentNode.setStringIndicator();
	}
	
	public boolean isStringPresent(String queryWord) {
		Node queryNode = getNode(queryWord);
		return queryNode == null ? false : (queryNode.getStringIndicator());
	}
	
	private Node getNode(String word) {
		Node currentNode = rootNode;
		for(int i = 0; i < word.length() && currentNode != null; ++i) {
			currentNode = currentNode.getChild(word.charAt(i));
		}
		return currentNode;
	}
	
	private class Node{
		private final Character charKey;
		private final String value;
		private boolean stringPresent;
		private Map<String, Trie.Node> children;
		
		public Node(char _charKey, String _value) {
			charKey = _charKey;
			value = _value;
			stringPresent = false;
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
		
		public boolean getStringIndicator() {
			return this.stringPresent;
		}
		
		public void setStringIndicator() {
			this.stringPresent = true;
		}
	}
}