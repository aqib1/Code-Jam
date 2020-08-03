package round.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
/**
 * Problem
 * Vestigium means "trace" in Latin. In this problem we work with Latin squares and matrix traces.
 * The trace of a square matrix is the sum of the values on the main diagonal (which runs from the upper left to the lower right).
 * An N-by-N square matrix is a Latin square if each cell contains one of N different values, and no value is repeated within a row or a column. In this problem, we will deal only with "natural Latin squares" in which the N values are the integers between 1 and N.
 * Given a matrix that contains only integers between 1 and N, we want to compute its trace and check whether it is a natural Latin square. To give some additional information, instead of simply telling us whether the matrix is a natural Latin square or not, please compute the number of rows and the number of columns that contain repeated values.
 * 
 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow. Each starts with a line containing a single integer N: the size of the matrix to explore. Then, N lines follow. The i-th of these lines contains N integers Mi,1, Mi,2 ..., Mi,N. Mi,j is the integer in the i-th row and j-th column of the matrix.

 * Output
 * For each test case, output one line containing Case #x: k r c, where x is the test case number (starting from 1), k is the trace of the matrix, r is the number of rows of the matrix that contain repeated elements, and c is the number of columns of the matrix that contain repeated elements.

 * Limits
 * Test set 1 (Visible Verdict)
 * Time limit: 20 seconds per test set.
 * Memory limit: 1GB.
 * 1 ≤ T ≤ 100.
 * 2 ≤ N ≤ 100.
 * 1 ≤ Mi,j ≤ N, for all i, j.
 * @author AQIB JAVED
 * */

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
