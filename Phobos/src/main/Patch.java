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
	private int lifeTime;
	
	/** The fix. */
	private boolean fix;
	
	/**
	 * Tick.
	 */
	public abstract void tick();
	
	/**
	 * Sets the fix.
	 */
	public void setFix() {
	}
}
