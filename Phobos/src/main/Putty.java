/**
 * 
 */
package main;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Putty.
 *
 * @author Dávid
 */
public class Putty extends Patch {
	
	/**
	 * Instantiates a new putty.
	 */
	public Putty(){
		Random r = new Random();
		id = "p"+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id);
	}
	
	/**
	 * Instantiates a new putty.
	 *
	 * @param id the id
	 */
	public Putty(String id){
		this.id = id;
		lifeTime=4;
		Skeleton.printLastCalledFunction(id);
	}
	
	/* (non-Javadoc)
	 * @see main.Patch#tick()
	 */
	@Override
	public void tick() {
		Skeleton.printLastCalledFunction(id);
	}
	public void decreaseLife() {
		if(fix!=true){
			lifeTime--;
		}
		Skeleton.printLastCalledFunction(id);
	}
	public void doEffect(Robi r) {
		// TODO Auto-generated method stub
		r.slowDown();
		Skeleton.printLastCalledFunction(id);
	}
}
