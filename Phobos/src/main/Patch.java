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
	
	public abstract void doEffect(Robi r);
	
	public void setFix() {
		fix = true;
	}
}

//Most akkor melyik list�kb�l lesz kiv�ve a patchek megsemmis�l�skor? Mert van a Field-be(1db) �s a SafeZone-ba(2db) is?;
