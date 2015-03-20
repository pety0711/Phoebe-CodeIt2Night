package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * The Class Arena.
 */
public class Arena {
	
	public static final int robot0StartField = 5;
	public static final int robot1StartField = 6;
	
	/** */
	private String arenaID;
	
	/** The fields. */
	private List<Field> fields;
	
	/** Patches - temporary*/
	private List<Patch> patches;
	
	/** The gamers. */
	private Map<String, Robot> gamers;
	
	/** The dimension*/
	private CoordVector dim;
	
	/** Number of Robots */
	private int noRobots = 2;
	
	public Arena(String id) {
		arenaID = id;
		Skeleton.printLastCalledFunction(arenaID, new String[]{""});
		Initialize();
	}
	
	public Arena(){
		Skeleton.printLastCalledFunction(arenaID, new String[]{""});
		arenaID = "arena";
		Initialize();
	}
	private void Initialize() {
//		Skeleton.printLastCalledFunction(id, new String[]{""});
		int[] tmp = {5, 2};
		try {
			dim = new CoordVector(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		generateFields(dim);
		
		gamers = new HashMap<String, Robot>();
		
		for (int i = 0; i < noRobots; i++)
		{
			addRobot("Robot" + i);
		}

		Set<String> keys = gamers.keySet();
		gamers.get(keys.toArray()[0]).setField(fields.get(robot0StartField));
		gamers.get(keys.toArray()[1]).setField(fields.get(robot1StartField));
	}
	
	/**
	 * Generate fields.
	 *
	 * @param size the size
	 */
	public void generateFields(CoordVector size){
		Skeleton.printLastCalledFunction(arenaID, new String[]{"size : CoordVector"});
		
		//TODO erre valami algoritmust kitalálni, különben a pálya megalkotásába fogunk belezöldülni...
		//+ be kell állítani a szomszédjaikat
		SafeZone s0 = new SafeZone("s0");
		SafeZone s1 = new SafeZone("s1");
		SafeZone s2 = new SafeZone("s2");
		SafeZone sp = new SafeZone("sp");
		SafeZone so = new SafeZone("so");
		SafeZone sr0 = new SafeZone("sr0");
		SafeZone sr1 = new SafeZone("sr1");
		
		//TODO név paraméternek
		DangerZone d = new DangerZone();

		patches = new ArrayList<Patch>();
		Putty p = new Putty();
		p.setFix();
		patches.add(p);
		sp.addPutty(p);
		
		Oil o = new Oil();
		o.setFix();
		patches.add(o);
		so.addOil(o);
		
		fields = new ArrayList<Field>();
		fields.add(s0);
		fields.add(s1);
		fields.add(s2);
		fields.add(sp);
		fields.add(so);
		fields.add(sr0);
		fields.add(sr1);
		fields.add(d);		
		
		//TODO na meg ez is probléma lesz
		List<Field> temp = new ArrayList<Field>();
		temp.add(s0);
		temp.add(s1);
		temp.add(s2);
		temp.add(sp);
		temp.add(so);
		temp.add(sr0);
		temp.add(sr1);
		temp.add(d);

		//TODO valakinek van ötlete erre?
		s0.setNeighbours(temp);
		s1.setNeighbours(temp);
		s2.setNeighbours(temp);
		sp.setNeighbours(temp);
		so.setNeighbours(temp);
		sr0.setNeighbours(temp);
		sr1.setNeighbours(temp);
		d.setNeighbours(temp);
		
	}
	
	/**
	 * Adds the robot.
	 *
	 * @param id the id
	 */
	public void addRobot(String id){
		Skeleton.printLastCalledFunction(arenaID, new String[]{"id : String"});
		
		gamers.put(id, new Robot(id, this));
	}
	
	public void registerPatch(Patch p) {
		Skeleton.printLastCalledFunction(arenaID, new String[]{"id : String"});
		
		patches.add(p);
	}
	
	/**
	 * Kill robot.
	 *
	 * @param points the points
	 * @param id the id
	 */
	public void killRobot(int points, String id){
		Skeleton.printLastCalledFunction(arenaID, new String[]{"points : int", "id : String"});
		
		Skeleton.printOutINFO(id + " Robot died, points: " + points);
	}
	
	public void tick() {
		for (Patch p : patches) {
			p.tick();
		}
		
		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			gamers.get(key).tick();
		}
		
		for (String key : keys) {
			gamers.get(key).investigateCollision();
		}
		
		
	}
	
	public void finishGame() {
		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			gamers.get(key).killRobot();
		}
	}
}
