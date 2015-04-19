package main;

/**
 * The Class Robi.
 *
 * @author Virág
 */
public class Robi extends Robot {

	/** The oil supply. */
	private int oilSupply;

	/** The putty supply. */
	private int puttySupply;

	/** The points. */
	private int points;

	public boolean canChangeSpeed;

	/**
	 * Instantiates a new robot - Robi.
	 *
	 * @param a
	 *            the container Arena
	 */
	public Robi(Arena a) {
		super(a);
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		canChangeSpeed = true;

	}

	/**
	 * Instantiates a new robot.
	 *
	 * @param id
	 *            the id
	 * @param a
	 *            the a
	 */
	public Robi(String id, Arena a) {
		super(id, a);
		puttySupply = 3;
		oilSupply = 3;
		points = 0;
		canChangeSpeed = true;

	}

	/**
	 * Kill robot - Robi.
	 */
	@Override
	public void killRobot() {
		field.steppedOffYou(this);
		isItAlive = false;
		arena.killRobot(points, id);

	}

	/**
	 * Get the points of the Robi.
	 */
	public int getPoints() {
		return points;

	}

	/**
	 * Disable movement after stepping on an oil.
	 */
	public void disableMovement() {
		disableEffect = false;

	}

	/**
	 * Change the speed to lower after stepping on a putty.
	 */
	public void slowDown() {
		slowDownEffect = false;
	}

	/**
	 * Robi puts a putty.
	 */
	public void putPutty() {
		if (puttySupply > 0) {
			puttySupply--;
			field.addPutty();
		}

	}

	/**
	 * Robi puts an Oil.
	 */
	@Override
	public void putOil() {
		if (oilSupply > 0) {
			oilSupply--;
			field.addOil();
		}

	}

	/**
	 * Robi can step.
	 */
	@Override
	public void tick() {
		Field f = field.step(speed, this);
		field.steppedOffYou(this);
		setField(f);
		f.steppedOnYou(this);

	}

	/**
	 * Robi applies the effects of the patches.
	 * */
	@Override
	public void takeEffect() {
		if (slowDownEffect) {
			int[] newSpeed = null;
			for (int i = 0; i < speed.dimension; i++) {
				newSpeed[i] = ((speed.getCoordofDim(i)) / 2);
			}
			try {
				speed = new CoordVector(newSpeed);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		slowDownEffect = false;

		if (disableEffect) {
			canChangeSpeed = false;
		}
		disableEffect = false;
		canChangeSpeed = true;
	}

}
