package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfusionMatrixLoader {
	public static int[][] deletionMatrix, additionMatrix, substitutionMatrix, TranspositionMatrix;
	public static void loadDictionary() throws IOException{
		String[] fileNames = {"Addition", "Deletion", "Substitution", "Transposition"};
		BufferedReader infile;
		for(String file : fileNames) {
			int[][] tmpMatrix = file.equals("Addition") ? additionMatrix : (file.equals("Deletion") ? deletionMatrix : (
					file.equals("Substitution") ? substitutionMatrix : TranspositionMatrix));
			tmpMatrix = new int[28][28];
			try {
				infile = new BufferedReader(new FileReader("./ConfusionMatrices/"+file+".txt"));
				String line;
				int i = 0;
				while((line = infile.readLine()) != null ) {
					int j = 0;
					for(String tmp : line.split(" ")){
						tmpMatrix[i][j] = Integer.parseInt(tmp);
						++j;
					}
					++i;
				}
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(file+" File Not found!");
				e.printStackTrace();
			}
		}
	}
}