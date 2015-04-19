/**
 * 
 */
package main;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


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
		lifeTime=4;
		Random r = new Random();
		id = "p"+r.nextInt(2000000000);
	}
	
	/**
	 * Instantiates a new putty.
	 *
	 * @param id the id
	 */
	public Putty(String id){
		this.id = id;
		lifeTime=4;
	}

	public void decreaseLife() {
		if(fix!=true){
			lifeTime--;
		}
	}
	public void doEffect(Robi r) {
		r.slowDown();
	}
}
