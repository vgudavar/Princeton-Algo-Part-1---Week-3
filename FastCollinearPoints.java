package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	   private ArrayList<LineSegment> segs = new ArrayList<LineSegment>();	
	   public FastCollinearPoints(Point[] points)  {   // finds all line segments containing 4 or more points 
	        int l = points.length;
	        Point[] pts = points.clone(); 
	        for(int i = 0; i < l;i++){ 
	        	helper(points,pts[i]); 
	        }  
		   
	   }
	   private void helper(Point[] points, Point P){
		   ArrayList<Point> temp = new ArrayList<Point>();
		   Arrays.sort(points, 0, points.length, P.slopeOrder());
		   for(int i = 1; i < points.length-1; i++){
				if(P.slopeTo(points[i]) == P.slopeTo(points[i+1])){
					if(temp.size() == 0){
						temp.add(points[i]);
						temp.add(points[i+1]);
					}
					else{
						if(P.slopeTo(temp.get(temp.size()-1)) == P.slopeTo(points[i])){
							temp.add(points[i+1]);
						}
						else{
							if(temp.size() >= 3){
								temp.add(P);
								Collections.sort(temp);
								if(temp.get(0) == P){
									segs.add(new LineSegment(temp.get(0), temp.get(temp.size()-1)));
								}
								temp.clear();
								temp.add(points[i]);
								temp.add(points[i+1]);
							}
							else{
								temp.clear();
								temp.add(points[i]);
								temp.add(points[i+1]);
							}
						}
								
					}
					
				}
			}
			if(temp.size() >= 3){
				temp.add(P);
				Collections.sort(temp);
				if(temp.get(0) == P){
					segs.add(new LineSegment(temp.get(0), temp.get(temp.size()-1)));
				}
					temp.clear();
			}
			else{
				temp.clear();
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
			In in = new In("C:\\Users\\vgudavar\\Desktop\\Data_Mining_Python\\Coursera_look_courses\\Priceton_Algoithsm-1\\Test\\Algorithms\\src\\collinear\\input6.txt");
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
			FastCollinearPoints collinear = new FastCollinearPoints(points);
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
