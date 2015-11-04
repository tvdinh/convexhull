package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * The <tt>SetGenerator</tt> class is to generate random 2D-point sets
 * and export it to a file with a suitable format that can be used as 
 * inputs for <tt>ConvexHull</tt>.
 * <p>
 * Run:
 * <tt>java SetGenerator [export_file] N lb ub</tt>
 * <p>
 * where:
 * <li><tt>export_file</tt> is the file to which the set will be exported.</li> 
 * <li><tt>N</tt> is the cardinality of the set</li>
 * <li><tt>lb</tt> is the lower bound of coordinates</li>
 * <li><tt>ub</tt> is the upper bound of coordinates</li>
 */
public class SetGenerator {

	private static final int DEFAULT_N = 100;
	private static final int DEFAULT_LOWER_BOUND  = -10;
	private static final int DEFAULT_UPPER_BOUND = 10;
	
	/**
     * main method to generate random set  
     * @param list of arguments  
     */
	public static void main(String[] args) {
		String fileName = String.format("Set_%d.txt",DEFAULT_N); 
		if (args.length > 0) fileName = args[0];
		try {
			int N  = Integer.parseInt(args[1]);
			int lp = Integer.parseInt(args[2]);
			int up = Integer.parseInt(args[3]); 
			generateRandomSet(fileName,lp,up,N);
		} catch (IndexOutOfBoundsException e) {
			generateRandomSet(fileName,DEFAULT_LOWER_BOUND,DEFAULT_UPPER_BOUND,DEFAULT_N);
		}
	}

    /**
     * Generate a random set with a given cardinality and
     * output to a file 
     * @param String filename: name of file to output 
     * @param upperBound;
     * @param lowerBound;
     * @param set's cardinality;
     */
	public static void generateRandomSet(String fn, int upperBound, int lowerBound, int N) {
	
		if (!sanityCheck(lowerBound, upperBound)) return;
		File fout   = new File(fn);
		Random rand = new Random(); 
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new  OutputStreamWriter(fos));
			
			bw.write(Integer.toString(N));
			
			for (int i = 0; i < N; i++) {
				bw.newLine();
				double x = rand.nextDouble()*(upperBound - lowerBound + 1)  + lowerBound;
				double y = rand.nextDouble()*(upperBound - lowerBound + 1)  + lowerBound;
				bw.write(String.format("%.3f",x)  + ", " + String.format("%.3f", y));
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**/
	private static boolean sanityCheck(int lowerBound, int upperBound) {
		return ((lowerBound <= upperBound));
	}
}
