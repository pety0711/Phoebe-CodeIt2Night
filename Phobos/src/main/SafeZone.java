/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Random;

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
		robots.add(r);
		r.setField(this);
		
		for (Patch patch : patches) {
			//if(Skeleton.getClassName(patch)==(Skeleton.getClassName(new Putty())))
			if(patch.getClass()==Putty.class)
				r.slowDown();
			else 
				r.disableMovement();
		}
	}
	
	@Override
	public void steppedOffYou(Robot r) {
		Skeleton.printLastCalledFunction(id, new String[]
				{r.id,Skeleton.getClassName(r)});
		if(robots.contains(r)){
			robots.remove(r);
		}
	}
	
	@Override
	public Field step(CoordVector direction, Robot r){
		Skeleton.printLastCalledFunction(id, new String[]
				{"direction",Skeleton.getClassName(direction)});
		Field nb = getNeighbour(direction);
		switch (Skeleton.currentUseCase) {
			case Put_oil:
				nb = this;
				break;
			case Put_putty:
				nb = this;
				break;
			default:
				break;
		}
		//robots.remove(0);
		steppedOffYou(r);
		return nb;
	}
	
	@Override
	public void investigateCollision() {
		Skeleton.printLastCalledFunction(id);
		switch(Skeleton.currentUseCase){
		case Collision:
			if( (robots.get(0) != null) && robots.get(1) != null ){
				robots.get(0).detectCollision(coord);
				robots.get(1).detectCollision(coord);
				/*for (Robot r : robots) {
					r.detectCollision(coord);
				}*/
			}
			Skeleton.drawLine();
			break;
		case Close_tester:
			break;
		case Finish_game:
			break;
		case New_game:
			break;
		case Put_oil:
			break;
		case Put_putty:
			break;
		case Step_on_a_dangerzone:
			break;
		case Step_on_a_safezone:
			break;
		case Stepping_on_a_putty:
			break;
		case Stepping_on_an_oil:
			break;
		default:
			break;
		}
	}
	
	public void addPutty(Putty p){
		Skeleton.printLastCalledFunction(id,new String[]
				{p.id,Skeleton.getClassName(p)});
		//pList.add(new Putty());
		pList.add(p);
		patches.add(p);
	}
	public void addOil(Oil o){     //Ez kell ide?? 
		Skeleton.printLastCalledFunction(id,new String[]
				{o.id,Skeleton.getClassName(o)});
		//oList.add(new Oil());
		oList.add(o);
		patches.add(o);
	}

	@Override
	public Field getNeighbour(CoordVector direction) {
		Skeleton.printLastCalledFunction(id,new String[]
				{"direction",Skeleton.getClassName(direction)});
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
		case Close_tester:
			break;
		case Finish_game:
			returnField = neighbours.get(0);
			break;
		case New_game:
			returnField = neighbours.get(0);
			break;
		case Put_oil:
			returnField = neighbours.get(0);
			break;
		case Put_putty:
			returnField = neighbours.get(0);
			break;
		default:
			break;
			}
		return returnField;
	}
}
