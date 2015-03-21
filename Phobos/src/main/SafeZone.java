/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author Dávid
 *
 */
public class SafeZone extends Field {

	private String id;
	private ArrayList<Putty> pList;
	private ArrayList<Oil> oList;
	
	
	public SafeZone() {
		Random r = new Random();
		id = ""+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id, new String[]{id,Skeleton.getClassName(id)});
		/*
		pList = new ArrayList<>();
		pList.add(new Putty());
		pList.add(new Putty());
		pList.add(new Putty());
		
		oList = new ArrayList<>();
		oList.add(new Oil());
		oList.add(new Oil());
		oList.add(new Oil());*/
		Skeleton.printLastCalledFunction(id, new String[]{""});
	}
	
	public SafeZone(String id) {
		this.id = id;
		Skeleton.printLastCalledFunction(id, new String[]{id,Skeleton.getClassName(id)});
		/*
		pList = new ArrayList<>();

		pList.add(new Putty());
		pList.add(new Putty());
		pList.add(new Putty());
		
		oList = new ArrayList<>();
		oList.add(new Oil());
		oList.add(new Oil());
		oList.add(new Oil());*/
	}
	
	/* (non-Javadoc)
	 * @see main.Field#steppedOnYou(main.Robot)
	 */
	@Override
	public void steppedOnYou(Robot r) {
		/*
		if(patches.size() > 0) {// Szerintem ez így már nem kell
			if(patches.get(0).getClass().equals(Oil.class)) {
				r.disableMovement();
			} else {
				r.slowDown();
			}
			
		}*/
		
		Skeleton.printLastCalledFunction(id, new String[]{""});
		for (Patch patch : patches) {
			if(Skeleton.getClassName(patch)==(Skeleton.getClassName(new Putty())))
				r.slowDown();
			else 
				r.disableMovement();
		}
	}
	@Override
	public Field step(CoordVector speed){
		Skeleton.printLastCalledFunction(id, new String[]{"speed",Skeleton.getClassName(speed)});
		//getNeighbour(direction);
		getNeighbour(speed);
		return null;
	}
	
	public void investigateCollision() {
		
	}
	
	
	public Field step(Vector speed) {

		Skeleton.printLastCalledFunction(id,new String[]{"speed","CoordVector"});
		return null;
	}
	
	public void addPutty(Putty p){
		Skeleton.printLastCalledFunction(id,new String[]{p.id,Skeleton.getClassName(p)});
	}
}
