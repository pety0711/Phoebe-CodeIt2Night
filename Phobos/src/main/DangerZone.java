/**
 * 
 */
package main;

import java.util.List;

/**
 * @author Dávid
 *
 */
public class DangerZone extends Field {

	private int type;
	
	@Override
	public void steppedOnYou(Robot r) {
		// TODO Auto-generated method stub
		robots.add(r);
		r.killRobot();
	}

	@Override
	public Field step(CoordVector speed) {
		List<Field> choose = this.getNeighbours();
		
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void investigateCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Field getNeighbour(CoordVector direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void steppedOffYou(Robot r) {
		// TODO Auto-generated method stub
		
	}
}
