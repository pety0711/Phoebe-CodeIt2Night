/**
 * 
 */
package main;

import java.util.Random;

/**
 * @author Dávid
 *
 */
public class Putty extends Patch {
	
	public Putty(){
		Random r = new Random();
		id = "p"+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id);
	}
	
	public Putty(String id){
		this.id = id;
		Skeleton.printLastCalledFunction(id);
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		lifeTime--;
		Skeleton.printLastCalledFunction(id);
	}
}
