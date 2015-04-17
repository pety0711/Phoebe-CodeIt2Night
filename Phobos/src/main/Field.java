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

public abstract class Field {

	/** The neighbours. 
	 * 
	 * Important!
	 * neighbours[0] is the neighbour at direction [-1; 0]
	 * neighbours[1] is the neighbour at direction [ 0; 1]
	 * neighbours[2] is the neighbour at direction [ 1; 0]
	 * neighbours[3] is the neighbour at direction [ 0;-1]
	 * 
	 * */
	protected List<Field> neighbours;

	/** The robots. */
	protected List<Robi> robots;

	/** The patches. */
	protected ArrayList<Patch> patches;

	/** The arena. */
	protected Arena arena;

	/** The coord. */
	protected CoordVector coord;

	/** The id. */
	public String id;

	
	
	/**
	 * Instantiates a new field.
	 */
	public Field() {
		patches = new ArrayList<Patch>();
		robots = new ArrayList<Robi>();
	}
	
	/**
	 * The method returns with the List of the IDs of Robots on the field
	 * @return the List of the IDs
	 */
	public List<String> getRobotId() {		//R
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < robots.size(); i++) {
			temp.add(robots.get(i).id);
		}
		return temp;
	}

	/**
	 * Step.
	 *
	 * @param speed
	 *            the speed
	 * @param r
	 *            the r
	 * @return the field
	 */
	public abstract Field step(CoordVector speed, Robi r);

	/**
	 * Stepped on you.
	 *
	 * @param r
	 *            the r
	 */
	public abstract void steppedOnYou(Robi r);

	/**
	 * Investigate collision.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public abstract void investigateCollision() throws Exception;



	/**
	 * Inits the field.
	 */
	public abstract void initField();

	/**
	 * Adds the putty.
	 *
	 * @param p
	 *            the putty that we add to the patches List
	 */
	public void addPutty(Putty p) {			//R
		patches.add(p);
	}

	/**
	 * Adds the oil.
	 *
	 * @param o
	 *            the oil that we add to the patches List
	 */
	public void addOil(Oil o) {				//R
		patches.add(o);
	}

	/**
	 * Gets the neighbours of the field.
	 *
	 * @return the List of neighbours.
	 */
	public List<Field> getNeighbours() {	//R
		return neighbours;
	}

	/**
	 * Sets the neighbours.
	 *
	 * @param fields
	 *            the new neighbours
	 */
	public void setNeighbours(List<Field> fields) {		//R
		neighbours = fields;
	}

	/**
	 * Gets the neighbour.
	 *
	 * @param direction
	 *            the direction
	 * @return the neighbour
	 */
	public abstract Field getNeighbour(CoordVector direction);

	/**
	 * Sets the coord.
	 *
	 * @param c
	 *            the new coord
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
