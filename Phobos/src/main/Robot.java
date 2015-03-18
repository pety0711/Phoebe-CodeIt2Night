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
		
	}
	
	public Robot(String id, Arena a) {
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
	}
	
	/**
	 * Put oil.
	 */
	public void putOil(){
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
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
	 * Change fields.
	 *
	 * @param s the s
	 */
	public void changeFields(SafeZone s){
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	
}
