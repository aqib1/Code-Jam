package round.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class Vestigium {
	private static final Boolean FILE_READ = true;
	private static final String VESTIGINUM1_IN = "resources/round/qualification/Vestiginum-1.in";
	// Overall time O(n)2 and space O(n)
	public static int[] calculate(int[][] latinArray) {
		int[] result = new int[3];

		// O(n)
		// calculating trace
		for (int x = 0; x < latinArray.length; x++) {
			result[0] += latinArray[x][x];
		}

		// row wise check O(n2)
		for (int row = 0; row < latinArray.length; row++) {
			HashSet<Integer> set = new HashSet<>();
			for (int column = 0; column < latinArray[row].length; column++) {
				set.add(latinArray[row][column]);
			}
			if (set.size() != latinArray.length)
				result[1]++;
		}

		// columns wise check O(n2)
		for (int column = 0; column < latinArray.length; column++) {
			HashSet<Integer> set = new HashSet<>();
			for (int row = 0; row < latinArray.length; row++) {
				set.add(latinArray[row][column]);
			}
			if (set.size() != latinArray.length)
				result[2]++;
		}

		return result;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream in = FILE_READ ? new FileInputStream(new File(VESTIGINUM1_IN)) :  System.in;
		try(Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(in)))){
			int testCases = scanner.nextInt();
			for(int testcase=0; testcase < testCases; testcase++) {
				int rows = scanner.nextInt();
				int [][] data = new int[rows][rows];
				for(int row=0; row < rows; row++) {
					for(int col=0; col<rows; col++) {
						data[row][col] = scanner.nextInt();
					}
				}
				int[] result = calculate(data);
				System.out.println("Case #" + (testcase+1) + ": " + result[0] + " " + result[1] + " " + result[2]);
			}
		}
	}
}
