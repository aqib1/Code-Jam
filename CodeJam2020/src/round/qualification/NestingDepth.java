package round.qualification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

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
