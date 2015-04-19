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
	
	
	/**
	 * Tick.
	 */
	public abstract void tick();
	
	public abstract void doEffect(Robi r);
	
	/**
	 * Sets the fix.
	 */
	public void setFix() {
		fix = true;
	}
}
