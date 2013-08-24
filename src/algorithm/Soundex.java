package algorithm;

/**
 * Soundex Algorithm to hash strings.
 * First remove the vowels from the string and retain the first letter of the string
 * Iterate over the string until it becomes a character followed by three numbers.
 * @author sabari
 * link: http://en.wikipedia.org/wiki/Soundex
 */

public class Soundex {
	private static final char[] ALPHABET_MAP = {
	    //A  B   C   D   E   F   G   H   I   J   K   L   M
	    '0','1','2','3','0','1','2','0','0','2','2','4','5',
	    //N  O   P   W   R   S   T   U   V   W   X   Y   Z
	    '5','0','1','2','6','2','3','0','1','0','2','0','2'
	  };
	
	public static String getSoundex(String source){
		char prev = '@';
		String tmp_source = source.toUpperCase();
		StringBuffer result = new StringBuffer();
		
		for(int i = 0; i < tmp_source.length() && result.length() < 4; ++i) {
			char c = tmp_source.charAt(i);
			if ( c >= 'A' && c <= 'Z' && prev != c){
				prev = c;
				if (i == 0)  {
					result.append(c);
				}
				else {
					c = ALPHABET_MAP[c-'A'];
					if (c != '0') {
						result.append(c);
					}
				}
			}
		}
		if (result.length() == 0)
			return null;
		while(result.length() < 4) {
			result.append('0');
		}
		return result.toString();
	}
}
