package main;

public class CoordVector {
	public static final int dimension = 2;
	private int[] points;
	
	public CoordVector() {
		points = new int[dimension];
	}
	
	public CoordVector(int[] arg0) throws Exception {
		if(arg0.length == dimension) {
			points = arg0;
		}
		else throw new Exception("arg0's dimension is not correct!");
	}
	
}
