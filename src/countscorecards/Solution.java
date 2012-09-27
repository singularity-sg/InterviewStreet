package countscorecards;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int noOfTests = 0;
        String s = null;

        Solution solution = new Solution();

        if((s = in.readLine()) != null && s.length() != 0) {
            noOfTests = Integer.parseInt(s);
        }

        for(int i=0;i<noOfTests;i++) {
        	int numOfPlayers = Integer.parseInt(in.readLine().trim());
        	String[] scorestr = in.readLine().split(" ");
        	assert(scorestr.length == numOfPlayers);
        	int[] scores = new int[numOfPlayers];
        	for(int j=0;j<scores.length;j++) {
        		scores[j] = Integer.parseInt(scorestr[j]);
        	}

        	int result = solveTheImpossible(scores);
        	System.out.println(result);
        }

        in.close();
	}

	private static int solveTheImpossible(int[] scores) {

		int maxPts = calculateTotalPts(scores);

		int erasedCnt = 0;

		for(int i=0;i<scores.length; i++) {
			if(scores[i] == -1) {
				erasedCnt++;
			} else {
				maxPts -= scores[i];
			}
		}

		return choose((maxPts + erasedCnt-1), maxPts);
	}

	private static int choose(int n, int r) {

		int n_factorial = 1;

		for(int i=n;i>0;i--) {
			n_factorial = n_factorial * i;
		}

		int r_factorial = 1;

		for(int i=r;i>0;i--) {
			r_factorial = r_factorial * i;
		}

		int n_minus_r_factorial = 1;

		for(int i=(n-r);i>0;i--) {
			n_minus_r_factorial = n_minus_r_factorial * i;
		}

		return n_factorial / (r_factorial * n_minus_r_factorial);
	}

	private static int calculateTotalPts(int[] scores) {
		int startMax = scores.length - 1;
		int totalPts = 0;

		for(int i=startMax;i>0;i--) {
			totalPts += i;
		}

		return totalPts;
	}
}
