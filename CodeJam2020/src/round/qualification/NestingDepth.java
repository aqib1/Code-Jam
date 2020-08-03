package round.qualification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
/**
 * Problem
 * Given a string of digits S, insert a minimum number of opening and closing parentheses into it such that the resulting string is balanced and each digit d is inside exactly d pairs of matching parentheses.
 * Let the nesting of two parentheses within a string be the substring that occurs strictly between them. An opening parenthesis and a closing parenthesis that is further to its right are said to match if their nesting is empty, or if every parenthesis in their nesting matches with another parenthesis in their nesting. The nesting depth of a position p is the number of pairs of matching parentheses m such that p is included in the nesting of m.
 * For example, in the following strings, all digits match their nesting depth: 0((2)1), (((3))1(2)), ((((4)))), ((2))((2))(1). The first three strings have minimum length among those that have the same digits in the same order, but the last one does not since ((22)1) also has the digits 221 and is shorter.
 * 
 * Given a string of digits S, find another string S', comprised of parentheses and digits, such that:
 * all parentheses in S' match some other parenthesis,
 * removing any and all parentheses from S' results in S,
 * each digit in S' is equal to its nesting depth, and S' is of minimum length.

 * Input
 * The first line of the input gives the number of test cases, T. T lines follow. Each line represents a test case and contains only the string S.

 * Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the string S' defined above.

 * Limits
 * Time limit: 20 seconds per test set.
 * Memory limit: 1GB.
 * 1 ≤ T ≤ 100.
 * 1 ≤ length of S ≤ 100.

 * Test set 1 (Visible Verdict)
 * Each character in S is either 0 or 1.

 * Test set 2 (Visible Verdict)
 * Each character in S is a decimal digit between 0 and 9, inclusive.
 * @author AQIB JAVED
 * */
public class NestingDepth {
	private static final boolean FILE_READ = true;
	private static final String FILE_PATH = "resources/round/qualification/Nestingdepth-1.in";

	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = FILE_READ ? new FileInputStream(new File(FILE_PATH)) : System.in;
		try (Scanner scanner = new Scanner(in)) {
			int testCases = scanner.nextInt();
			scanner.nextLine();
			for (int testcase = 1; testcase <= testCases; testcase++) {
				String data = scanner.nextLine();
				System.out.println("Case #" + testcase + ":" + nestedDepth(data));
			}
		}
		

	}

	// Time complexity O(n) and space Complexity O(n)
	public static String nestedDepth(String s) {
		if (s == null || s.isEmpty())
			return "";
		s = "0" + s + "0";
		String result = "";

		for (int x = 0; x < s.length() - 1; x++) {
			int current = Integer.parseInt(String.valueOf(s.charAt(x)));
			int next = Integer.parseInt(String.valueOf(s.charAt(x + 1)));
			result += current;
			if (next > current)
				result += createParan(next - current, false);
			else
				result += createParan(current - next, true);

		}

		return result.substring(1);
	}

	private static String createParan(int no, boolean closing) {
		if(no == 0) return "";
		String paran = closing ? ")" : "(";
		String result = paran;
		for (int x = 1; x < no; x++)
			result += paran;
		return result;
	}
	
}
