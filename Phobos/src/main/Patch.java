/**
 * 
 */
package main;

// TODO: Auto-generated Javadoc
/**
 * The Class Patch.
 *
 * @author D�vid
 */
public abstract class Patch {

	/** The id. */
	public String id;
	/** The life time. */
	protected int lifeTime;
	
	/** The fix. */
	protected boolean fix;
	
	public int alive;
	
	public abstract void doEffect(Robot r);
	
	public void setFix() {
		fix = true;
	}
	public boolean isitremovable(){
		return (alive==0);
			
	}
	
}