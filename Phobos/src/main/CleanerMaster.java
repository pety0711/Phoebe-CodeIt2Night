package main;

import java.util.ArrayList;

/**
 * The Class CleanerMaster.
 *
 * @author Vir�g
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
	
	// Megtal�lja azt a Patch-et, ami a legkevesebb l�p�sre van a Robott�l --> Heurisztika a keres�shez
	// Itt m�g nem �rdekes, hogy mi van a robot �s a Patch-ek k�z�tt
	/**
	 * Find the target Patch from Arena's patchList.
	 */
	private void getTarget(){
		Skeleton.printLastCalledFunction(id);

		ArrayList<Patch> p = arena.getPatches();
		Patch minDist = p.get(0);
		for(int i = 1; i < p.size(); i++){
			// Patch-ek �s a CleanerMaster coordin�t�inak kivon�sa, l�p�ssz�m kital�l�sa
			// Legk�zelebbi Patch kiv�laszt�sa
				
		}
		
	}
	
	// K�zel�t� megold�s A* �tkeres�s szer�
	// A szomsz�dok �s a Target koordin�t�j�nak k�l�nbs�ge alapj�n megkapom, hogy h�nyat kell l�pni a 
	// szomsz�dt�l a Target-ig.
	// Kiv�lasztom azt a szomsz�dot, amelyik a legkevesebb l�p�sre van a Target-t�l
	// Ha a kiv�lasztott szomsz�d t�pusa DangerZone, akkor a m�sodik legk�zelebbit v�lasztom �s �gy tov�bb...
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
