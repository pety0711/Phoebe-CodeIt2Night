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
		id = Arena.createID();
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
	@Override
	public void doEffect(Robot r) {
		r.takeEffect();
	}
}
