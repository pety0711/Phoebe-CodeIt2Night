/**
 * 
 */
package main;

// TODO: Auto-generated Javadoc
/**
 * The Class Oil.
 *
 * @author D�vid
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
		lifeTime=5; 
		this.id = id;
		alive=1;
	}

	public void tick() {
		if(fix!=true){
			lifeTime--;
			if(lifeTime<=0)
				alive=0;
		}
	}
	@Override
	public void doEffect(Robot r) {
		r.takeEffect();
	}
}