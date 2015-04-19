/**
 * 
 */
package main;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Robot.
 *
 * @author Virág
 */
public abstract class Robot {

	/** The id. */
	public String id;

	/** The is it alive. */
	public boolean isItAlive;

	/** The speed. */
	protected CoordVector speed;

	/** The field. */
	protected Field field;

	/** The arena. */
	protected Arena arena;

	protected boolean slowDownEffect;
	protected boolean disableEffect;
	public boolean canChangeSpeed;

	
	/**
	 * Instantiates a new robot.
	 *
	 * @param a
	 *            the a
	 */
	public Robot(Arena a) {
		this.id = "Robot" + new Random(22222222).toString();
		isItAlive = true;
		arena = a;

		speed = new CoordVector();
		Skeleton.printLastCalledFunction(
				id,
				new String[] { id, Skeleton.getClassName(this), "a",
						Skeleton.getClassName(a) });
	}

	/**
	 * Instantiates a new robot.
	 *
	 * @param id
	 *            the id
	 * @param a
	 *            the a
	 */
	public Robot(String id, Arena a) {
		this.id = id;
		isItAlive = true;
		arena = a;

		speed = new CoordVector();
		Skeleton.printLastCalledFunction(
				id,
				new String[] { id, Skeleton.getClassName(this), "a",
						Skeleton.getClassName(a) });
	}

	/**
	 * Put oil.
	 */
	public abstract void putOil();

	public abstract void takeEffect();
		
	/**
	 * Tick.
	 */
	public abstract void tick(); 
/*	{
		if (Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_putty
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_an_oil
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_dangerzone
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_safezone) {
			Skeleton.drawLine();
		}
		Skeleton.printLastCalledFunction(id);

		
		 * try { speed = new CoordVector(2,0); } catch (Exception e) {
		 * e.printStackTrace(); }
		 

		Field f = field.step(speed, this);
		setField(f);
		f.steppedOnYou(this);
	}*/

	/**
	 * Investigate collision.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void investigateCollision() throws Exception {
		Skeleton.printLastCalledFunction(id);
		field.investigateCollision();
	}

	/**
	 * Detected collision.
	 *
	 * @param coord
	 *            the coord
	 */
	public void detectedCollision(CoordVector coord) {
		Skeleton.printLastCalledFunction(id, new String[] { "coord",
				"CoordVector" });
		setSpeed(coord);
		Field f = field.step(coord, this);
		setField(f);
		f.steppedOnYou(this);
		// this.isItAlive=false;
		/*
		 * // majd meghalnak field.steppedOffYou(this); this.killRobot();
		 */
	}

	/**
	 * Gets the Robot's speed.
	 *
	 * @return the speed
	 */
	public CoordVector getSpeed() {
		return speed;
	}

	/**
	 * Sets the Robot's speed.
	 *
	 * @param speed
	 *            the new speed
	 */
	public void setSpeed(CoordVector speed) {
		if(canChangeSpeed)
			this.speed = speed;
	}

	/**
	 * Kill robot.
	 */
	public abstract void killRobot();

	/**
	 * Gets the container field.
	 *
	 * @return the field
	 */
	public Field getField() {
		Skeleton.printLastCalledFunction(id);
		return field;
	}

	/**
	 * Sets the container field.
	 *
	 * @param field
	 *            the new field
	 */
	public void setField(Field field) {
		Skeleton.printLastCalledFunction(id,
				new String[] { field.id, Skeleton.getClassName(field) });
		this.field = field;
	}
	
	
}
