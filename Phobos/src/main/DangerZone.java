/**
 * 
 */
package main;

/**
 * The Class DangerZone.
 *
 * @author Dávid
 */
public class DangerZone extends Field {
	
	/**
	 * Instantiates a new danger zone.
	 */
	public DangerZone() {
		super();
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
		robots.add(r);
		this.sethasChanged(true);				
		r.killRobot();
	}

	/**
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
	@Override
	public Field step(CoordVector speed, Robot r) {
		return this;
	}

	/**
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision() {
		
	}

	@Override
	public void steppedOffYou(Robot r) {
		Prototype.printOut("You can't step when you are on a DangerZone! :'( ");
	}
}
