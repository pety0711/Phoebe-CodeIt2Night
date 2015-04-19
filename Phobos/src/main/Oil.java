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
		lifeTime=20;  
	}
	/**
	 * Instantiates a new oil.
	 *
	 * @param id the id
	 */
	public Oil(String id){
		lifeTime=20; 
		this.id = id;
	}

	public void tick() {
		if(fix!=true){
			lifeTime--;
		}
	}
	
	public void doEffect(Robi r) {
		r.disableMovement();
	}
}
