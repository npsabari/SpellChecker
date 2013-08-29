package dataStructure;

public class StringPair implements Comparable<StringPair>{
	public String first, second;
	public StringPair(String _first, String _second){
		this.first = _first;
		this.second = _second;
	}
	
	public boolean equals(StringPair other){
		return (this.first == other.first && this.second == other.second);
	}

	@Override
	public int compareTo(StringPair o) {
		// TODO Auto-generated method stub
		if (this.equals(o))
			return 0;
		else
			return 1;
	}
	
	
}