package main;

import java.awt.BufferCapabilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;








import main.Skeleton.UseCaseType;

// TODO: Auto-generated Javadoc
/**
 * The Class Arena.
 */
public class Arena {
	
	/** The Constant robot0StartField. */
	public static final int robot0StartField = 5;
	
	/** The Constant robot1StartField. */
	public static final int robot1StartField = 6;
	
	/** The arena id. */
	private String arenaID;
	
	/** The fields. */
	private ArrayList<Field> fields;
	
	/**  Patches - temporary. */
	private ArrayList<Patch> patches;
	
	/** The gamers. */
	private HashMap<String, Robi> gamers;
	
	/**  The dimension. */
	private CoordVector dim;
	
	private String mapFilePath;
	
	/**  Number of Robots. */
	private int noRobots = 2;
	
	private int sCounter = 0;
	private int dCounter = 0;
	private int oCounter = 0;
	private int pCounter = 0;
	private int rCounter = 0;
	private int kCounter = 0;
	
	/**
	 * Instantiates a new arena.
	 *
	 * @param id the id
	 */
	public Arena(String id) {
		arenaID = id;
		if(Skeleton.currentUseCase==UseCaseType.New_game)
			Skeleton.drawLine();
		//Skeleton.printLastCalledFunction(arenaID, new String[]{""});
		Initialize();
	}
	
	/**
	 * Instantiates a new arena.
	 */
	public Arena(){
		if(Skeleton.currentUseCase==UseCaseType.New_game)
			Skeleton.drawLine();
		//Skeleton.printLastCalledFunction(arenaID, new String[]{""}); //Kiíratás
		arenaID = "arena";
		Initialize();
	}
	
	/**
	 * Initialize.
	 */
	private void Initialize() {
		Skeleton.printLastCalledFunction(arenaID);
		
		generateFields();
		
		gamers = new HashMap<String, Robi>();
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
		gamers.get(keys.toArray()[0]).setField(fields.get(robot0StartField));
		fields.get(robot0StartField).steppedOnYou(gamers.get(keys.toArray()[0]));
		if (Skeleton.currentUseCase == UseCaseType.Collision || Skeleton.currentUseCase == UseCaseType.Finish_game){			
			gamers.get(keys.toArray()[1]).setField(fields.get(robot1StartField));
			fields.get(robot1StartField).steppedOnYou(gamers.get(keys.toArray()[1]));
		}		
	}

	/**
	 * Generate fields.
	 *
	 * @param size the size
	 */
	public void generateFields() {
		try {
			FileReader mapFile = new FileReader(mapFilePath);
			BufferedReader bf = new BufferedReader(mapFile);
			
			int coord_dim_x = 0;
			int coord_dim_y = 0;
			
			String line;
			while( (line = bf.readLine()) != null) {
				coord_dim_x++;
							
				String[] row = line.split(",");

				if(coord_dim_y == 0) {
					coord_dim_y = row.length;
					dim = new CoordVector(new int[]{coord_dim_x, coord_dim_y});
				}
				
				createFields(row);
			}
		}
		catch (Exception e) {
			
		}
	}
	
	private void createFields(String[] row)  {

		for(String element : row) {
			switch (element) {
			case "s":
				SafeZone ss = new SafeZone("s" + sCounter++);
				fields.add(ss);
				break;
				
			case "d":
				DangerZone dd = new DangerZone("d" + dCounter++);
				fields.add(dd);
				break;
				
			case "r":
				Robi rr = new Robi("r" + rCounter++, this);
				gamers.put(rr.id, rr);
				SafeZone sr = new SafeZone("s" + sCounter++);
				fields.add(sr);
				rr.setField(sr);
				break;
				
			case "k":
				CleanerMaster cm = new CleanerMaster("k" + kCounter++, this);
				break;
			case "o":
				break;
			case "p":
				break;
			default:
				break;
			}
		}
		
	}
	
	public String getMapFilePath() {
		return mapFilePath;
	}

	public void setMapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
	}

	/**
	 * Adds the robot.
	 *
	 * @param id the id
	 */
	public void addRobot(String id){
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{id,Skeleton.getClassName(id)});
		
		gamers.put(id, new Robi(id, this));
	}
	
	/**
	 * Register patch.
	 *
	 * @param p the p
	 */
	public void registerPatch(Patch p) {
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{p.id,Skeleton.getClassName(p)});
		
		patches.add(p);
	}
	
	/**
	 * tick
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
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_safezone)
			Skeleton.drawLine();
		for (String key : keys) {
			if(gamers.get(key).isItAlive)
				try {
					gamers.get(key).investigateCollision();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Collision)
			Skeleton.drawLine();
		for (Patch p : patches) {
			p.tick();
		}
		
		
	}
	
	/**
	 * Kill robot.
	 *
	 * @param points the points
	 * @param id the id
	 */
	public void killRobot(int points, String id){
		Skeleton.printLastCalledFunction(arenaID, new String[]
				{"points","int", "id","String"});
		Skeleton.printOutINFO(id + " Robot died, points: " + points);
		if(Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_dangerzone)
			Skeleton.drawLine();
	}

	/**
	 * Finish game.
	 */
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
////beállítjuk a Permutációt
//int[] n = new int[size.dimension];
//int[] Nr = new int[size.dimension];
//for (int i = 0; i < size.dimension; i++) {
//	Nr[i]=size.getCoordofDim(i+1)-1; //ez elég fura de a dimenziót 1-tõl számoljuk a getCoordofDim pedig 0-tól indexelve adja vissza de ez is dimenziós.
//}
//printPermutations(n, Nr, 0);
//}
//
///**
//* Hozzáadja az aviableCoords listához hozzáad CoordVector típusú objektumokat, amik
//* lefedik az összes lehetságes koordináta permutációját. Így kigenerálódik a megadott 
//* dimenziók szerint az elérhetõ CoordVector lista. pl.: 3 dimenziónál
//* bemenet int[]{1,2,1}
//* generált koordináták [0,0,0],[0,1,0], tehát ilyenkor ne hozzunk létre 3 elemet,
//* mert nem tudja feltenni az aréna
//*
//* @param n segédtömb
//* @param Nr maximális koordinátákat tároló tömb
//* @param idx kezdõ index ahonnan generál
//* @link http://stackoverflow.com/questions/9632677/combinatorics-generate-all-states-array-combinations
//*/
//public static void printPermutations(int[] n, int[] Nr, int idx) {
//if (idx == n.length) {  //stop condition for the recursion [base clause]
//    //System.out.println(Arrays.toString(n)); //csak kiíratáshoz kell
//    try {
//		aviableCoords.add(new CoordVector(n)); //hozzáadjuk az elérhetõ koordináták listájához
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
Ezt légyszí ne töröld ha útban van se, nem tudom hogy ez így elég elegáns megoldás e az elõzõ vagy inkább ezzel kéne folytatni? ez elég bonyolultá teszi ha már így nem az.
HashMap<String,String> b = new HashMap<String,String>();
b.put("size", "CoordVector");
b.put("Alma", "String");
b.put("Körte", "String");
ArrayList<HashMap<String,String>> l = new ArrayList<HashMap<String,String>>();
l.add(b);
//Skeleton.printLastCalledFunction(arenaID, b);
*/
