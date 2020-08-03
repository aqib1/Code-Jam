package round.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 * Problem
 * Cameron and Jamie's kid is almost 3 years old! However, even though the child is more independent now, scheduling kid activities and domestic necessities is still a challenge for the couple.
 * Cameron and Jamie have a list of N activities to take care of during the day. Each activity happens during a specified interval during the day. They need to assign each activity to one of them, so that neither of them is responsible for two activities that overlap. An activity that ends at time t is not considered to overlap with another activity that starts at time t.
 * For example, suppose that Jamie and Cameron need to cover 3 activities: one running from 18:00 to 20:00, another from 19:00 to 21:00 and another from 22:00 to 23:00. One possibility would be for Jamie to cover the activity running from 19:00 to 21:00, with Cameron covering the other two. Another valid schedule would be for Cameron to cover the activity from 18:00 to 20:00 and Jamie to cover the other two. Notice that the first two activities overlap in the time between 19:00 and 20:00, so it is impossible to assign both of those activities to the same partner.
 * Given the starting and ending times of each activity, find any schedule that does not require the same person to cover overlapping activities, or say that it is impossible.

 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with a line containing a single integer N, the number of activities to assign. Then, N more lines follow. The i-th of these lines (counting starting from 1) contains two integers Si and Ei. The i-th activity starts exactly Si minutes after midnight and ends exactly Ei minutes after midnight.

 * Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is IMPOSSIBLE if there is no valid schedule according to the above rules, or a string of exactly N characters otherwise. The i-th character in y must be C if the i-th activity is assigned to Cameron in your proposed schedule, and J if it is assigned to Jamie.

 * If there are multiple solutions, you may output any one of them. (See "What if a test case has multiple correct solutions?" in the Competing section of the FAQ. This information about multiple solutions will not be explicitly stated in the remainder of the 2020 contest.)

 * Limits
 * Time limit: 20 seconds per test set.
 * Memory limit: 1GB.
 * 1 ≤ T ≤ 100.
 * 0 ≤ Si < Ei ≤ 24 × 60.

 * Test set 1 (Visible Verdict)
 * 2 ≤ N ≤ 10.

 * Test set 2 (Visible Verdict)
 * 2 ≤ N ≤ 1000.


 * @author AQIB JAVED
 */
public class ParentingPartneringReturns {
	private static final boolean FILE_READ = true;
	private static final String FILE_PATH = "resources/round/qualification/ParentingPartneringReturns-1.in";

	static class IntervalDao {
		private int[] interval;
		private int actualIndex;

		public IntervalDao(int[] interval, int actualIndex) {
			this.interval = interval;
			this.actualIndex = actualIndex;
		}

		public int getActualIndex() {
			return actualIndex;
		}

		public int[] getInterval() {
			return interval;
		}

		@Override
		public String toString() {
			return actualIndex + " : " + Arrays.toString(interval);
		}
	}

	// Time complexity is On*log(n) and space complexity is O(n)
	public static String partnering(List<IntervalDao> data) {
		Collections.sort(data, (c, b) -> Integer.compare(c.getInterval()[0], b.getInterval()[0]));
		char[] result = new char[data.size()];
		int max_for_c = Integer.MIN_VALUE;
		int max_for_j = Integer.MIN_VALUE;
		for (int x = 0; x < data.size(); x++) {
			IntervalDao intervalDao = data.get(x);
			if (max_for_c <= intervalDao.getInterval()[0]) {
				result[intervalDao.getActualIndex()] = 'C';
				max_for_c = intervalDao.getInterval()[1];
			} else if (max_for_j <= intervalDao.getInterval()[0]) {
				result[intervalDao.actualIndex] = 'J';
				max_for_j = intervalDao.getInterval()[1];
			} else {
				return "IMPOSSIBLE";
			}
		}
		return String.valueOf(result);
	}

	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = FILE_READ ? new FileInputStream(new File(FILE_PATH)) : System.in;
		try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(in)))) {
			int testCases = scanner.nextInt();
			for (int testcase = 1; testcase <= testCases; testcase++) {
				List<IntervalDao> li = new ArrayList<>();
				int totalIntervals = scanner.nextInt();
				scanner.nextLine();
				for (int interval = 0; interval < totalIntervals; interval++) {
					String[] data = scanner.nextLine().split(" ");
					li.add(new IntervalDao(new int[] { Integer.parseInt(data[0]), Integer.parseInt(data[1]) },
							interval));
				}
				System.out.println("Case #"+testcase+": " +partnering(li));
			}
		}
	}
}
