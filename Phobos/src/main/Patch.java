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
	
	/** The life time. */
	protected int lifeTime;
	
	/** The fix. */
	protected boolean fix;
	
	public String id;
	
	/**
	 * Tick.
	 */
	public abstract void tick();
	
	/**
	 * Sets the fix.
	 */
	public void setFix() {
		fix = true;
	}
}
