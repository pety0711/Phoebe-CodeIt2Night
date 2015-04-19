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

	/** The neighbours. Important:
	 * 
	 * neighbours[0] is the neighbour at direction left
	 * neighbours[1] is the neighbour at direction up
	 * neighbours[2] is the neighbour at direction right
	 * neighbours[3] is the neighbour at direction down
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

	enum direction{
		up,
		down,
		right,
		left
	};
	
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
	public abstract Field step(CoordVector speed, Robot r);

	/**
	 * Stepped on you.
	 *
	 * @param r
	 *            the r
	 */
	public abstract void steppedOnYou(Robot r);
	
	public abstract void steppedOffYou(Robot r); 

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
	 * Tells the direction from the coordinates
	 * @param coord - the coordinates that describes the direction
	 * @return the direction translated from the coord parameter, if unknown return with null
	 */
	private direction getDirection(CoordVector coord){
		if(coord == new CoordVector( 1, 0)){return direction.right;}
		if(coord == new CoordVector(-1, 0)){return direction.left;}
		if(coord == new CoordVector( 0, 1)){return direction.up;}
		if(coord == new CoordVector( 0,-1)){return direction.down;}
		return null;
	}
	
	/**
	 * Gets the neighbour.
	 *
	 * @param direction
	 *            the direction
	 * @return the neighbour
	 */
	public Field getNeighbour(direction dir) throws Throwable{
		Field temp = null;
		switch (dir) {
		case left:
			temp=neighbours.get(0);
			break;
		case up:
			temp=neighbours.get(1);
			break;
		case right:
			temp=neighbours.get(2);
			break;
		case down:
			temp=neighbours.get(3);
			break;

		default:
			throw new Throwable("Unknown direction");
		}
		
		return temp;
	}

	/**
	 * Sets the coord.
	 *
	 * @param c
	 *            the new coord
	 */
	public void setCoord(CoordVector coord) {		//R
		this.coord = coord;
	}

	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public CoordVector getCoord() {		//R
		return coord;
	}
}
