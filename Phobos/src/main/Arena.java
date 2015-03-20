package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Arena.
 */
public class Arena {
	
	public static final int robot0StartField = 5;
	public static final int robot1StartField = 6;
	
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

		gamers.get(0).setField(fields.get(robot0StartField));
		gamers.get(1).setField(fields.get(robot1StartField));
	}
	
	/**
	 * Generate fields.
	 *
	 * @param size the size
	 */
	public void generateFields(CoordVector size){
		
		//TODO erre valami algoritmust kitalálni, különben a pálya megalkotásába fogunk belezöldülni...
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
		
		//TODO na meg ez is probléma lesz
		List<Field> temp = new ArrayList<Field>();
		temp.add(s0);
		temp.add(s1);
		temp.add(s2);
		temp.add(s3);
		temp.add(s4);
		temp.add(sr0);
		temp.add(sr1);
		temp.add(d);

		//TODO valakinek van ötlete erre?
		s0.setNeighbours(temp);
		s1.setNeighbours(temp);
		s2.setNeighbours(temp);
		s3.setNeighbours(temp);
		s4.setNeighbours(temp);
		sr0.setNeighbours(temp);
		d.setNeighbours(temp);
		
	}
	
	/**
	 * Adds the robot.
	 *
	 * @param id the id
	 */
	public void addRobot(String id){
		gamers.add(new Robot(id, this));
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
