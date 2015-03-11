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
	private Point coord;
	
	/**
	 * Gets the robot id.
	 *
	 * @return the robot id
	 */
	public String getRobotId() {
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
		return null;
	}
	
	/**
	 * Sets the neighbours.
	 *
	 * @param fields the new neighbours
	 */
	public void setNeighbours(List<Field> fields) {
	}
	
	/**
	 * Gets the neighbour.
	 *
	 * @param direction the direction
	 * @return the neighbour
	 */
	public Field getNeighbour(Vector<?> direction) {
		return null;
	}
	
	/**
	 * Change neighbour.
	 *
	 * @param from the from
	 * @param to the to
	 */
	public void changeNeighbour(SafeZone from, SafeZone to) {
	}
	
	/**
	 * Sets the coord.
	 *
	 * @param c the new coord
	 */
	public void setCoord(Point c) {
	}
	
	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public Point getCoord() {
		return null;
	}
	
	
}
