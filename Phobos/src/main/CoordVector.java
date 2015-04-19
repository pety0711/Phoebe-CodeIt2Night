package main;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordVector.
 */
public class CoordVector {
	
	/** The Constant dimension. */
	public static final int dimension = 2;
	
	/** The points. */
	private int[] points;
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int[] getPoints() {
		return points;
	}

	/**
	 * Sets the points.
	 *
	 * @param points the new points
	 */
	public void setPoints(int[] points) {
		this.points = points;
	}

	/**
	 * Instantiates a new coord vector.
	 */
	public CoordVector() {
		points = new int[dimension];
		for (int i = 0; i < dimension; i++) {
			points[i] = 0;
		}
	}
	
	/**
	 * Instantiates a new coord vector.
	 *
	 * @param arg0 the arg0
	 * @throws Exception the exception
	 */
	public CoordVector(int[] arg0) throws Exception {
		if(arg0.length == dimension) {
			points = arg0;
		}
		else throw new Exception("arg0's dimension is not correct!");
	}
	
	//mostantól érdemes lenne ezeket használni. Egyszerûbb lesz az életünk.
	public CoordVector(int x, int y) {
		points = new int[] {x, y};
	}
	
	public int getX() {
		return points[0];
	}
	
	public int getY() {
		return points[1];
	}
	
	/**
	 * Gets the coordof dim.
	 *
	 * @param dimension the dimension
	 * @return the coordof dim
	 */
	public int getCoordofDim(int dimension){
		return points[dimension-1];
	}
	
	 /* (non-Javadoc)
 	 * @see java.lang.Object#toString()
 	 */
 	@Override 
	 public String toString(){
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		CoordVector cv = (CoordVector) obj;
		for (int i = 0; i < dimension; i++) {
			if (this.points[i] != cv.getPoints()[i])
				return false;
		}
		return true;
	}
	
	public CoordVector getDiff(CoordVector cv) {
		return new CoordVector(cv.getX() - this.getX(), cv.getY() - this.getY());
	}
}
