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
		Skeleton.printLastCalledFunction(id);
		
	}
	
	public Robot(String id, Arena a) {
		
		
		this.id = id;
		isItAlive = true;
		arena = a;
		
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		
		speed = new CoordVector();
		Skeleton.printLastCalledFunction(id,new String[]{id,Skeleton.getClassName(this), "a", Skeleton.getClassName(a)});
	}
	
	/**
	 * Put putty.
	 */
	public void putPutty(){
		Skeleton.printLastCalledFunction(id);
		Putty putty = new Putty("p");
		field.addPutty(putty);
	}
	
	/**
	 * Put oil.
	 */
	public void putOil(){
		Skeleton.printLastCalledFunction(id);
		Oil oil = new Oil("o");
		field.addOil(oil);
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
		Skeleton.printLastCalledFunction(id);
		try {
			speed = new CoordVector(new int[]{2,0});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		field.step(speed);
	}
	
	/**
	 * Investigate collision.
	 *
	 */
	public void investigateCollision(){
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
		Skeleton.printLastCalledFunction(id);
	}

	/**
	 * Disable movement.
	 */
	public void disableMovement(){
		Skeleton.printLastCalledFunction(id);
	}

	/**
	 * Kill robot.
	 */
	public void killRobot(){
		Skeleton.printLastCalledFunction(this.id);
		arena.killRobot(points, id);
	}

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		Skeleton.printLastCalledFunction(id);
		return points;
	}
	
	public Field getField() {
		Skeleton.printLastCalledFunction(id);
		return field;
	}

	public void setField(Field field) {
		Skeleton.printLastCalledFunction(id);
		this.field = field;
	}

	
}
