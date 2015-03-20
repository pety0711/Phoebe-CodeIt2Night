/**
 * 
 */
package main;

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Robot.
 *
 * @author Dávid
 */
public class Robot {
	
	/** The id. */
	public String id;
	
	/** The speed. */
	private CoordVector speed;
	
	/** The points. */
	private int points;
	
	/** The putty supply. */
	int puttySupply;
	
	/** The oil supply. */
	int oilSupply;
	
	/** The is it alive. */
	boolean isItAlive;
	
	/** Robot stands on this field */
	Field field;

	/** The arena. */
	Arena arena;
	
	
	/** Default Constructor */
	public Robot() {
		Skeleton.printLastCalledFunction(id,new String[]{});
		
	}
	
	public Robot(String id, Arena a) {
		Skeleton.printLastCalledFunction(id,new String[]{id, a.toString()});
		
		this.id = id;
		isItAlive = true;
		arena = a;
		
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		
		speed = new CoordVector();
	}
	
	/**
	 * Put putty.
	 */
	public void putPutty(){
		Skeleton.printLastCalledFunction(id,new String[]{});
		Putty putty = new Putty();
		field.addPutty(putty);
	}
	
	/**
	 * Put oil.
	 */
	public void putOil(){
		Skeleton.printLastCalledFunction(id,new String[]{});
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
		Skeleton.printLastCalledFunction(id,new String[]{});
	}
	
	/**
	 * Investigate collision.
	 *
	 * @param coord the coord
	 */
	public void investigateCollision(Vector coord){
	}
	
	/**
	 * Detect collision.
	 *
	 * @param coord the coord
	 */
	public void detectCollision(Vector coord){
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public void getSpeed(){
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(Vector speed){
	}
	
	/**
	 * Slow down.
	 */
	public void slowDown(){
	}

	/**
	 * Disable movement.
	 */
	public void disableMovement(){
	}

	/**
	 * Kill robot.
	 */
	public void killRobot(){
	}

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		Skeleton.printLastCalledFunction(id,new String[]{});
		return points;
	}
	
	public Field getField() {
		Skeleton.printLastCalledFunction(id,new String[]{});
		return field;
	}

	public void setField(Field field) {
		Skeleton.printLastCalledFunction(id,new String[]{});
		this.field = field;
	}

	
}
