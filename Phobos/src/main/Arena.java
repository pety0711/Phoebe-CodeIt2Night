package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;



import main.Skeleton.UseCaseType;

/**
 * The Class Arena.
 */
public class Arena {
	
	public static final int robot0StartField = 5;
	public static final int robot1StartField = 6;
	
	/** */
	private String arenaID;
	
	/** The fields. */
	private ArrayList<Field> fields;
	
	/** Patches - temporary*/
	private ArrayList<Patch> patches;
	
	/** The gamers. */
	private HashMap<String, Robot> gamers;
	
	/** The dimension*/
	private CoordVector dim;
	
//	private static ArrayList<CoordVector> aviableCoords; //
//	private static int aviableCoordsNext;
	
	/** Number of Robots */
	private int noRobots = 2;
	
	public Arena(String id) {
		arenaID = id;
		if(Skeleton.currentUseCase==UseCaseType.New_game)
			Skeleton.drawLine();
		//Skeleton.printLastCalledFunction(arenaID, new String[]{""});
		Initialize();
	}
	
	public Arena(){
		if(Skeleton.currentUseCase==UseCaseType.New_game)
			Skeleton.drawLine();
		//Skeleton.printLastCalledFunction(arenaID, new String[]{""}); //Ki�rat�s
		arenaID = "arena";
		Initialize();
	}
	private void Initialize() {
		Skeleton.printLastCalledFunction(arenaID);

		int[] tmp = {5, 2};
		try {
			dim = new CoordVector(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		generateFields(dim);
		
		gamers = new HashMap<String, Robot>();
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Collision||
				Skeleton.currentUseCase==Skeleton.UseCaseType.Finish_game){
			noRobots=2;
		}
		else 
			noRobots = 1;
		for (int i = 0; i < noRobots; i++)
		{
			addRobot("Robot" + i);
		}

		Set<String> keys = gamers.keySet();
		gamers.get(keys.toArray()[0]).setField(
				fields.get(robot0StartField));
		fields.get(robot0StartField).steppedOnYou(
				gamers.get(keys.toArray()[0]));
		if (Skeleton.currentUseCase == UseCaseType.Collision||
			Skeleton.currentUseCase == UseCaseType.Finish_game){			
			gamers.get(keys.toArray()[1]).setField(
					fields.get(robot1StartField));
			fields.get(robot1StartField).steppedOnYou(
					gamers.get(keys.toArray()[1]));
		}		
	}

	/**
	 * Generate fields.
	 *
	 * @param size the size
	 */
	public void generateFields(CoordVector size) {
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{"size","CoordVector"});

		
		//TODO erre valami algoritmust kital�lni, k�l�nben a p�lya megalkot�s�ba fogunk belez�ld�lni...
		//+ be kell �ll�tani a szomsz�djaikat
		SafeZone s0 = new SafeZone("s0");
		SafeZone s1 = new SafeZone("s1");
		SafeZone s2 = new SafeZone("s2");
		SafeZone sp = new SafeZone("sp");
		SafeZone so = new SafeZone("so");
		SafeZone sr0 = new SafeZone("sr0");
		SafeZone sr1 = new SafeZone("sr1");
		//TODO n�v param�ternek
		DangerZone d = new DangerZone();

		patches = new ArrayList<Patch>();
		Putty p = new Putty("p");
		p.setFix();
		patches.add(p);
		sp.addPutty(p);
		
		Oil o = new Oil("o");
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
		
		//TODO na meg ez is probl�ma lesz, meg szerintem nem is olyan j� mert mindenkinek ugyanaz a szomsz�dja mindenki mindenkivel szomsz�dos ebben az esetben!
		List<Field> temp = new ArrayList<Field>();
		temp.add(s0);
		temp.add(s1);
		temp.add(s2);
		temp.add(sp);
		temp.add(so);
		temp.add(sr0);
		temp.add(sr1);
		temp.add(d);

		//TODO valakinek van �tlete erre?
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
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{id,Skeleton.getClassName(id)});
		
		gamers.put(id, new Robot(id, this));
	}
	
	public void registerPatch(Patch p) {
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{p.id,Skeleton.getClassName(p)});
		
		patches.add(p);
	}
	
	/**
	 * Kill robot.
	 *
	 * @param points the points
	 * @param id the id
	 */
	
	
	public void tick() {
		Set<String> keys = gamers.keySet();
		switch(Skeleton.currentUseCase) {	
			case Put_oil:
				Skeleton.drawLine();
				gamers.get(keys.toArray()[0]).putOil();
				Skeleton.drawLine();
	
				break;
			case Put_putty:
				Skeleton.drawLine();
				gamers.get(keys.toArray()[0]).putPutty();
				Skeleton.drawLine();
				break;
			default:
				break;
			}
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Collision)
			Skeleton.drawLine();
		for (String key : keys) {
			gamers.get(key).tick();
		}
		
		for (String key : keys) {
			if(gamers.get(key).isItAlive)
				gamers.get(key).investigateCollision();
		}
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Collision)
			Skeleton.drawLine();
		for (Patch p : patches) {
			p.tick();
		}
		
		
	}
	public void killRobot(int points, String id){
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{"points","int", "id","String"});
		Skeleton.printOutINFO(id + " Robot died, points: " + points);
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_dangerzone)
			Skeleton.drawLine();
	}

	public void finishGame() {
		Skeleton.printLastCalledFunction(arenaID);		
		Set<String> idSet = gamers.keySet();
		for (String robotId : idSet) {
			gamers.get(robotId).killRobot();
		}
		
		gamers.clear();
	}
}

//private void calcCoords(CoordVector size){
//ArrayList<CoordVector> coords = new ArrayList<>();
//
////be�ll�tjuk a Permut�ci�t
//int[] n = new int[size.dimension];
//int[] Nr = new int[size.dimension];
//for (int i = 0; i < size.dimension; i++) {
//	Nr[i]=size.getCoordofDim(i+1)-1; //ez el�g fura de a dimenzi�t 1-t�l sz�moljuk a getCoordofDim pedig 0-t�l indexelve adja vissza de ez is dimenzi�s.
//}
//printPermutations(n, Nr, 0);
//}
//
///**
//* Hozz�adja az aviableCoords list�hoz hozz�ad CoordVector t�pus� objektumokat, amik
//* lefedik az �sszes lehets�ges koordin�ta permut�ci�j�t. �gy kigener�l�dik a megadott 
//* dimenzi�k szerint az el�rhet� CoordVector lista. pl.: 3 dimenzi�n�l
//* bemenet int[]{1,2,1}
//* gener�lt koordin�t�k [0,0,0],[0,1,0], teh�t ilyenkor ne hozzunk l�tre 3 elemet,
//* mert nem tudja feltenni az ar�na
//*
//* @param n seg�dt�mb
//* @param Nr maxim�lis koordin�t�kat t�rol� t�mb
//* @param idx kezd� index ahonnan gener�l
//* @link http://stackoverflow.com/questions/9632677/combinatorics-generate-all-states-array-combinations
//*/
//public static void printPermutations(int[] n, int[] Nr, int idx) {
//if (idx == n.length) {  //stop condition for the recursion [base clause]
//    //System.out.println(Arrays.toString(n)); //csak ki�rat�shoz kell
//    try {
//		aviableCoords.add(new CoordVector(n)); //hozz�adjuk az el�rhet� koordin�t�k list�j�hoz
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//    return;
//}
//for (int i = 0; i <= Nr[idx]; i++) { 
//    n[idx] = i;
//    printPermutations( n, Nr, idx+1); //recursive invokation, for next elements
//}
//}

//private void genAll(){
//	fields = new ArrayList<Field>();
//	patches = new ArrayList<Patch>();
//	SafeZone s = new SafeZone("s");
//	SafeZone s1 = new SafeZone("s1");
//	SafeZone s2 = new SafeZone("s2");
//	SafeZone s3 = new SafeZone("s3");
//	Oil o = new Oil("o");
//	Putty p = new Putty("p");/*
//	SafeZone s3 = new SafeZone("s3");
//	SafeZone s1 = new SafeZone("s1");
//	SafeZone s2 = new SafeZone("s2");
//	SafeZone s3 = new SafeZone("s3");*/
//	ArrayList<Field> neighbours0 = new ArrayList<Field>();
//	ArrayList<Field> neighbours1 = new ArrayList<Field>();
//	ArrayList<Field> neighbours2 = new ArrayList<Field>();
//}
//private void putPuttyGen(){		
//	fields = new ArrayList<Field>();
//	patches = new ArrayList<Patch>();
//	ArrayList<Field> neighbours0 = new ArrayList<Field>();
//
//	ArrayList<Field> neighbours1 = new ArrayList<Field>();
//
//	ArrayList<Field> neighbours2 = new ArrayList<Field>();
//	
//	SafeZone s1 = new SafeZone("s1");
//	SafeZone s2 = new SafeZone("s2");
//	SafeZone s3 = new SafeZone("s3");
//	s1.setCoord(aviableCoords.get(aviableCoordsNext++));
//	s2.setCoord(aviableCoords.get(aviableCoordsNext++));
//	s3.setCoord(aviableCoords.get(aviableCoordsNext++));
//	
//	Putty p = new Putty();
//	p.setFix();
//	patches.add(p);
//	s3.addPutty(p);
//	
//	fields.add(s1);
//	fields.add(s2);
//	fields.add(s3);
//	
//	neighbours0.add(s2);
//	s1.setNeighbours(neighbours0);
//
//	neighbours1.add(s1);
//	neighbours1.add(s3);
//	s2.setNeighbours(neighbours1);
//	
//	neighbours2.add(s2);
//	s3.setNeighbours(neighbours2);
//	
//	int robot0StartField = 0;
//	gamers = new HashMap<String, Robot>();
//	addRobot("Robot" + "0");
//	Set<String> keys4 = gamers.keySet();
//	gamers.get(keys4.toArray()[0]).setField(fields.get(robot0StartField));
//	
//}
/*
Ezt l�gysz� ne t�r�ld ha �tban van se, nem tudom hogy ez �gy el�g eleg�ns megold�s e az el�z� vagy ink�bb ezzel k�ne folytatni? ez el�g bonyolult� teszi ha m�r �gy nem az.
HashMap<String,String> b = new HashMap<String,String>();
b.put("size", "CoordVector");
b.put("Alma", "String");
b.put("K�rte", "String");
ArrayList<HashMap<String,String>> l = new ArrayList<HashMap<String,String>>();
l.add(b);
//Skeleton.printLastCalledFunction(arenaID, b);
*/
