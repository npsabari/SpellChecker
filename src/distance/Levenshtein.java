package distance;

import auxiliaryDataStructure.StringPair;

public class Levenshtein {
	private static int insertionCost = 1, deletionCost = 1, substitutionCost = 1;
	private static String source, target;
	private static String operations;
	private static StringPair stringAlignment;
	public static int[][] dp;
	
	/**
	 * Calculates the levenshtein distance between any two given strings
	 * @param _source Misspelled word
	 * @param _target Correct word
	 * @return	Edit distance
	 */
	public static int getEditDistance(String _source, String _target){
		source = _source;
		target = _target;
		dp = new int[source.length()+1][target.length()+1];
		
		for(int i = 0; i <= source.length(); ++i)
			dp[i][0] = i*deletionCost;
		for(int i = 0; i <= target.length(); ++i)
			dp[0][i] = i*insertionCost;
		
		for(int i = 1; i <= source.length(); ++i){
			for(int j = 1; j <= target.length(); ++j){
				if(source.charAt(i-1) == target.charAt(j-1))
					dp[i][j] = dp[i-1][j-1];
				else{
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1] + substitutionCost,
							dp[i][j-1] + deletionCost), dp[i-1][j] + insertionCost);
				}
			}
		}
		calculateOperations();
		return dp[source.length()][target.length()];
	}
	
	public static String getOperations(){
		return operations;
	}
	
	public static StringPair getStringAlignment() {
		return stringAlignment;
	}
	
	/**
	 * Returns a string containing, I or D or S or N
	 * I - Insertion, D - Deletion, S - Substitution, N - No change
	 * This method prefers Substitution > Deletion > Insertion
	 */
	private static void calculateOperations(){
		int subCost, delCost, insCost, minCost;
		int i = source.length();
		int j = target.length();
		StringBuilder operation = new StringBuilder();
		while(i != 0 || j != 0){
			subCost = (i > 0 && j > 0 ? dp[i-1][j-1] : Integer.MAX_VALUE);
			delCost = (i > 0 ? dp[i-1][j] : Integer.MAX_VALUE);
			insCost = (j > 0 ? dp[i][j-1] : Integer.MAX_VALUE);
			minCost = Math.min(Math.min(subCost, delCost), insCost);
			if (minCost == subCost){
				operation.append(subCost != dp[i][j] ? 'S' : 'N');
				--i;
				--j;
			} else if (minCost == delCost) {
				operation.append('D');
				--i;
			} else {
				operation.append('I');
				--j;
			}
		}
		operations = operation.reverse().toString();
		getAlignment();
	}
	
	private static void getAlignment(){
		StringBuilder _source = new StringBuilder();
		StringBuilder _target = new StringBuilder();
		for(int i = 0, j = 0, k = 0; i < operations.length(); ++i) {
			switch(operations.charAt(i)){
			case 'N':	
			case 'S':	
				_source.append(source.charAt(j++));
				_target.append(target.charAt(k++));
				break;
			case 'D':
				_source.append(source.charAt(j++));
				_target.append('*');
				break;
			case 'I':
				_source.append('*');
				_target.append(target.charAt(k++));
			}
		}
		stringAlignment = new StringPair(_target.toString(), _source.toString());
	}
}