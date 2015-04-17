/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List; 

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
	protected List<Robi> robots;  //Sztem ez jobb protectedbe, igaz?
	
	/** The patches. */
	protected ArrayList<Patch> patches;
	
	/** The arena. */
	protected Arena arena;
	
	/** The coord. */
	protected CoordVector coord;
	
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
	
	/**
	 * Step.
	 *
	 * @param speed the speed
	 * @param r the r
	 * @return the field
	 */
	public abstract Field step(CoordVector speed, Robi r);
	
	/**
	 * Stepped on you.
	 *
	 * @param r the r
	 */
	public abstract void steppedOnYou(Robi r);
	
	/**
	 * Stepped off you.
	 *
	 * @param r the r
	 */
	public abstract void steppedOffYou(Robi r);
	
	/**
	 * Investigate collision.
	 *
	 * @throws Exception the exception
	 */
	public abstract void investigateCollision() throws Exception;
	
	/**
	 * Instantiates a new field.
	 */
	public Field() {
		patches = new ArrayList<Patch>();
		robots = new ArrayList<Robi>();
		// this.id = id; Ez kell bele?
	}
	
	/**
	 * Inits the field.
	 */
	public abstract void initField();
	
	/**
	 * Adds the putty.
	 *
	 * @param p the p
	 */
	public void addPutty(Putty p) {
		Skeleton.printLastCalledFunction(id,new String[]{p.id,
				Skeleton.getClassName(p)});
		patches.add(p);
	}
	
	/**
	 * Adds the oil.
	 *
	 * @param o the o
	 */
	public void addOil(Oil o) {
		Skeleton.printLastCalledFunction(id,new String[]{o.id,
				Skeleton.getClassName(o)});
		patches.add(o);
	}
	
	/**
	 * Gets the neighbours.
	 *
	 * @return the neighbours
	 */
	public List<Field> getNeighbours() {
		Skeleton.printLastCalledFunction(id);
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
	public abstract Field getNeighbour(CoordVector direction);/* {
		Skeleton.printLastCalledFunction(id,new String[]{"direction",Skeleton.getClassName(direction)});
		return null;
	}*/

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
}
