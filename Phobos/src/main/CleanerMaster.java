package main;

import java.util.ArrayList;

/**
 * The Class CleanerMaster.
 *
 * @author Virág
 */
public class CleanerMaster extends Robot{

	/** 
	 * The target patch's CoordVector
	*/
	private CoordVector target;
	
	/**
	 * Instantiates a new robot - CleanerMaster.
	 *
	 * @param a the Arena
	 */
	public CleanerMaster(Arena a) {
		super(a);		
	}
	
	/**
	 * Instantiates a new robot - CleanerMaster.
	 *
	 * @param id the id
	 * @param a the Arena
	 */
	CleanerMaster(String id, Arena arena) {
		super(id, arena);
	}

//	public Field getField() {
//		return field;
//	}
//
//	public void setField(Field field) {
//		this.field = field;
//	}
//	
//	public void tick() {}
//
//	public void investigateCollision() {}
	
	/**
	 * Kill robot - CleanerMaster.
	 */
	@Override
	public void killRobot() 
	{
		Skeleton.printLastCalledFunction(this.id);
		field.steppedOffYou(this);
		isItAlive = false;
		putOil();
		arena.killRobot(id);
	}
	
	/**
	 * Put oil. - CleanerMaster.
	 */
	@Override
	public void putOil() {
		Skeleton.printLastCalledFunction(id);
		Oil oil = new Oil("or");
		field.addOil(oil);
		arena.registerPatch(oil);	
	}
	
	// Megtalálja azt a Patch-et, ami a legkevesebb lépésre van a Robottól --> Heurisztika a kereséshez
	// Itt még nem érdekes, hogy mi van a robot és a Patch-ek között
	/**
	 * Find the target Patch from Arena's patchList.
	 */
	private void getTarget(){
		Skeleton.printLastCalledFunction(id);

		ArrayList<Patch> p = arena.getPatches();
		Patch minDist = p.get(0);
		for(int i = 1; i < p.size(); i++){
			// Patch-ek és a CleanerMaster coordinátáinak kivonása, lépésszám kitalálása
			// Legközelebbi Patch kiválasztása
				
		}
		
	}
	
	// Közelítõ megoldás A* útkeresés szerû
	// A szomszédok és a Target koordinátájának különbsége alapján megkapom, hogy hányat kell lépni a 
	// szomszédtól a Target-ig.
	// Kiválasztom azt a szomszédot, amelyik a legkevesebb lépésre van a Target-tõl
	// Ha a kiválasztott szomszéd típusa DangerZone, akkor a második legközelebbit választom és így tovább...
	/**
	 * Find the next Field to step on, on the way to the target.
	 */
	private CoordVector getNextFiled()
	{
		Skeleton.printLastCalledFunction(id);
		
		CoordVector dist;
		
		for(int i = 0; i < field.neighbours.size(); i++){
			CoordVector n = field.neighbours.get(i).coord;
			
		}
		
		return new CoordVector();
	}
}
