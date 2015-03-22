/**
 * 
 */
package main;
/**
 * @author Dávid
 *
 */
public class DangerZone extends Field {

	private int type;
	
	@Override
	public void steppedOnYou(Robot r) {
		Skeleton.printLastCalledFunction(r.id,new String[]
				{r.id,Skeleton.getClassName(r)});
		robots.add(r);
		r.killRobot();
	}

	@Override
	public Field step(CoordVector speed, Robot r) {
		return this;
	}

	@Override
	public void investigateCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Field getNeighbour(CoordVector direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void steppedOffYou(Robot r) {
		// TODO Auto-generated method stub
	}
}
