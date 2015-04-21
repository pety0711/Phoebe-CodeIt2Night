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

	/** SlowDown flag. */
	protected boolean slowDownEffect;

	/** DisableMovement flag. */
	protected boolean disableEffect;

	/** CanChangeSpeed flag. */
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
		canChangeSpeed = true;
		arena = a;
		canChangeSpeed = true;

		speed = new CoordVector();
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
		canChangeSpeed = true;
		arena = a;
		canChangeSpeed = true;

		speed = new CoordVector();
	}

	/**
	 * Put oil.
	 */
	public abstract void putOil();

	/**
	 * Take Effect. SlowDown and DisableMovement effects are validated on Robi.
	 * The CleanerMaster can clean up the patches.
	 */

	public abstract void takeEffect();

	/**
	 * Tick.
	 */
	public abstract void tick();

	/**
	 * Kill robot.
	 */
	public abstract void killRobot();

	/**
	 * Investigate collision.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void investigateCollision() throws Exception {
		//System.out.println("HELLO ROBOT INVESTIGATE");
		field.investigateCollision();
	}

	/**
	 * Detected collision.
	 *
	 * @param coord
	 *            the coord
	 */
	public void detectedCollision(CoordVector coord) {
		setSpeed(coord);
		Field f = field.step(coord, this);
		setField(f);
		f.steppedOnYou(this);
	}

	/**
	 * Gets the Robot's speed.
	 *
	 * @return the speed
	 */
	public CoordVector getSpeed() {
		return speed;
	}
	
	public int getIntSpeed(){
		int s = 0;
		for (int i = 0; i < speed.dimension; i++)
			s += Math.abs(speed.getCoordofDim(i));
		return s;
	}

	/**
	 * Sets the Robot's speed.
	 *
	 * @param speed
	 *            the new speed
	 */
	public void setSpeed(CoordVector s) {
		String infoToPrint = "SpeedChanged - " + this.id + " " + this.getClass().getSimpleName() + " - Old speed: [" + this.speed.getX() + ", " + this.speed.getY() + "]";
		
		if (canChangeSpeed)	
		{
			this.speed = s;
		}
		
		infoToPrint += " New Speed: [" + this.speed.getX() + ", " + this.speed.getY() + "]";
		Prototype.printOut(infoToPrint);
	}

	/**
	 * Gets the container field.
	 *
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Sets the container field.
	 *
	 * @param field
	 *            the new field
	 */
	public void setField(Field field) {
		this.field = field;
	}

}
