/**
 * 
 */
package main;

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
		id = Arena.createID();
		alive=1;
	}
	
	/**
	 * Instantiates a new putty.
	 *
	 * @param id the id
	 */
	public Putty(String id){
		this.id = id;
		lifeTime=4;
		alive=1;
	}

	public void decreaseLife() {
		if(fix!=true){
			lifeTime--;
			if(lifeTime<=0)
				alive=0;
		}
	}
	@Override
	public void doEffect(Robot r) {
		r.takeEffect();
		this.decreaseLife();
		
	}
}
