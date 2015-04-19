package main;

import java.util.ArrayList;
import java.lang.*;

/**
 * The Class CleanerMaster.
 *
 * @author Vir�g
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
	}

	/**
	 * Kill robot - CleanerMaster.
	 */
	@Override
	public void killRobot() {
		Skeleton.printLastCalledFunction(this.id);
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
		Skeleton.printLastCalledFunction(id);
		Oil oil = new Oil("or");
		field.addOil(oil);
		arena.registerPatch(oil);
	}

	// Seg�df�ggv�ny, amely megkeresi a start-hoz legk�zelebbi targetelemet a
	// targetLsit-b�l.
	public CoordVector getMinDist(ArrayList<CoordVector> targetList,
			CoordVector start) {
		// start �s a vizsg�lt tartgetelem t�vols�ga l�p�ssz�mban
		int dist = 0;

		// Legkisebb l�p�ssz�m �s az ahhoz tartoz� targetelem koordin�t�i
		int minDist = 0;
		CoordVector t = null;

		for (int i = 0; i < targetList.size(); i++) {
			// Az aktu�lis t�vols�got kinull�zzuk, egy temp-be tessz�k az
			// aktu�lisan vizsg�lt targetelem koordin�t�t, hogy olvashat�bb
			// legyen.
			dist = 0;
			CoordVector temp = targetList.get(i);

			// L�p�s t�vols�g kisz�mol�sa
			for (int j = 0; j < start.dimension; j++) {
				dist += Math
						.abs(start.getCoordofDim(j) - temp.getCoordofDim(j));
			}

			// friss�tj�k a legkisebb t�vols�g �rt�keket
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

	// Megtal�lja azt a Patch-et, ami a legkevesebb l�p�sre van a Robott�l -->
	// Heurisztika a keres�shez
	// Itt m�g nem �rdekes, hogy mi van a robot �s a Patch-ek k�z�tt
	/**
	 * Find the target Patch from Arena's patchList.
	 */
	private void getTarget() {
		Skeleton.printLastCalledFunction(id);

		ArrayList<CoordVector> pc = arena.getPatchesCoords();
		CoordVector cleanerMasterCoord = this.field.getCoord();

		target = getMinDist(pc, cleanerMasterCoord);

	}

	// K�zel�t� megold�s A* �tkeres�s szer�
	// A szomsz�dok �s a Target koordin�t�j�nak k�l�nbs�ge alapj�n megkapom,
	// hogy h�nyat kell l�pni a
	// szomsz�dt�l a Target-ig.
	// Kiv�lasztom azt a szomsz�dot, amelyik a legkevesebb l�p�sre van a
	// Target-t�l
	// Ha a kiv�lasztott szomsz�d t�pusa DangerZone, akkor a m�sodik
	// legk�zelebbit v�lasztom �s �gy tov�bb...
	/**
	 * Find the next Field to step on, on the way to the target.
	 */
	private void getNextField() {
		Skeleton.printLastCalledFunction(id);

		ArrayList<CoordVector> n = null;

		// List�ba tessz�k a szomsz�dok koordin�t�it
		for (int i = 0; i < field.neighbours.size(); i++) {
			Field temp = field.getNeighbours().get(i);

			// Mi az oszt�ly neve az aktu�is szomsz�dnak?
			String className = temp.getClass().getSimpleName();

			// Ha DangerZone, nem vessz�k bele a l�p�s sz�mol�sba, �gy biztos
			// nem l�p r�.
			if (!(("DangerZone").equals(className))) {
				n.add(temp.getCoord());
			}
		}

		// Megn�zz�k, hogy melyik szomsz�dhoz van a target a legk�zelebb �s
		// visszaadjuk a CoordVector-�t
		// Erre kell majd l�pnie a CleanerMasternek.
		CoordVector nextField = getMinDist(n, this.target);
		int[] newSpeed = null;
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

		getTarget();
		getNextField();
		
		Field f = field.step(speed, this);
		field.steppedOffYou(this);
		setField(f);
		f.steppedOnYou(this);

	}
}
