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
	
	public abstract void doEffect(Robi r);
	
	public void setFix() {
		fix = true;
	}
}

//Most akkor melyik listákból lesz kivéve a patchek megsemmisüléskor? Mert van a Field-be(1db) és a SafeZone-ba(2db) is?;
