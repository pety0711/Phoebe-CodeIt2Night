/**
 * 
 */
package main;

import java.util.Random;

/**
 * The Class Robot.
 *
 * @author Dávid
 */
public class Robot {
	
	public String id;
	public boolean isItAlive;
	private CoordVector speed;
	private int points;
	private int puttySupply;
	private int oilSupply;
	private Field field;
	private Arena arena;
	
	public Robot(Arena a) {
		this.id = "Robot"+new Random(22222222).toString();
		isItAlive = true;
		arena = a;
		
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		
		speed = new CoordVector();
		Skeleton.printLastCalledFunction(id,new String[]
				{id,Skeleton.getClassName(this), "a", Skeleton.getClassName(a)});
	}
	
	public Robot(String id, Arena a) {
		this.id = id;
		isItAlive = true;
		arena = a;
		
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		
		speed = new CoordVector();
		Skeleton.printLastCalledFunction(id,new String[]
				{id,Skeleton.getClassName(this), "a", Skeleton.getClassName(a)});
	}

	public void putPutty(){
		Skeleton.printLastCalledFunction(id);
		Putty putty = new Putty("pr");
		field.addPutty(putty);
		arena.registerPatch(putty);	}
	
	public void putOil(){
		Skeleton.printLastCalledFunction(id);
		Oil oil = new Oil("or");
		field.addOil(oil);
		arena.registerPatch(oil);	}
	
	public void tick(){
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_a_putty||
			Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_an_oil||
			Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_dangerzone||
			Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_safezone){
			Skeleton.drawLine();
		}
		Skeleton.printLastCalledFunction(id);
		
		try {
			speed = new CoordVector(new int[]{2,0});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Field f = field.step(speed,this);
		setField(f);
		f.steppedOnYou(this);}
	
	public void investigateCollision(){
		Skeleton.printLastCalledFunction(id);
		field.investigateCollision();	}
	
	public void detectCollision(CoordVector coord){
		Skeleton.printLastCalledFunction(id);
		this.isItAlive=false;
		/*
		// majd meghalnak
		field.steppedOffYou(this);
		this.killRobot();*/
	}
	
	public CoordVector getSpeed(){
		return speed;}
	
	public void setSpeed(CoordVector speed){
		this.speed = speed;}

	public void slowDown(){
		Skeleton.printLastCalledFunction(id);
		if(
				Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_a_putty||
				Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_an_oil){
				Skeleton.drawLine();
			}
	}

	public void disableMovement(){
		Skeleton.printLastCalledFunction(id);
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_a_putty||
				Skeleton.currentUseCase==Skeleton.UseCaseType.Stepping_on_an_oil){
				Skeleton.drawLine();
			}
	}

	public void killRobot(){
		Skeleton.printLastCalledFunction(this.id);
		field.steppedOffYou(this);
		this.isItAlive = false;
		arena.killRobot(points, id);	}

	public int getPoints() {
		Skeleton.printLastCalledFunction(id);
		return points;}
	
	public Field getField() {
		Skeleton.printLastCalledFunction(id);
		return field;}

	public void setField(Field field) {
		Skeleton.printLastCalledFunction(id,new String[]{id,Skeleton.getClassName(this)});
		this.field = field;
	}
}
