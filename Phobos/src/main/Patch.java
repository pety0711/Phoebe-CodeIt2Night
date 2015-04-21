/**
 * 
 */
package main;

// TODO: Auto-generated Javadoc
/**
 * The Class Patch.
 *
 * @author Dávid
 */
public abstract class Patch {

	/** The id. */
	public String id;
	/** The life time. */
	protected int lifeTime;
	
	/** The fix. */
	protected boolean fix;
	
	/** The alive. */
	public int alive;
	
	/** Doing effect on robot */
	public abstract void doEffect(Robot r);
	
	/** Fix parameter set true. */
	public void setFix() {
		fix = true;
	}
	/** Can we remove it */
	public boolean isitremovable(){
		return (alive==0);
			
	}
	
}