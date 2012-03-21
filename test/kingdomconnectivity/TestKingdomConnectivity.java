package kingdomconnectivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestKingdomConnectivity {

	@Test
	public void testCaseOne() throws Exception {
		InputStream input = TestKingdomConnectivity.class.getResourceAsStream("input00.txt");
			
		Solution kc = new Solution(input);
		kc.go();
		
		String[] output = getOutput("output00.txt");
		
		Assert.assertArrayEquals(output, kc.getOutput());
	}
	
	@Test
	public void testCaseTwo() throws Exception {
		InputStream input = TestKingdomConnectivity.class.getResourceAsStream("input01.txt");
			
		Solution kc = new Solution(input);
		kc.go();
		
		String[] output = getOutput("output01.txt");
		
		Assert.assertArrayEquals(output, kc.getOutput());
	}
	
	@Test
	public void testCaseThree() throws Exception {
		InputStream input = Solution.class.getResourceAsStream("input02.txt");
			
		Solution kc = new Solution(input);
		kc.go();
		
		String[] output = getOutput("output02.txt");
		
		Assert.assertArrayEquals(output, kc.getOutput());
	}
	
	@Test
	public void testCaseFour() throws Exception {
		
		long start = System.currentTimeMillis();
		
		InputStream input = Solution.class.getResourceAsStream("input03.txt");
			
		Solution kc = new Solution(input);
		kc.go();
		
		long time = System.currentTimeMillis() - start;
		System.out.println("Time :" + time + "ms");
		
		String[] output = getOutput("output03.txt");
		
		Assert.assertArrayEquals(output, kc.getOutput());
	}


	private String[] getOutput(String output) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(TestKingdomConnectivity.class.getResourceAsStream(output)));
		List<String> outputs = new ArrayList<String>();
		String out;
		while((out = br.readLine()) != null) {
			outputs.add(out);
		}
		
		br.close();
		return outputs.toArray(new String[]{});
	}
	
}
