/**
 * 
 */
package main;

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
		id = "o" + Arena.createID();
		lifeTime=5;  
		alive=1;
	}
	/**
	 * Instantiates a new oil.
	 *
	 * @param id the id
	 */
	public Oil(String id){
		lifeTime=30;
		this.id = id;
		alive=1;
		
	}

	/**
	 * Tick for Oil
	 */
	public void tick() {
		if(fix!=true){
			lifeTime--;
			if(lifeTime<=0){
				alive=0;
				Prototype.printOut("Timeout " + id);
			}
		}
	}
	/**
	 * Activate effect on robot
	 */
	@Override
	public void doEffect(Robot r) {
		if ("Robi".equals(r.getClass().getSimpleName()))
			((Robi)r).disableMovement();
		else
			r.takeEffect();
	}
}