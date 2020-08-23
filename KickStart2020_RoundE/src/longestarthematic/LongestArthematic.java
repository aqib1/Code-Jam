package longestarthematic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LongestArthematic {
	private static final boolean FILE_READ = true;
	private static final String FILE_PATH = "resources/longestarthematic/LongestArthematic.in";

	public static void main(String[] args) throws IOException {
		InputStream in = FILE_READ ? new FileInputStream(new File(FILE_PATH)) : System.in;
		try (Scanner scanner = new Scanner(in)) {
			int testCases = scanner.nextInt();
			scanner.nextLine();
			for (int testcase = 1; testcase <= testCases; testcase++) {
				int len = scanner.nextInt();
				int array[] = new int[len];
				for (int x = 0; x < len; x++)
					array[x] = scanner.nextInt();
				System.out.println("Case #" + testcase + ": " + longestArthematic(array));
			}
		}
	}

	/**
	 * 5 5 4 5 5 5 4 5 6
	 */
	private static int longestArthematic(int[] array) {
		int len = 1, currLen = 1;
		int diff = array[0] - array[1];
		for (int x = 1; x < array.length - 1; x++) {
			int currDiff = array[x] - array[x + 1];
			if (diff != currDiff) {
				diff = currDiff;
				currLen = 1;
			} else
				len = Math.max(len, ++currLen);
		}
		return len + 1;
	}
}
