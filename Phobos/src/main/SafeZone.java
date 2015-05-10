package main;

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
		this.id = id;
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
			for (Patch patch : patches) {
				Prototype.printOut("ObjectDestroyed " + patch.id
						+ "CleanerMaster cleaned it");
			}
			patches.clear();
			this.setHasChanged(true);
			haveToClean = false;
			return true;
		}
		return false;
	}

	/**
	 * The method calls the tick() functions of all the oil patches that are on
	 * the field.
	 */
	public Boolean checkPatch() {
		String infoToPrint = "";
		Boolean temp = false;

		for (int i = 0; i < patches.size(); i++) {
			if (patches.get(i).isitremovable()) {
				infoToPrint += "Object Destroyed";
				infoToPrint += patches.get(i).id;
				infoToPrint += " - timeOut";
				Prototype.printOut(infoToPrint);
				patches.remove(patches.get(i));
				temp = true;
				this.setHasChanged(true);
			}
		}
		return temp;
	}

	/**
	 * 
	 */
	@Override
	public void steppedOnYou(Robot r) {
		robots.add(r);
		for (int i = 0; i < patches.size(); i++) {
			patches.get(i).doEffect(r);
			checkPatch();
		}
		this.setHasChanged(true);
	}

	/**
	 * @param r
	 *            - Robot that wants to step off the field
	 */
	@Override
	public void steppedOffYou(Robot r) {
		while (robots.contains(r)) {
			robots.remove(r);
		}
		this.setHasChanged(true);
	}

	/**
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
	@Override
	public Field step(CoordVector speed, Robot r) {
		direction tempDirX = direction.right;
		direction tempDirY = direction.up;
		Field temp = this;

		if (speed.getX() < 0) {
			tempDirX = direction.left;
		}
		if (speed.getY() < 0) {
			tempDirY = direction.down;
		}

		for (int i = 0; i < Math.abs(speed.getX()); i++) {
			temp = temp.getNeighbour(tempDirX);
		}
		for (int i = 0; i < Math.abs(speed.getY()); i++) {
			temp = temp.getNeighbour(tempDirY);
		}
		return temp;
	}

	/**
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision() {
		if (robots.size() > 1) {

			String infoToPrint = "Collide - ";
			Boolean allTheSameSpeed = true;
			// Robot fastest = robots.get(0);
			int fastest = 0;
			int numOfRobis = 0;
			for (int i = 0; i < robots.size(); i++) {

				infoToPrint += robots.get(i).id;
				infoToPrint += " ";
				infoToPrint += robots.get(i).getClass().toString();
				infoToPrint += ", speed: [";
				infoToPrint += robots.get(i).getSpeed().getX();
				infoToPrint += ",";
				infoToPrint += robots.get(i).getSpeed().getY();
				infoToPrint += "] - ";

				if ("Robi".equals(robots.get(i).getClass().getSimpleName())) {
					numOfRobis++;
				}
				if (robots.get(fastest).getIntSpeed() != robots.get(i)
						.getIntSpeed()) {
					allTheSameSpeed = false;
				}
				if (robots.get(fastest).getIntSpeed() < robots.get(i)
						.getIntSpeed()) {
					fastest = i;
				}
			}

			infoToPrint += "Collision: " + id + "[" + coord.getX() + ","
					+ coord.getY() + "]";

			/* Counting of avg speed */
			int avgX = 0;
			for (int i = 0; i < robots.size(); i++) {
				avgX += robots.get(i).getSpeed().getX();
			}
			avgX /= robots.size();

			int avgY = 0;
			for (int i = 0; i < robots.size(); i++) {
				avgY += robots.get(i).getSpeed().getY();
			}
			avgY /= robots.size();
			/*-----------------------------*/

			if (allTheSameSpeed) {
				if (numOfRobis > 1) {
					for (int i = 0; i < robots.size(); i++) {
						robots.get(i).killRobot();
					}
				} else {
					if (numOfRobis == 1) {
						for (int i = 0; i < robots.size(); i++) {
							if (!"Robi".equals(robots.get(i).getClass()
									.getSimpleName())) {
								robots.get(i).killRobot();
							}
						}
					}
				}
			} else if ("CleanerMaster".equals(robots.get(fastest).getClass()
					.getSimpleName())) {
				((CleanerMaster) robots.get(fastest)).stepBack();
			} else {
				infoToPrint += robots.get(fastest).id + " speed changed -";

				for (int i = 0; i < robots.size(); i++) {
					if (!robots.get(i).equals(robots.get(fastest))) {

						infoToPrint += robots.get(i).id
								+ robots.get(i).getClass().getSimpleName()
								+ " died";
						robots.get(i).killRobot();
					}
				}
				robots.get(fastest).detectedCollision(
						new CoordVector(avgX, avgY));
			}
			Prototype.printOut(infoToPrint);
		}
	}
}
