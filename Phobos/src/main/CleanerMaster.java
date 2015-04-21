package main;

import java.util.ArrayList;

/**
 * The Class CleanerMaster.
 *
 * @author Virág
 */
public class CleanerMaster extends Robot {

	/**
	 * The target patch's CoordVector
	 */
	private CoordVector target;

	/**
	 * Instantiates a new robot - CleanerMaster.
	 *
	 * @param a
	 *            the Arena
	 */
	public CleanerMaster(Arena a) {
		super(a);
		canChangeSpeed = true;

	}

	/**
	 * Instantiates a new robot - CleanerMaster.
	 *
	 * @param id
	 *            the id
	 * @param a
	 *            the Arena
	 */
	CleanerMaster(String id, Arena arena) {
		super(id, arena);
		canChangeSpeed = true;

	}

	/**
	 * Kill robot - CleanerMaster.
	 */
	@Override
	public void killRobot() {
		field.steppedOffYou(this);
		isItAlive = false;
		putOil();
		arena.killRobot(id);
	}

	/**
	 * Put oil. - CleanerMaster.
	 */
	@Override
	public void putOil() {
		field.addOil();
	}

	/**
	 * Get Min Dist. Returns the nearest CoordVector element from target list.
	 * 
	 * @param targetList
	 *            the list of the possible targets.
	 * @param start
	 *            reference
	 */
	// Segédfüggvény, amely megkeresi a start-hoz legközelebbi targetelemet a
	// targetLsit-bõl.
	public CoordVector getMinDist(ArrayList<CoordVector> targetList,
			CoordVector start) {
		// start és a vizsgált tartgetelem távolsága lépésszámban
		int dist = 0;

		// Legkisebb lépésszám és az ahhoz tartozó targetelem koordinátái
		int minDist = 0;
		CoordVector t = new CoordVector();

		for (int i = 0; i < targetList.size(); i++) {
			// Az aktuális távolságot kinullázzuk, egy temp-be tesszük az
			// aktuálisan vizsgált targetelem koordinátát, hogy olvashatóbb
			// legyen.
			dist = 0;
			CoordVector temp = targetList.get(i);

			// Lépés távolság kiszámolása
			for (int j = 0; j < start.dimension; j++) {
				dist += Math
						.abs(start.getCoordofDim(j) - temp.getCoordofDim(j));
			}

			// frissítjük a legkisebb távolság értékeket
			if (i == 0) {
				minDist = dist;
				t = temp;
			} else if (dist < minDist) {
				minDist = dist;
				t = temp;
			}
		}
		return t;
	}

	// Megtalálja azt a Patch-et, ami a legkevesebb lépésre van a Robottól -->
	// Heurisztika a kereséshez
	// Itt még nem érdekes, hogy mi van a robot és a Patch-ek között
	/**
	 * Find the target Patch from Arena's patchList.
	 */
	private void getTarget() {

		ArrayList<CoordVector> pc = arena.getPatchesCoords();

		if( pc.size() > 0){
			CoordVector cleanerMasterCoord = this.field.getCoord();
			target = getMinDist(pc, cleanerMasterCoord);
			getNextField();}

	}

	// Közelítõ megoldás A* útkeresés szerû
	// A szomszédok és a Target koordinátájának különbsége alapján megkapom,
	// hogy hányat kell lépni a
	// szomszédtól a Target-ig.
	// Kiválasztom azt a szomszédot, amelyik a legkevesebb lépésre van a
	// Target-tõl
	// Ha a kiválasztott szomszéd típusa DangerZone, akkor a második
	// legközelebbit választom és így tovább...
	/**
	 * Find the next Field to step on, on the way to the target.
	 */
	private void getNextField() {

		ArrayList<CoordVector> n = new ArrayList<CoordVector>();

		// Listába tesszük a szomszédok koordinátáit
		for (int i = 0; i < field.neighbours.size(); i++) {
			Field temp = field.getNeighbours().get(i);

			// Mi az osztály neve az aktuáis szomszédnak?
			String className = temp.getClass().getSimpleName();

			// Ha DangerZone, nem vesszük bele a lépés számolásba, így biztos
			// nem lép rá.
			if (!(("DangerZone").equals(className))) {
				n.add(temp.getCoord());
			}
		}

		// Megnézzük, hogy melyik szomszédhoz van a target a legközelebb és
		// visszaadjuk a CoordVector-át
		// Erre kell majd lépnie a CleanerMasternek.
		CoordVector nextField = getMinDist(n, this.target);
		int[] newSpeed = new int[this.field.coord.dimension];
		for (int j = 0; j < this.field.coord.dimension; j++) {
			newSpeed[j] = (nextField.getCoordofDim(j) - this.field.coord
					.getCoordofDim(j));
		}
		try {
			speed = new CoordVector(newSpeed);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tick. The CleanerMaster can step.
	 */
	@Override
	public void tick() {
		getTarget();
		//getNextField();

		Field f = field.step(speed, this);
		field.steppedOffYou(this);
		setField(f);
		f.steppedOnYou(this);

	}

	/**
	 * The CleanerMaster can clean up the patches.
	 */
	@Override
	public void takeEffect() {
		if (field.patches.size() > 0) {
			((SafeZone) field).cleanThePatches(this);
		}

	}
}
