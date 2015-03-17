package main;

import java.util.List;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Arena.
 */
public class Arena {
	
	/** The fields. */
	private List<Field> fields;
	
	/** The gamers. */
	private List<Robot> gamers;
	
	/** The dimension*/
	private CoordVector dim;
	
	/** Number of Robots */
	private int noRobots = 2;
	
	public Arena(){
		int[] tmp = {5, 2};
		try {
			dim = new CoordVector(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		generateFields(dim);
		
		for (int i = 0; i < noRobots; i++)
		{
			addRobot("Robot" + i);
		}
	}
	
	/**
	 * Generate fields.
	 *
	 * @param size the size
	 */
	public void generateFields(CoordVector size){
		
		//TODO erre valami algoritmust kitalálni, különben a pálya kitalálsába fogunk belezöldülni...
		SafeZone s0 = new SafeZone();
		SafeZone s1 = new SafeZone();
		SafeZone s2 = new SafeZone();
		SafeZone s3 = new SafeZone();
		SafeZone s4 = new SafeZone();
		SafeZone sr0 = new SafeZone();
		SafeZone sr1 = new SafeZone();
		
		Putty p = new Putty();
		Oil o = new Oil();
		DangerZone d = new DangerZone();
		
	}
	
	/**
	 * Adds the robot.
	 *
	 * @param id the id
	 */
	public void addRobot(String id){
		
	}
	
	/**
	 * Kill robot.
	 *
	 * @param points the points
	 * @param id the id
	 */
	public void killRobot(int points, String id){
		
	}
	
	/**
	 * Change field.
	 *
	 * @param from the from
	 * @param to the to
	 */
	public void changeField(SafeZone from, SafeZone to){
		
	}
}
