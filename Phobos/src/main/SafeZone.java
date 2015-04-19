package main;

import java.util.ArrayList;
import java.util.Random;

import main.Field.direction;

// TODO: Auto-generated Javadoc
/**
 * The Class SafeZone.
 *
 * @author Dávid
 */
public class SafeZone extends Field {

	/**
	 * 
	 */
	private Boolean haveToClean = false;

	/**
	 * Instantiates a new safe zone.
	 *
	 * @param id
	 *            the id
	 */
	public SafeZone(String id) {
		super();
	}

	/**
	 * The method clears all the patches on the field
	 * 
	 * @param cleanerMaster
	 *            - the cleanerMaster who cleans the patches
	 */
	public void cleanThePatches(CleanerMaster cleanerMaster) {
		haveToClean = true;
	}

	/**
	 * The method is called by the Arena, when the patches can be removed from
	 * the list
	 * 
	 * @return - true, it something has been deleted - false, if no patch
	 *         removed
	 */
	public Boolean haveToCleanPatch() {
		if (haveToClean) {
			patches.clear();
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public void steppedOnYou(Robot r) {
		robots.add(r);
		for (Patch patch : patches) {
			patch.doEffect(r);
		}
	}

	/**
	 * @param r
	 *            - Robot that wants to step off the field
	 */
	@Override
	public void steppedOffYou(Robot r) {
		if (robots.contains(r)) {
			robots.remove(r);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
	@Override
	public Field step(CoordVector speed, Robot r) {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision() throws Exception {
		Skeleton.printLastCalledFunction(id);
		switch (Skeleton.currentUseCase) {
		case Collision:
			if (robots.size() > 1 && (robots.get(0) != null)
					&& robots.get(1) != null) {
				CoordVector cv0 = new CoordVector(1, 0);
				CoordVector cv1 = new CoordVector(1, 1);
				robots.get(1).detectedCollision(cv1);
				robots.get(0).detectedCollision(cv0);
			}
			break;
		default:
			break;
		}
	}

}
