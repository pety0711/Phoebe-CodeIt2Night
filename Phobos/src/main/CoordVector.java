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
	
	public int getCoordofDim(int dimension){
		return points[dimension-1];
	}
	
	 @Override public String toString(){
		StringBuilder result = new StringBuilder();
		 
	    result.append("{");
	    for (int i = 0; i < points.length; i++) {
	    	result.append(points[i]);
	    	if(i<points.length-1)
	    		result.append(", ");
		}
	    result.append("}");

	    return result.toString();
	 }
}
