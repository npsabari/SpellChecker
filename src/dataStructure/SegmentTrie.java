package dataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegmentTrie {
	private SegmentTrie.Node rootNode;
	
	public SegmentTrie() {
		rootNode = new SegmentTrie.Node('\0', "");
	}
	
	public void addAllWords(List<String> wordList) {
		for (String word : wordList) {
			addWord(word);
		}
	}
	
	public void addAllWordPairs(List<StringPair> wordPairList) {
		for(StringPair wordPair : wordPairList) {
			addWordPair(wordPair);
		}
	}
	
	public void addWordPair(StringPair wordPair) {
		addWord(wordPair.first+"#"+wordPair.second);
	}
	
	public void addWord(String queryWord) {
		Node currentNode = rootNode;
		
		for(int i = 0; i < queryWord.length(); ++i) {
			if (!currentNode.containsChild(queryWord.charAt(i))) {
				currentNode.addChild(new Node(queryWord.charAt(i), currentNode.getValue() + queryWord.charAt(i)));
			}
			currentNode = currentNode.getChild(queryWord.charAt(i));
		}
		currentNode.addCount();
	}
	
	public int getStringPairCount(StringPair queryWordPair) {
		return getStringCount(queryWordPair.first+"#"+queryWordPair.second);
	}
	
	public int getStringCount(String queryWord) {
		Node queryNode = getNode(queryWord);
		return queryNode != null ? queryNode.getCount() : 0;
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
		private int count;
		private Map<String, SegmentTrie.Node> children;
		
		public Node(char _charKey, String _value) {
			charKey = _charKey;
			value = _value;
			count = 0;
			children = new HashMap<String, SegmentTrie.Node>();
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
		
		public int getCount() {
			return this.count;
		}
		
		public void addCount() {
			this.count++;
		}
	}
}
