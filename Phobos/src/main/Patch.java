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

	public String id;
	/** The life time. */
	protected int lifeTime;
	
	/** The fix. */
	protected boolean fix;
	
	
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
