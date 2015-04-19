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
		Skeleton.printLastCalledFunction(id);
		field.steppedOffYou(this);
		isItAlive = false;
		arena.killRobot(points, id);

	}

	/**
	 * Get the points of the Robi.
	 */
	public int getPoints() {
		Skeleton.printLastCalledFunction(id);
		return points;

	}

	/**
	 * Disable movement after stepping on an oil.
	 */
	public void disableMovement() {
		Skeleton.printLastCalledFunction(id);
		if (Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_putty
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_an_oil) {
			Skeleton.drawLine();
		}
/*		canChangeSpeed = false;*/
		disableEffect = false;

	}

	/**
	 * Change the speed to lower after stepping on a putty.
	 */
	public void slowDown() {
		Skeleton.printLastCalledFunction(id);

/*		int[] newSpeed = null;
		for (int i = 0; i < speed.dimension; i++) {

			newSpeed[i] = ((speed.getCoordofDim(i)) / 2);
		}
		try {
			speed = new CoordVector(newSpeed);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		slowDownEffect = false;

		if (Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_putty
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_an_oil) {
			Skeleton.drawLine();
		}
	}

	/**
	 * Robi puts a putty.
	 */
	public void putPutty() {
		if (puttySupply > 0) {
			Skeleton.printLastCalledFunction(id);
			puttySupply--;
			Putty putty = new Putty("pr");
			field.addPutty(putty);
			arena.registerPatch(putty);
		}

	}

	/**
	 * Robi puts an Oil.
	 */
	@Override
	public void putOil() {
		if (oilSupply > 0) {
			Skeleton.printLastCalledFunction(id);
			oilSupply--;
			Oil oil = new Oil("or");
			field.addOil(oil);
			arena.registerPatch(oil);
		}

	}
	
	@Override
	public void tick() {
		if (Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_putty
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_an_oil
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_dangerzone
				|| Skeleton.currentUseCase == Skeleton.UseCaseType.Step_on_a_safezone) {
			Skeleton.drawLine();
		}
		Skeleton.printLastCalledFunction(id);

		/*
		 * try { speed = new CoordVector(2,0); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		
		Field f = field.step(speed, this);
		field.steppedOffYou(this);
		setField(f);
		f.steppedOnYou(this);

	}

	@Override
	public void takeEffect() {
		Skeleton.printLastCalledFunction(id);
		
		if (slowDownEffect){
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
		
		if(disableEffect){
			canChangeSpeed = false;
		}
		
	}
	
	
}
