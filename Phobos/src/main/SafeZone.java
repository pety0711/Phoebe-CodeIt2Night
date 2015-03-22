/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author D�vid
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
		
		pList = new ArrayList<>();
		oList = new ArrayList<>();
		
		/*
		pList.add(new Putty());
		pList.add(new Putty());
		pList.add(new Putty());
		
		oList.add(new Oil());
		oList.add(new Oil());
		oList.add(new Oil());*/
		Skeleton.printLastCalledFunction(id, new String[]{""});
	}
	
	public SafeZone(String id) {
		this.id = id;
		Skeleton.printLastCalledFunction(id, new String[]{id,Skeleton.getClassName(id)});
		
		pList = new ArrayList<>();
		oList = new ArrayList<>();
		/*
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
		Skeleton.printLastCalledFunction(id, new String[]{r.id,Skeleton.getClassName(r)});
		for (Patch patch : patches) {
			if(Skeleton.getClassName(patch)==(Skeleton.getClassName(new Putty())))
				r.slowDown();
			else 
				r.disableMovement();
		}
		robots.add(r);
	}
	
	@Override
	public void steppedOffYou(Robot r) {
		Skeleton.printLastCalledFunction(id, new String[]{r.id,Skeleton.getClassName(r)});
		robots.remove(robots.get(0));
	}
	
	@Override
	public Field step(CoordVector speed){
		Skeleton.printLastCalledFunction(id, new String[]{"speed",Skeleton.getClassName(speed)});
		//getNeighbour(direction);
		Field nb = getNeighbour(speed);
		switch (Skeleton.currentUseCase) {
			case Collision:
				break;
			case Step_on_a_dangerzone:
				this.steppedOffYou(robots.get(0));
				nb.steppedOnYou(robots.get(0));
				break;
			case Step_on_a_safezone:
				this.steppedOffYou(robots.get(0));
				nb.steppedOnYou(robots.get(0));
				break;
			case Stepping_on_a_putty:
				this.steppedOffYou(robots.get(0));
				nb.steppedOnYou(robots.get(0));
				break;
			case Stepping_on_an_oil:				
				this.steppedOffYou(robots.get(0));
				nb.steppedOnYou(robots.get(0));
				break;
			default:
				break;
		}
		return null;
	}
	
	@Override
	public void investigateCollision() {
		Skeleton.printLastCalledFunction(id);
		if( (robots.get(0) != null) && robots.get(1) != null ){
			robots.get(0).detectCollision(coord);
			robots.get(1).detectCollision(coord);
			
		}
		
	}
	
	
	public Field step(Vector speed) {

		Skeleton.printLastCalledFunction(id,new String[]{"speed","CoordVector"});
		return null;
	}
	
	public void addPutty(Putty p){
		Skeleton.printLastCalledFunction(id,new String[]{p.id,Skeleton.getClassName(p)});
		//pList.add(new Putty());
		pList.add(p);
	}
	public void addOil(Oil o){     //Ez kell ide?? 
		Skeleton.printLastCalledFunction(id,new String[]{o.id,Skeleton.getClassName(o)});
		//oList.add(new Oil());
		oList.add(o);
	}

	@Override
	public Field getNeighbour(CoordVector direction) {
		Skeleton.printLastCalledFunction(id,new String[]{"direction",Skeleton.getClassName(direction)});
		Field returnField = null;
		switch (Skeleton.currentUseCase) {
			case Collision:
				returnField = neighbours.get(0);
				break;
			case Step_on_a_dangerzone:
				returnField = neighbours.get(7);
				break;
			case Step_on_a_safezone:
				returnField = neighbours.get(2);
				break;
			case Stepping_on_a_putty:
				returnField = neighbours.get(3);
				break;
			case Stepping_on_an_oil:
				returnField = neighbours.get(4);
				break;
			}
		return returnField;
	}

}
