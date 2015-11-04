package Models;

import java.util.Comparator;

/**
 * The <tt>Point2D<tt> class presents a two-dimensional point.
 * <p>
 */
public class Point2D {

	public final Comparator<Point2D> POLAR_ORDER         = new PolarOrder();
	public static final Comparator<Point2D> Y_ORDER 	 = new Y_Order();
	
	private final double x;
	private final double y;
	
	/**
	 * Initializes a point with a given x,y
	 * @param x1
	 * @param y1
	 */
	public Point2D(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
    /**
     * Accessor method to get the x-coordinate
     * @return the x-coordinate
     */
	public double getX() { return x;} 
	
    /**
     * Accessor method to get the y-coordinate
     * @return the y-coordinate
     */
	public double getY() { return y;}
	
    /**
     * Calculate the Euclidean distance to a given point
     * @param  Point2D toPoint
     * @return Euclidean distance to the given point
     */
	public double EDistanceTo(Point2D toPoint) { 
		return Math.sqrt( (x - toPoint.getX())*(x - toPoint.getX()) 
				+ (y - toPoint.getY())*(y - toPoint.getY()) );
	}

    /**
     * Return a String in (x,y) format
     * @return String in (x,y) format
     */
	public String showPointXY() { return "(" + x + "," + y + ")";}
	
	
    /**
     * Return a String in x,y format
     * @return String in x,y format
     */
	public String exportPoint() { return  x + "," + y;}
	
    /**
     * Return the order of a->b->c in terms of clockwise, counter-clockwise
     * or collinear.
     * <p>
     * This is the key method to implement 
     * <a href="https://en.wikipedia.org/wiki/Graham_scan">Graham scan</a>.
     * The code for this method is obtained from Algorithms, 4th Edition, Sedgewick et al. 
     * @param a;
     * @param b;
     * @param c;
     *  
     */
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		double area2 = (b.x - a.x)*(c.y-a.y) - (b.y - a.y)*(c.x-a.x);
		if (area2 < 0) 		return -1;  		//clockwise
		else if (area2 > 0) return +1;			//counter-clockwise
		else 				return 0;		    //collinear
	}
	
    /**
     * a Comparator to compare 2 points (Point2D) with respect to
     * the polar angle to this point
     */
	private class PolarOrder implements Comparator<Point2D> {
		/**
		 * To return an order between two points with respect to
		 * the polar angles to this point 
		 * @param Point2D point 1
	     * @param Point2D point 2 
	     * @author: Robert Sedgewick & Kevin Wayne
		 */
		@Override
		public int compare(Point2D o1, Point2D o2) {
			double dy1 = o1.y -y;
			double dy2 = o2.y -y;
			
			if 		(dy1 == 0 && dy2 == 0) { return 0; }
			else if (dy1 >=0  && dy2  < 0) return -1;
			else if (dy2 >=0  && dy1  < 0) return 1;
			else return -ccw(Point2D.this, o1, o2);
		}
	}
	
    /**
     * a Comparator to compare 2 points (Point2D) with respect to
     * the y-coordinator 
     */
	private static class Y_Order implements Comparator<Point2D> {
		/**
		 * To return an order between two points with respect to
		 * the y-coordinator (height) 
		 * @param Point2D point 1
	     * @param Point2D point 2 
		 */
		@Override
		public int compare(Point2D o1, Point2D o2) {
			if (o1.y == o2.y) return 0;
			else if (o1.y < o2.y) return -1;
			else return 1;
		}
	}
}
