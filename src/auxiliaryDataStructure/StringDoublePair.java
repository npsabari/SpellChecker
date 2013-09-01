package auxiliaryDataStructure;

public class StringDoublePair implements Comparable<StringDoublePair> {
	public String word;
	public double score;
	
	public StringDoublePair(String _word, Double _score) {
		this.word = _word;
		this.score = _score;
	}

	@Override
	public int compareTo(StringDoublePair o) {
		// TODO Auto-generated method stub
		return this.score > o.score ? 1 : (this.score == o.score ? 0 : -1);
	}
}
