/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SafeZone.
 *
 * @author Dávid
 */
public class SafeZone extends Field {

	/** The p list. */
	private ArrayList<Putty> pList;
	
	/** The o list. */
	private ArrayList<Oil> oList;
	
	
	/**
	 * Instantiates a new safe zone.
	 */
	public SafeZone() {
		Random r = new Random();
		id = ""+r.nextInt(2000000000);
		Skeleton.printLastCalledFunction(id, new String[]{id,Skeleton.getClassName(id)});
		
		pList = new ArrayList<>();
		oList = new ArrayList<>();

		Skeleton.printLastCalledFunction(id, new String[]{""});
	}
	
	/**
	 * Instantiates a new safe zone.
	 *
	 * @param id the id
	 */
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
		//r.setField(this);
		
		for (Patch patch : patches) {
			//if(Skeleton.getClassName(patch)==(Skeleton.getClassName(new Putty())))
			if(patch.getClass()==Putty.class)
				r.slowDown();
			else 
				r.disableMovement();
		}
	}

	public void steppedOffYou(Robot r) {
		if(robots.contains(r)){
			robots.remove(r);
		}
	}
	
	/* (non-Javadoc)
	 * @see main.Field#step(main.CoordVector, main.Robot)
	 */
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
	
	/* (non-Javadoc)
	 * @see main.Field#investigateCollision()
	 */
	@Override
	public void investigateCollision() throws Exception {
		Skeleton.printLastCalledFunction(id);
		switch(Skeleton.currentUseCase){
		case Collision:
			if( robots.size() > 1 && (robots.get(0) != null) && robots.get(1) != null ){
				CoordVector cv0 = new CoordVector(1, 0);
				CoordVector cv1 = new CoordVector(1, 1);
				robots.get(1).detectedCollision(cv1);
				robots.get(0).detectedCollision(cv0);
			}
			break;
		default:
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see main.Field#addPutty(main.Putty)
	 */
	public void addPutty(Putty p){
		Skeleton.printLastCalledFunction(id,new String[]
				{p.id,Skeleton.getClassName(p)});
		//pList.add(new Putty());
		pList.add(p);
		patches.add(p);
	}
	
	/* (non-Javadoc)
	 * @see main.Field#addOil(main.Oil)
	 */
	public void addOil(Oil o){  
		Skeleton.printLastCalledFunction(id,new String[]
				{o.id,Skeleton.getClassName(o)});
		//oList.add(new Oil());
		oList.add(o);
		patches.add(o);
	}

	/* (non-Javadoc)
	 * @see main.Field#getNeighbour(main.CoordVector)
	 */
	@Override
	public Field getNeighbour(CoordVector direction) {
		Skeleton.printLastCalledFunction(id,new String[]
				{"direction",Skeleton.getClassName(direction)});
		Field returnField = null;
		switch (Skeleton.currentUseCase) {
			case Collision:
				try {
					if (direction.equals(new CoordVector(1, 0))) {
						returnField = neighbours.get(1);
					} else {
						returnField = neighbours.get(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case Step_on_a_dangerzone:
				returnField = neighbours.get(7);
				break;
			case Step_on_a_safezone:
				returnField = neighbours.get(2);
				break;
			case Step_on_a_putty:
				returnField = neighbours.get(3);
				break;
			case Step_on_an_oil:
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

	/* (non-Javadoc)
	 * @see main.Field#initField()
	 */
	@Override
	public void initField() {
		Skeleton.printLastCalledFunction(id);
	}
}
