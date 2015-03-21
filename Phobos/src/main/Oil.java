/**
 * 
 */
package main;

import java.util.Random;

/**
 * @author Dávid
 *
 */
public class Oil extends Patch{

	public Oil(){
		Random r = new Random();
		id = "o"+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id);

	}
	@Override
	public void tick() {
		lifeTime--;
		Skeleton.printLastCalledFunction(id);
	}

}
