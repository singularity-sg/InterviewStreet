package directconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import junit.framework.Assert;

import org.junit.Test;

public class TestSolution {

	private static final int NO_OF_TEST = 5;

	@Test
	public void testSolution() throws Exception {

		System.out.println("--- Generating file---");
		File file = generateFile();
		System.out.println("--- Generating file completed ---");

		// System.out.println("--- Test case ---");
		// BufferedReader br = new BufferedReader(new FileReader(file));
		// String line = null;
		// while((line = br.readLine()) != null) {
		// System.out.println(line);
		// }
		// br.close();
		//
		System.out.println("--- Starting Test ---");

		System.setIn(new FileInputStream(file));

		Solution.main(new String[] {});

		System.out.println("--- Test ended ---");

	}

	@Test
	public void testSimpleSolution() throws Exception {

		System.out.println("--- Generating file---");
		File file = generateSimpleFile();
		System.out.println("--- Generating file completed ---");

		System.out.println("--- Starting Test ---");

		System.setIn(new FileInputStream(file));

		Solution.main(new String[] {});

		System.out.println("--- Test ended ---");
		System.out.println("Is answer 1) 2200 and 2) 800 ?");

	}

	private File generateSimpleFile() throws Exception {

		File file = new File("testcase.txt");
		FileWriter fw = new FileWriter(file);
		fw.write("2\n");
		fw.write("3\n");
		fw.write("2 4 6\n");
		fw.write("10 100 1000\n");
		fw.write("2\n");
		fw.write("2 10\n");
		fw.write("10 100 \n");
		fw.flush();
		fw.close();

		return file;
	}

	private File generateFile() throws Exception {

		File file = new File("testcase.txt");

		FileWriter fw = new FileWriter(file);

		fw.write(String.valueOf(NO_OF_TEST) + "\n");

		for (int i = 1; i <= NO_OF_TEST; i++) {
			int noOfCities = (int) Math.pow(10.0, (double) i);

			fw.write(String.valueOf(noOfCities) + "\n");

			StringBuilder pos = new StringBuilder();
			for (int j = 1; j <= noOfCities; j++) {
				pos.append(
						String.valueOf((int) (Math.random() * 10 * noOfCities)))
						.append(" ");
			}
			pos.delete(pos.length() - 1, pos.length());
			pos.append("\n");

			fw.write(pos.toString());

			StringBuilder pop = new StringBuilder();
			for (int j = 1; j <= noOfCities; j++) {
				pop.append(String.valueOf((int) (Math.random() * 10000)))
						.append(" ");
			}
			pop.delete(pop.length() - 1, pop.length());
			pop.append("\n");

			fw.write(pop.toString());
		}
		fw.flush();
		fw.close();

		return file;
	}

	@Test
	public void testString() {
		String plain = "HELLO";

		char[] alphabet = new char[] { 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] plainChars = plain.toCharArray();
		char[] encryptedChars = new char[plainChars.length];
		for(int i=0;i<encryptedChars.length;i++) {
		    for(int j=0;j<alphabet.length;j++) {
		        if(plainChars[i] == alphabet[j]) {
		             if(j >= 10) 
			          encryptedChars[i] = alphabet[(j-10)%alphabet.length];
		             else
		                 encryptedChars[i] = alphabet[j + alphabet.length - 10];
		             }
		        }
		    }
		}

		//return String.valueOf(encryptedChars);

		Assert.assertEquals(String.valueOf(encryptedChars), "ROVVY");

	}
}
