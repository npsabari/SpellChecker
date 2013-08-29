package dataStructure;

public class StringPair{
	public String first, second;
	public StringPair(String _first, String _second){
		this.first = _first;
		this.second = _second;
	}
	
	public boolean equals(StringPair other){
		return (this.first == other.first && this.second == other.second);
	}
}