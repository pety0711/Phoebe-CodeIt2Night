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

	private ArrayList<Patch> patches;
	
	public SafeZone() {
		id = "";
		patches = new ArrayList<Patch>();
	}
	
	public SafeZone(String id) {
		this.id = id; 
		patches = new ArrayList<Patch>();
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
	
	/**
	 * Change to putty.
	 */
	public void addPutty(Putty p) {
		patches.add(p);
	}
	
	public void addOil(Oil o) {
		patches.add(o);
	}
	
	public Field step(Vector speed) {
		return null;
	}
}
