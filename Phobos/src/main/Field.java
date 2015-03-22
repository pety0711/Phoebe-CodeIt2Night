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
	 
	protected List<Field> neighbours; //Lehet ez is protected? Privat volt alapból.
	protected List<Robot> robots;  //Sztem ez jobb protectedbe, igaz?
	protected ArrayList<Patch> patches;
	protected Arena arena;
	protected CoordVector coord;
	public String id;   //Ez a field neve vagy mi lesz??

	public String getRobotId() {
		return robots.get(0).id;
	}
	
	public abstract Field step(CoordVector speed);
	public abstract void steppedOnYou(Robot r);
	public abstract void investigateCollision();
	
	public Field() {
		patches = new ArrayList<Patch>();
		// this.id = id; Ez kell bele?
	}

	public void addPutty(Putty p) {
		patches.add(p);
	}
	
	public void addOil(Oil o) {
		patches.add(o);
	}
	
	public List<Field> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Field> fields) {
		neighbours = fields;
	}

	public Field getNeighbour(CoordVector direction) {
		//TODO

		return null;
	}

	public void setCoord(CoordVector c) {
		coord = c;
	}

	public CoordVector getCoord() {
		return coord;
	}
}
