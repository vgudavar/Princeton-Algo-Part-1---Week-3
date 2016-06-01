package collinear;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private ArrayList<LineSegment> segs = new ArrayList<LineSegment>();
	public BruteCollinearPoints(Point[] points) {
	if(points == null) throw new java.lang.NullPointerException();
	else{
		int l = points.length;
		for(int p = 0; p < l; p++){
			if(points[p] == null) throw new java.lang.NullPointerException();
			else{
				for(int q = p+1; q < l; q++){
					if(points[q] == null) throw new java.lang.NullPointerException();
					else{
						for(int r = q+1; r < l; r++){
							if(points[r] == null) throw new java.lang.NullPointerException();
							else{
								if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])){
									for(int s = r+1; s < l; s++){
										if(points[s] == null) throw new java.lang.NullPointerException();
										else{
											if(points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])){
												ArrayList<Point> temp = new ArrayList<Point> (4);
												temp.add(points[p]);
												temp.add(points[q]);
												temp.add(points[r]);
												temp.add(points[s]);
												Collections.sort(temp);
												// System.out.println("Point P is : " + points[p] + " Point Q is : " + points[q]  + " Point R is : " + points[r] + " Point S is : " + points[s]);
												segs.add(new LineSegment(temp.get(0), temp.get(3)));
											}
										}
									}
							    }
							}
						}
					}
				}
			}
		}
	  }
	}


	
	public int numberOfSegments() {
		return segs.size();
	}
	
	public LineSegment[] segments() {
		return segs.toArray(new LineSegment[segs.size()]);
	}
	
	
	public static void main(String[] args) {

		// read the N points from a file
		In in = new In("C:\\Users\\vgudavar\\Desktop\\Data_Mining_Python\\Coursera_look_courses\\Priceton_Algoithsm-1\\Test\\Algorithms\\src\\collinear\\input8.txt");
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.show(0);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.03);
		for (Point p : points) {
        p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		StdDraw.show(0);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenColor(StdDraw.BLUE);
    	StdDraw.setPenRadius(0.0005);
    	for (LineSegment segment : collinear.segments()) {
    		StdOut.println(segment);
    		segment.draw();
    	}
    	
    	System.out.println(" Number of Segments : " + collinear.numberOfSegments());
    	StdDraw.show();
}
	
}
