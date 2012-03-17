package luckynumbers;

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
            solution.processTest(in);
        }
        
        in.close();
	}

	private void processTest(BufferedReader in) throws Exception {
		String[] data = in.readLine().split("\\s+");
		assert(data.length == 2);
		
		long start = Long.parseLong(data[0]);
		long end = Long.parseLong(data[1]);
		
		int luckyCtr = 0;
		
		for(long i=start;i<=end;i++) {
			if(lucky(String.valueOf(i))) {
				luckyCtr++;
			}
		}
		
		System.out.println(luckyCtr);
	}

	private boolean lucky(String value) {
	
		long sum = 0;
		long sumSquares = 0;
		
		for(int i=0;i<value.length();i++) {
			long charVal = Long.parseLong(String.valueOf(value.charAt(i)));
			sum += charVal;
			
			sumSquares += charVal * charVal;
		}
		
		if(isPrime(sum) && isPrime(sumSquares)) {
			return true;
		}
		
		return false;
	}
	 
	private boolean isPrime(long num) {
		return BigInteger.valueOf(num).isProbablePrime(10);
	}

}
