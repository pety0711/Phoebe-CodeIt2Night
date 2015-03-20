/**
 * 
 */
package main;

import java.awt.Point;//Coord
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
	private List<Field> neighbours;
	
	/** The robots. */
	private List<Robot> robots;
	
	/** The arena. */
	private Arena arena;
	
	/** The coord. */
	private CoordVector coord;
	
	/**
	 * Gets the robot id.
	 *
	 * @return the robot id
	 */
	public String getRobotId() {
		//TODO
		return null;
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
	 * Change neighbour.
	 *
	 * @param from the from
	 * @param to the to
	 */
	public void changeNeighbour(SafeZone from, SafeZone to) {
		//TODO
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
	
	
}
