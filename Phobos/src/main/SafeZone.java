/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Dávid
 *
 */
public class SafeZone extends Field {

	private String id;

	
	
	public SafeZone() {
		
		id = "";
		
	}
	
	public SafeZone(String id) {
		this.id = id;
		Skeleton.printLastCalledFunction(id, new String[]{""});
	}
	
	/* (non-Javadoc)
	 * @see main.Field#steppedOnYou(main.Robot)
	 */
	@Override
	public void steppedOnYou(Robot r) {
		// TODO függvénykiiratás
		
		if(patches.size() > 0) {
			if(patches.get(0).getClass().equals(Oil.class)) {
				r.disableMovement();
			} else {
				r.slowDown();
			}
		}
		
		// TODO Auto-generated method stub
	}

	public void investigateCollision() {
		
	}
	
	
	public Field step(Vector speed) {
		return null;
	}
}
