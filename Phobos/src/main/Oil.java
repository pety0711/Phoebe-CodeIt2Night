/**
 * 
 */
package main;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Oil.
 *
 * @author Dávid
 */
public class Oil extends Patch{

	/**
	 * Instantiates a new oil.
	 */
	public Oil(){
		Random r = new Random();
		id = "o"+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id);

	}
	
	/**
	 * Instantiates a new oil.
	 *
	 * @param id the id
	 */
	public Oil(String id){
		this.id = id;
		Skeleton.printLastCalledFunction(id);

	}
	
	/* (non-Javadoc)
	 * @see main.Patch#tick()
	 */
	@Override
	public void tick() {
		lifeTime--;
		Skeleton.printLastCalledFunction(id);
	}

}
