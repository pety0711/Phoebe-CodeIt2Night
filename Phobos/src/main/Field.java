/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Field.
 *
 * @author Dávid
 */
public abstract class Field {

	/**
	 * The neighbours. Important:
	 * 
	 * neighbours[0] is the neighbour at direction left neighbours[1] is the
	 * neighbour at direction up neighbours[2] is the neighbour at direction
	 * right neighbours[3] is the neighbour at direction down
	 * 
	 * */
	protected List<Field> neighbours;

	/** The robots. */
	protected List<Robot> robots;

	/** The patches. */
	protected ArrayList<Patch> patches;

	/** The arena. */
	protected Arena arena;

	/** The coord. */
	protected CoordVector coord;

	/** The id. */
	public String id;

	/** Has the field changed */
	public boolean hasChanged = true; // Létrehozáskor "változott", utána
										// kirajzolás után kell sztem
										// visszaállítani false-ra

	int nPatches = 0;

	private Boolean haveToAddOil = false;

	private Boolean haveToAddPutty = false;

	/** The possibilities of directions */
	enum direction {
		up, down, right, left
	};

	/**
	 * Instantiates a new field.
	 */
	public Field() {
		patches = new ArrayList<Patch>();
		robots = new ArrayList<Robot>();
	}

	/**
	 * The method returns with the List of the IDs of Robots on the field
	 * 
	 * @return the List of the IDs
	 */
	public List<String> getRobotId() {
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
	 *            the robot that stepped on the Field
	 */
	public abstract void steppedOnYou(Robot r);

	public abstract void steppedOffYou(Robot r);

	/**
	 * Investigate collision.
	 */
	public abstract void investigateCollision();

	/**
	 * Function is called by the Arena, when it's time to add patches
	 */
	public void addPatchesNow() {
		if (haveToAddPutty) {
			Putty putty = new Putty("P" + nPatches++);
			Prototype.printOut("GeneratedPatch -" + putty.id + " Putty - ["
					+ id.toString() + "]");
			patches.add(putty);
			arena.registerPatchCoord(coord);
			haveToAddPutty = false;
		}
		if (haveToAddOil) {
			Oil oil = new Oil("O" + nPatches++);
			Prototype.printOut("GeneratedPatch -" + oil.id + " Putty - ["
					+ id.toString() + "]");
			patches.add(oil);
			arena.registerPatchCoord(coord);
			haveToAddOil = false;
		}
	}

	/**
	 * Function is called by Robots when they want to add a Putty
	 */
	public void addPutty() {
		haveToAddPutty = true;
	}

	/**
	 * Function is called by Robots when they want to add an Oil
	 */
	public void addOil() {
		haveToAddOil = true;
	}

	/**
	 * Gets the neighbours of the field.
	 *
	 * @return the List of neighbours.
	 */
	public List<Field> getNeighbours() {
		return neighbours;
	}

	/**
	 * Sets the neighbours.
	 *
	 * @param fields
	 *            the new neighbours
	 */
	public void setNeighbours(List<Field> fields) {
		neighbours = fields;
	}

	/**
	 * Tells the direction from the coordinates
	 * 
	 * @param coord
	 *            - the coordinates that describes the direction
	 * @return the direction translated from the coord parameter, if unknown
	 *         return with null
	 */
	@SuppressWarnings("unused")
	private direction getDirection(CoordVector coord) {
		if (coord == new CoordVector(1, 0)) {
			return direction.right;
		}
		if (coord == new CoordVector(-1, 0)) {
			return direction.left;
		}
		if (coord == new CoordVector(0, 1)) {
			return direction.up;
		}
		if (coord == new CoordVector(0, -1)) {
			return direction.down;
		}
		return null;
	}

	/**
	 * Gets the neighbour.
	 *
	 * @param direction
	 *            the direction
	 * @return the neighbour
	 */
	public Field getNeighbour(direction dir) {
		Field temp = null;
		switch (dir) {
		case left:
			temp = neighbours.get(0);
			break;
		case up:
			temp = neighbours.get(1);
			break;
		case right:
			temp = neighbours.get(2);
			break;
		case down:
			temp = neighbours.get(3);
			break;

		default:
			return null;
		}

		return temp;
	}

	/**
	 * Gets the patches.
	 *
	 * @return the patches
	 */
	public ArrayList<Patch> getPatches() {
		return patches;
	}

	/**
	 * Sets the patches.
	 *
	 * @param patches
	 *            the new patches
	 */
	public void setPatches(ArrayList<Patch> patches) {
		this.patches = patches;
	}

	/**
	 * Sets the coord.
	 *
	 * @param c
	 *            the new coord
	 */
	public void setCoord(CoordVector coord) {
		this.coord = coord;
	}

	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public CoordVector getCoord() {
		return coord;
	}

	/**
	 * Gets the hasChanged
	 *
	 * @return the hasChanged
	 */
	public boolean getHasChanged() {
		return hasChanged;
	}

	/**
	 * Set the hasChanged
	 *
	 * @param change
	 *            the new hasChanged
	 */
	public void setHasChanged(boolean change) {
		this.hasChanged = change;
	}

	public boolean hasRobi() {
		if (robots.size() > 0
				&& "Robi".equals(robots.get(0).getClass().getSimpleName()))
			return true;
		return false;
	}

	public boolean hasCleaner() {
		if (robots.size() > 0
				&& "CleanerMaster".equals(robots.get(0).getClass()
						.getSimpleName()))
			return true;
		return false;
	}
}
