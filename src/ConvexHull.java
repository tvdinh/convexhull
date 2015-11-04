import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import Models.Point2D;
import Utils.MergeSort;
import Utils.Stack;

/**
 * The <tt>ConvexHull<tt> class is to find the convex hull 
 * of a given set of 2D points. 
 * <p>
 * 
 * This implementation uses 
 * <a href="https://en.wikipedia.org/wiki/Graham_scan">Graham scan</a>.
 * 
 */

public class ConvexHull {

	/**
	 * Find the convex hull of a set (2D points) from an input file
	 */
	public static void main(String[] args) {

		String fileName = "noInput.txt";			//default input
		if (args.length > 0) fileName = args[0];

		Point2D [] set = getInput(fileName);
		showSet(set);

		//Find the lowest point
		//Point2D lowestP = findLowestPoint(set);	
		MergeSort.sort(set, Point2D.Y_ORDER);
		
		//Sort the set by polar angle with respect to the lowest point
		MergeSort.sort(set, set[0].POLAR_ORDER);
		showSet(set);
		
		//Find a convexhull
		//Limitation: At least 3 point, no three points on a line
		if (set.length >= 3) {
			Stack<Point2D> convexhull = new Stack<Point2D>();
			convexhull.push(set[0]);
			convexhull.push(set[1]);
			for (int i = 2; i < set.length; i++) {
				Point2D top = convexhull.pop();
				while(Point2D.ccw(convexhull.peek(),top,set[i]) <= 0) 
					top = convexhull.pop();
				convexhull.push(top);
				convexhull.push(set[i]);
			}
			//Export the convex hull to a file
			String output = "convexhull_" + fileName;
			exportConvexHull(convexhull,output);
		}
	}
	
    /**
     *   
     * @param StackIteration<Point2D>: the convexhull 
     * @param String: output filename  
     */
	public static void exportConvexHull(Stack<Point2D> convexhull, String fout) {
		try {
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter   bw  = new BufferedWriter(new  OutputStreamWriter(fos));
			for (Point2D p : convexhull) { 
				bw.write(p.exportPoint());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     *   
     * @param  Point2D [] set: an array of objects of type Point2D
     * @return Point2D: lowest point 
     */
	public static Point2D findLowestPoint(Point2D [] set) {
		int min =0;
		for (int i=0; i < set.length; i++) {
			if (set[i].getY() < set[min].getY()) min = i;
		}
		return  set[min];
	}

    /**
     * Show all the element in the set 
     * @param Point2D [] set: an array of objects of type Point2D 
     */
	public static void showSet(Point2D [] set) {
		if (set == null) {
			System.out.println("Set is empty!");
			return;
		} else {
			System.out.println("Set contains:");
			for (int i=0; i< set.length; i++) 
				System.out.println(set[i].showPointXY());
		}
	}
	
    /**
     * Initialize the set 
     * @param Point2D [] set: an array of objects of type Point2D 
     * @param String  fileName: is the input filename 
     */
	public static Point2D [] getInput(String fileName) {
		
		Point2D [] set;
		String line;
		
		try {
			FileReader inputFile = new FileReader(fileName);
			BufferedReader br    = new BufferedReader(inputFile);

			line = br.readLine();//The first line tells the cardinality of the set 
			set = new Point2D[Integer.parseInt(line)]; 
			
			//Read all the points in the file
			for (int i=0; i < set.length ; i++) {
				String [] p = br.readLine().split(",");
				set[i] = new Point2D(Double.valueOf(p[0]),Double.valueOf(p[1]));
			}
			br.close();
			return set;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
}
