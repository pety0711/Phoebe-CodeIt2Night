/**
 * 
 */
package main;

import java.util.Vector;

/**
 * @author Dávid
 *
 */
public class SafeZone extends Field {

	/* (non-Javadoc)
	 * @see main.Field#steppedOnYou(main.Robot)
	 */
	@Override
	public void steppedOnYou(Robot r) {
		// TODO Auto-generated method stub
	}

	public void investigateCollision() {
	}
	
	/**
	 * Change to putty.
	 */
	public void addPutty(Putty p) {
	}
	
	public void addOil(Oil o) {
	}
	
	public Field step(Vector speed) {
		return null;
	}
}
