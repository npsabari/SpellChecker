package distance;

import java.util.HashMap;
import java.util.Map;

public class Dameraulevenshtein {
	private static int insertionCost = 1, deletionCost = 1, substitutionCost = 1, swapCost = 1;
	
	public static int getEditDistance(String source, String target) {
		if (source.length() == 0){
			return target.length()*insertionCost;
		}
		if (target.length() == 0){
			return source.length()*deletionCost;
		}
		
		int[][] dp = new int[source.length()][target.length()];
		Map<Character, Integer> sourceIndex = new HashMap<Character, Integer>();
		if (source.charAt(0) != target.charAt(0)){
			dp[0][0] = Math.min(substitutionCost, deletionCost + insertionCost);
		}
		sourceIndex.put(source.charAt(0), 0);
		for (int i = 1; i < source.length(); i++) {
			int deleteDistance = dp[i - 1][0] + deletionCost;
			int insertDistance = (i + 1) * deletionCost + substitutionCost;
			int matchDistance = i * deletionCost + (source.charAt(i) == target.charAt(0) ? 0 : substitutionCost);
			dp[i][0] = Math.min(Math.min(deleteDistance, insertDistance), matchDistance);
		}
		for (int j = 1; j < target.length(); j++) {
			int deleteDistance = dp[0][j - 1] + insertionCost;
			int insertDistance = (j + 1) * insertionCost + deletionCost;
			int matchDistance = j * insertionCost + (source.charAt(0) == target.charAt(j) ? 0 : substitutionCost);
			dp[0][j] = Math.min(Math.min(deleteDistance, insertDistance), matchDistance);
		}
		for (int i = 1; i < source.length(); i++) {
			int maxSourceLetterMatchIndex = source.charAt(i) == target.charAt(0) ? 0 : -1;
			for (int j = 1; j < target.length(); j++) {
				Integer candidateSwapIndex = sourceIndex.get(target.charAt(j));
				int jSwap = maxSourceLetterMatchIndex;
				int deleteDistance = dp[i - 1][j] + deletionCost;
				int insertDistance = dp[i][j - 1] + insertionCost;
				int matchDistance = dp[i - 1][j - 1];
				if (source.charAt(i) != target.charAt(j)) {
					matchDistance += substitutionCost;
				} 
				else {
					maxSourceLetterMatchIndex = j;
				}
				int swapDistance;
				if (candidateSwapIndex != null && jSwap != -1) {
					int iSwap = candidateSwapIndex;
					int preSwapCost;
					if (iSwap == 0 && jSwap == 0) {
						preSwapCost = 0;
					} 
					else {
						preSwapCost = dp[Math.max(0, iSwap - 1)][Math.max(0, jSwap - 1)];
					}
					swapDistance = preSwapCost + (i-iSwap-1) * deletionCost + (j-jSwap-1) * insertionCost + swapCost;
				} 
				else {
					swapDistance = Integer.MAX_VALUE;
				}
				dp[i][j] = Math.min(Math.min(Math.min(deleteDistance, insertDistance),matchDistance), swapDistance);
			}
			sourceIndex.put(source.charAt(i), i);
		}
		return dp[source.length() - 1][target.length() - 1];
	}
}