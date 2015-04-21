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
			patches.clear();
			haveToClean = false;
			return true;
		}
		return false;
	}

	/**
	 * The method calls the tick() functions of all the oil patches that are on the field.
	 */
	public void checkPatch(){
		for (Patch patch : patches) {
			if(patch.isitremovable()){
				patches.remove(patch);
			}
		}
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

	/**
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
	@Override
	public Field step(CoordVector speed, Robot r) {
		direction tempDirX = direction.right;
		direction tempDirY = direction.up;
		Field temp = this;
		
		if (speed.getX()<0) { tempDirX = direction.left; }
		if (speed.getY()<0) { tempDirY = direction.down; }
		
		for (int i = 0; i < Math.abs(speed.getX()); i++) {
			temp.getNeighbour(tempDirX);
		}
		for (int i = 0; i < Math.abs(speed.getY()); i++) {
			temp.getNeighbour(tempDirY);
		}
		return temp;
	}

	/**
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision(){
		if(robots.size()>1){
			
			String infoToPrint = "Collide - ";
			
			Robot fastest = robots.get(0);
			for (int i = 0; i < robots.size(); i++) {
				
				infoToPrint += robots.get(i).id;
				infoToPrint += " ";
				infoToPrint += robots.get(i).getClass().toString();
				infoToPrint += ", speed: [";
				infoToPrint += robots.get(i).getSpeed().getX();
				infoToPrint += ",";
				infoToPrint += robots.get(i).getSpeed().getY();
				infoToPrint += "] - ";
				
				if(fastest.getIntSpeed() < robots.get(i).getIntSpeed()){fastest = robots.get(i);}
			}
			
			infoToPrint += "Collision: " + id + "[" + coord.getX() + "," + coord.getY() + "]";
			
			
			int avgX = 0;
			for (int i = 0; i < robots.size(); i++) {
				avgX += robots.get(i).getSpeed().getX();
			}
			avgX /= robots.size();
			//
			
			int avgY = 0;
			for (int i = 0; i < robots.size(); i++) {
				avgY += robots.get(i).getSpeed().getY();
			}
			avgY /= robots.size();
			
			
			fastest.detectedCollision(new CoordVector(avgX, avgY));
			infoToPrint += fastest.id + "speed changed ;";
			
			
			for (int i = 0; i < robots.size(); i++) {
				if (!robots.get(i).equals(fastest)) {
					
					infoToPrint += robots.get(i).id + robots.get(i).getClass().toString() +  "died";
					robots.get(i).killRobot();
				}
			}
			Prototype.printOut(infoToPrint);
		}
	}
}
