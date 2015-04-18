/**
 * 
 */
package main;
// TODO: Auto-generated Javadoc

/**
 * The Class DangerZone.
 *
 * @author Dávid
 */
public class DangerZone extends Field {

	/** The type. */
	private int type;
	
	/**
	 * Instantiates a new danger zone.
	 */
	public DangerZone() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new danger zone.
	 *
	 * @param id the id
	 */
	public DangerZone(String id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see main.Field#steppedOnYou(main.Robot)
	 */
	@Override
	public void steppedOnYou(Robot r) {
		Skeleton.printLastCalledFunction(id,new String[]
				{r.id,Skeleton.getClassName(r)});
		robots.add(r);
		r.killRobot();
	}

	/* (non-Javadoc)
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
	@Override
	public Field step(CoordVector speed, Robot r) {
		return this;
	}

	/* (non-Javadoc)
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see main.Field#getNeighbour(main.CoordVector)
	 */
	@Override
	public Field getNeighbour(CoordVector direction) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see main.Field#initField()
	 */
	@Override
	public void initField() {
		Skeleton.printLastCalledFunction(id);
	}
}
