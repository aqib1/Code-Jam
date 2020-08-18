package round.qualification;

import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Last year, a research consortium had some trouble with a distributed
 * database system that sometimes lost pieces of the data. You do not need to
 * read or understand that problem in order to solve this one!
 * 
 * The consortium has decided that distributed systems are too complicated, so
 * they are storing B bits of important information in a single array on one
 * awesome machine. As an additional layer of security, they have made it
 * difficult to obtain the information quickly; the user must query for a bit
 * position between 1 and B, and then they receive that bit of the stored array
 * as a response.
 * 
 * Unfortunately, this ultra-modern machine is subject to random quantum
 * fluctuations! Specifically, after every 1st, 11th, 21st, 31st... etc. query
 * is sent, but before the response is given, quantum fluctuation causes exactly
 * one of the following four effects, with equal probability:
 * 
 * 25% of the time, the array is complemented: every 0 becomes a 1, and vice
 * versa. 25% of the time, the array is reversed: the first bit swaps with the
 * last bit, the second bit swaps with the second-to-last bit, and so on. 25% of
 * the time, both of the things above (complementation and reversal) happen to
 * the array. (Notice that the order in which they happen does not matter.) 25%
 * of the time, nothing happens to the array. Moreover, there is no indication
 * of what effect the quantum fluctuation has had each time. The consortium is
 * now concerned, and it has hired you to get its precious data back, in
 * whatever form it is in! Can you find the entire array, such that your answer
 * is accurate as of the time that you give it? Answering does not count as a
 * query, so if you answer after your 30th query, for example, the array will be
 * the same as it was after your 21st through 30th queries.
 * 
 * 
 * @author AQIB JAVED
 */
public class EsabAtad {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int testCases = scanner.nextInt();
			int bitCount = scanner.nextInt();
			for (int testcase = 0; testcase < testCases; testcase++) {
				if (!runTest(bitCount, scanner))
					break;
			}
		}
	}

	private static String toString (BitSet bitSet, int bitCount) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<bitCount; i++) {
			sb.append(bitSet.get(i) ? '1' : '0');
		}
		return sb.toString();
	}
	private static boolean read(int bitIndex, Scanner scanner) {
		System.out.println(bitIndex + 1);
		System.out.flush();
		return scanner.next().charAt(0) == '1';
	}

	private static BitSet compliment(BitSet bitSet, int bitCount) {
		BitSet compliment = new BitSet(bitCount);
		for (int x = 0; x < bitCount; x++) {
			compliment.set(x, !bitSet.get(x));
		}
		return compliment;
	}

	private static BitSet reverse(BitSet bitSet, int bitCount) {
		BitSet reverse = new BitSet(bitCount);
		for (int x = 0; x < bitCount; x++) {
			reverse.set(bitCount - x - 1, bitSet.get(x));
		}
		return reverse;
	}

	// Time complexity is O(n)2 and space is O(n)
	private static boolean runTest(int bitCount, Scanner scanner) {
		BitSet bitSet = new BitSet(bitCount);
		int bitIndex = 0;
		int oppositeBitsIndex = -1;
		int sameBitsIndex = -1;
		int queries = 0;
		while (bitIndex < bitCount / 2) {
			if (queries > 0 && queries % 10 == 0) {
				boolean oppositeBitChanged = false;
				boolean sameBitChanged = false;
				if (oppositeBitsIndex != -1) {
					boolean oppositeCurrBit = read(oppositeBitsIndex, scanner);
					oppositeBitChanged = oppositeCurrBit != bitSet.get(oppositeBitsIndex);
				} else {
					read(0, scanner);
				}

				if (sameBitsIndex != -1) {
					boolean sameCurrBit = read(sameBitsIndex, scanner);
					sameBitChanged = sameCurrBit != bitSet.get(sameBitsIndex);
				} else {
					read(0, scanner);
				}

				if (oppositeBitChanged && sameBitChanged) {
					bitSet = compliment(bitSet, bitCount);
				} else if (oppositeBitChanged && !sameBitChanged) {
					bitSet = reverse(bitSet, bitCount);
				} else if (!oppositeBitChanged && sameBitChanged) {
					bitSet = compliment(reverse(bitSet, bitCount), bitCount);
				}
				
			} else {
				boolean bi = read(bitIndex, scanner);
				boolean bj = read(bitCount - bitIndex - 1, scanner);
				// opposite bit pair
				if (bi != bj && oppositeBitsIndex == -1)
					oppositeBitsIndex = bitIndex;
				// same bit pair
				if (bi == bj && sameBitsIndex == -1)
					sameBitsIndex = bitIndex;
				bitSet.set(bitIndex, bi);
				bitSet.set(bitCount - bitIndex - 1, bj);
				bitIndex++;
			}

			queries += 2;
		}
		String result = toString(bitSet, bitCount);
		System.out.println(result);
		System.out.flush();
		char response = scanner.next().charAt(0);
		if(response == 'Y') {
			System.err.println("Correct: "+ result);
			return true;
		}
		System.err.println("Wrong: "+ result);
		return false;
	}

}
