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
	
	public String id;
	private CoordVector speed;
	private int points;
	int puttySupply;
	int oilSupply;
	boolean isItAlive;
	Field field;
	Arena arena;
	
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

	public void putPutty(){
		Skeleton.printLastCalledFunction(id);
		Putty putty = new Putty("p");
		field.addPutty(putty);
		arena.registerPatch(putty);
	}
	
	public void putOil(){
		Skeleton.printLastCalledFunction(id);
		Oil oil = new Oil("o");
		field.addOil(oil);
		arena.registerPatch(oil);
	}
	
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
	
	public void investigateCollision(){
		Skeleton.printLastCalledFunction(id);
	}
	
	public void detectCollision(CoordVector coord){
		
	}
	
	public CoordVector getSpeed(){
		return speed;
	}
	

	public void setSpeed(CoordVector speed){
		this.speed = speed;
	}

	public void slowDown(){
		Skeleton.printLastCalledFunction(id);
	}

	public void disableMovement(){
		Skeleton.printLastCalledFunction(id);
	}

	public void killRobot(){
		Skeleton.printLastCalledFunction(this.id);
		arena.killRobot(points, id);
	}

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
