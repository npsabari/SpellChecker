package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfusionMatrixLoader {
	public static int[][] deletionMatrix, additionMatrix, substitutionMatrix, TranspositionMatrix;
	public static void loadMatrices() throws IOException{
		String[] fileNames = {"Addition", "Deletion", "Substitution", "Transposition"};
		deletionMatrix = new int[27][27];
		additionMatrix = new int[27][27];
		substitutionMatrix = new int[27][27];
		TranspositionMatrix = new int[27][27];
		BufferedReader infile;
		for(String file : fileNames) {
			try {
				infile = new BufferedReader(new FileReader("./ConfusionMatrices/"+file+".txt"));
				String line;
				int i = 0;
				while((line = infile.readLine()) != null ) {
					int j = 0;
					for(String tmp : line.split(" ")){
						if(file.equals("Addition")) {
							additionMatrix[i][j] = Integer.parseInt(tmp);
						} else if (file.equals("Deletion")) {
							deletionMatrix[i][j] = Integer.parseInt(tmp);
						} else if(file.equals("Substitution")) {
							substitutionMatrix[i][j] = Integer.parseInt(tmp);
						} else if(file.equals("Transpostion")) {
							TranspositionMatrix[i][j] = Integer.parseInt(tmp);
						}
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