/**
 * 
 */
package main;

import java.awt.Point;//Coord
import java.util.ArrayList;
import java.util.List;
import java.util.Vector; 

// TODO: Auto-generated Javadoc
/**
 * The Class Field.
 *
 * @author Dávid
 */
public abstract class  Field {
	 
	/** The neighbours. */
	protected List<Field> neighbours; //Lehet ez is protected? Privat volt alapból.
	
	/** The robots. */
	protected List<Robot> robots;  //Sztem ez jobb protectedbe, igaz?
	
	/** The Patches */
	protected ArrayList<Patch> patches;
	
	/** The arena. */
	private Arena arena;
	
	/** The coord. */
	private CoordVector coord;
	
	/** The id. */
	public String id;   //Ez a field neve vagy mi lesz??
	
	/**
	 * Gets the robot id.
	 *
	 * @return the robot id
	 */
	public String getRobotId() {
		
		
		return robots.get(0).id;
	}
	
	public abstract Field step(CoordVector speed);
	
	public Field() {
		patches = new ArrayList<Patch>();
		// this.id = id; Ez kell bele?
	}



	/**
	 * Change to putty.
	 */
	public void addPutty(Putty p) {
		patches.add(p);
		patches = new ArrayList<Patch>();
	}
	
	public void addOil(Oil o) {
		patches.add(o);
	}
	
	/**
	 * Stepped on you.
	 *
	 * @param r the r
	 */
	public abstract void steppedOnYou(Robot r);
	
	/**
	 * Gets the neighbours.
	 *
	 * @return the neighbours
	 */
	public List<Field> getNeighbours() {
		return neighbours;
	}
	
	/**
	 * Sets the neighbours.
	 *
	 * @param fields the new neighbours
	 */
	public void setNeighbours(List<Field> fields) {
		neighbours = fields;
	}
	
	/**
	 * Gets the neighbour.
	 *
	 * @param direction the direction
	 * @return the neighbour
	 */
	public Field getNeighbour(CoordVector direction) {
		//TODO

		return null;
	}
	
	/**
	 * Sets the coord.
	 *
	 * @param c the new coord
	 */
	public void setCoord(CoordVector c) {
		coord = c;
	}
	
	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public CoordVector getCoord() {
		return coord;
	}
	public abstract void investigateCollision();
	
}
