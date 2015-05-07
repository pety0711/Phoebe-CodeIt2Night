/**
 * 
 */
package main;

import java.util.List;

import main.Field.direction;

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
		this.setHasChanged(true);				
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
	public Field getNeighbour(direction dir) {
		if (id.toUpperCase().contains("EDGE")) {
			return this;
		}
		return super.getNeighbour(dir);
	}

	@Override
	public void steppedOffYou(Robot r) {
		Prototype.printOut("You can't step when you are on a DangerZone! :'( ");
	}
}
