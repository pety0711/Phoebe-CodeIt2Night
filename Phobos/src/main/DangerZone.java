/**
 * 
 */
package main;
/**
 * @author D�vid
 *
 */
public class DangerZone extends Field {

	private int type;
	
	public DangerZone() {
		// TODO Auto-generated constructor stub
	}
	
	public DangerZone(String id) {
		this.id = id;
	}
	
	@Override
	public void steppedOnYou(Robot r) {
		Skeleton.printLastCalledFunction(id,new String[]
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
