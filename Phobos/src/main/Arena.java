package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class Arena.
 */
public class Arena {

	/** The arena id. */
	private String arenaID;

	/** The fields. */
	private ArrayList<Field> fields;

	/** Coords of patches - temporary. */
	private ArrayList<CoordVector> patchesCoords;

	/** The gamers. */
	private HashMap<String, Robi> gamers;

	/** The cleaners. */
	private HashMap<String, CleanerMaster> cleaners;

	/** The dimension. */
	private CoordVector dim;

	/** The mapFilePath. */
	private String mapFilePath;

	private int sCounter = 0;
	private int dCounter = 0;
	private int rCounter = 0;
	private int kCounter = 0;

	private static AtomicInteger idCounter = new AtomicInteger();

	public static String createID() {
		return String.valueOf(idCounter.getAndIncrement());
	}

	/**
	 * Instantiates a new arena.
	 *
	 * @param id
	 *            the id
	 */
	public Arena(String id, String path) {
		this.arenaID = id;
		mapFilePath = path;
		Initialize();
	}

	/**
	 * Instantiates a new arena.
	 */
	public Arena(String path) {
		this.arenaID = "arena" + createID();
		arenaID = "arena";
		mapFilePath = path;
		Initialize();
	}

	/**
	 * Initialize.
	 */
	private void Initialize() {
		gamers = new HashMap<String, Robi>();
		cleaners = new HashMap<String, CleanerMaster>();
		patchesCoords = new ArrayList<CoordVector>();
		fields = new ArrayList<Field>();
		generateFields();
	}

	/**
	 * Generate fields.
	 *
	 * @param size
	 *            the size
	 */
	public void generateFields() {
		FileReader mapFile = null;
		BufferedReader bf = null;
		try {
			mapFile = new FileReader(mapFilePath);
			bf = new BufferedReader(mapFile);

			int coord_dim_x = 0;
			int coord_dim_y = 0;

			ArrayList<String[]> tempFields = new ArrayList<String[]>();

			String line;
			while ((line = bf.readLine()) != null) {
				coord_dim_y++;

				String[] row = line.split(",");

				if (coord_dim_x == 0) {
					coord_dim_x = row.length;
				}

				tempFields.add(row);
			}
			Collections.reverse(tempFields);
			for (String[] str : tempFields) {
				createFields(str);
			}

			dim = new CoordVector(coord_dim_x, coord_dim_y);

			setNeighbourhood();
			initPatchCoords();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					//
				}
			}
			if (mapFile != null) {
				try {
					bf.close();
				} catch (IOException e) {
					//
				}
			}
		}
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public ArrayList<Field> getFields() {
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields
	 *            the new fields
	 */
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	/**
	 * Gets the dim.
	 *
	 * @return the dim
	 */
	public CoordVector getDim() {
		return dim;
	}

	/**
	 * Sets the dim.
	 *
	 * @param dim
	 *            the new dim
	 */
	public void setDim(CoordVector dim) {
		this.dim = dim;
	}

	/**
	 * The Arena here creates the Fields.
	 *
	 * @param row
	 *            the fields which will be created
	 */
	private void createFields(String[] row) {
		// drawLine(row.length);
		List<String> data = new ArrayList<String>();

		for (String element : row) {
			element = element.toLowerCase();
			switch (element) {
			case "s":
				SafeZone ss = new SafeZone("s" + sCounter++);
				ss.arena = this;
				fields.add(ss);

				break;

			case "r":
				Robi rr = new Robi("r" + rCounter++, this);
				gamers.put(rr.id, rr);
				SafeZone sr = new SafeZone("s" + sCounter++);
				sr.arena = this;
				fields.add(sr);
				rr.setField(sr);
				sr.steppedOnYou(rr);

				break;

			case "k":
				CleanerMaster cm = new CleanerMaster("k" + kCounter++, this);
				cleaners.put(cm.id, cm);
				SafeZone sk = new SafeZone("s" + sCounter++);
				sk.arena = this;
				fields.add(sk);
				cm.setField(sk);
				sk.steppedOnYou(cm);

				break;

			case "o":
				SafeZone so = new SafeZone("s" + sCounter++);
				so.arena = this;
				so.addOil();
				fields.add(so);

				break;

			case "p":
				SafeZone sp = new SafeZone("s" + sCounter++);
				sp.arena = this;
				sp.addPutty();
				fields.add(sp);

				break;

			case "d":
				DangerZone dd = new DangerZone("d" + dCounter++);
				dd.arena = this;
				fields.add(dd);

			default:
				break;
			}
			// Prototype.printOut("adsadsa");
		}
	}

	private void setNeighbourhood() {
		for (int i = 0; i < fields.size(); i++) {
			int y = (int) Math.floor(i / dim.getX());
			int x = i % dim.getX();

			fields.get(i).setCoord(new CoordVector(x, y));

			ArrayList<Field> neighbours = new ArrayList<Field>();
			// left neighbour

			Field le = new DangerZone("edge");
			if (i % dim.getX() > 0) {
				le = fields.get(i - 1);
			}
			neighbours.add(le);

			Field up = new DangerZone("edge");
			// upper neighbour
			if (i <= fields.size() - 1 - dim.getX()) {
				up = fields.get(i + dim.getX());
			}
			neighbours.add(up);

			Field ri = new DangerZone("edge");
			// right neighbour
			if (i % dim.getX() < dim.getX() - 1) {
				ri = fields.get(i + 1);
			}
			neighbours.add(ri);

			// bottom neighbour
			Field bo = new DangerZone("edge");
			if (i >= dim.getX()) {
				bo = fields.get(i - dim.getX());
			}
			neighbours.add(bo);

			fields.get(i).setNeighbours(neighbours);
		}
	}

	private void initPatchCoords() {
		for (Field f : fields) {
			f.addPatchesNow();
		}
	}

	/**
	 * Gets the mapFilePath.
	 *
	 * @return the mapFilePath
	 */
	public String getMapFilePath() {
		return mapFilePath;
	}

	/**
	 * Sets the mapFilePath.
	 *
	 * @param mapFilePath
	 *            the new mapFilePath
	 */
	public void setMapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
	}

	/**
	 * Adds the robot.
	 *
	 * @param id
	 *            the id
	 */
	public void addRobot(String id) {
		gamers.put(id, new Robi(id, this));
	}

	/**
	 * Gets the Robies/gamers.
	 *
	 * @return the gamers
	 */
	public ArrayList<Robi> getGamers() {
		ArrayList<Robi> temp = new ArrayList<Robi>();

		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			temp.add(gamers.get(key));
		}

		return temp;
	}

	public ArrayList<Robot> getRobots() {
		ArrayList<Robot> temp = new ArrayList<Robot>();

		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			temp.add(gamers.get(key));
		}

		return temp;
	}

	// /**
	// * Register patch.
	// *
	// * @param p the p
	// */
	// public void registerPatch(Patch p) {
	// Skeleton.printLastCalledFunction(arenaID, new String[]
	// {p.id,Skeleton.getClassName(p)});
	//
	// patches.add(p);
	// }
	//
	// /**
	// * Get patches list.
	// */
	//
	// public ArrayList<Patch> getPatches() {
	// return patches;
	// }

	/**
	 * Register patch's CoordVector.
	 *
	 * @param p
	 *            the p
	 */
	public void registerPatchCoord(CoordVector c) {
		// Skeleton.printLastCalledFunction(arenaID, new String[]
		// {c.toString(), Skeleton.getClassName(c)});
		//
		patchesCoords.add(c);
	}

	/**
	 * Get patches Coord list.
	 */

	public ArrayList<CoordVector> getPatchesCoords() {
		return patchesCoords;
	}

	/**
	 * Gets the specific Field by string.
	 *
	 * @param id
	 *            the specific Field's id
	 */
	public Field getFieldById(String id) {
		for (Field f : fields) {
			if (f.id.equals(id)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * tick
	 */
	public void tick() {

		// foltok tickel�se
		for (Field f : fields) {
			if (("SafeZone").equals(f.getClass().getSimpleName())) {
				for (Patch patch : f.patches) {
					if (("Oil").equals(patch.getClass().getSimpleName())) {
						((Oil) patch).tick();
					}
				}
			}
		}

		// foltok lerak�sa
		for (Field f : fields) {
			f.addPatchesNow();
		}

		// foltok t�rl�se
		for (Field f : fields) {
			if (("SafeZone").equals(f.getClass().getSimpleName())) {
				if ((((SafeZone) f).checkPatch()))
					patchesCoords.remove(f.getCoord());

				boolean cleaned = ((SafeZone) f).haveToCleanPatch();
				if (cleaned) {
					patchesCoords.remove(f.getCoord());
				}
			}
		}

		// robotok l�ptet�se
		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			if (gamers.get(key).isItAlive)
				gamers.get(key).tick();
		}

		keys = cleaners.keySet();
		for (String key : keys) {
			if (cleaners.get(key).isItAlive)
				cleaners.get(key).tick();
		}

		// �tk�z�s vizsg�lat
		keys = gamers.keySet();
		for (String key : keys) {
			if (gamers.get(key).isItAlive)
				try {
					gamers.get(key).investigateCollision();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		keys = cleaners.keySet();
		for (String key : keys) {
			if (cleaners.get(key).isItAlive)
				try {
					cleaners.get(key).investigateCollision();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		// foltok �rv�nyes�t�se
		keys = gamers.keySet();
		for (String key : keys) {
			gamers.get(key).takeEffect();
		}
	}

	/**
	 * Kill Robi.
	 *
	 * @param points
	 *            the points
	 * @param id
	 *            the id
	 */
	public void killRobot(int points, String id) {
		// Skeleton.printLastCalledFunction(arenaID, new String[]
		// {"points","int", "id","String"});
		// Skeleton.printOutINFO(id + " Robi died, points: " + points);
		// if(Skeleton.currentUseCase==Skeleton.UseCaseType.Step_on_a_dangerzone)
		// Skeleton.drawLine();
	}

	/**
	 * Kill CleanerMaster.
	 *
	 * @param id
	 *            the id
	 */
	public void killRobot(String id) {
		Set<String> keys = gamers.keySet();
		for (String key : keys) {
			gamers.get(key).takeEffect();
		}
	}

	/**
	 * Finish game.
	 */
	public void finishGame() {
		// Skeleton.printLastCalledFunction(arenaID);
		Set<String> idSet = gamers.keySet();
		for (String robotId : idSet) {
			gamers.get(robotId).killRobot();
		}

		gamers.clear();
	}

	// private Field getFieldByCoord(CoordVector cv) {
	// for (Field f : fields) {
	// if(f.getCoord().equals(cv))
	// return f;
	// }
	// return null;
	// }
	//
	// private void writeOutMap() {
	// StringBuilder text = new
	// StringBuilder("+--------------------------------------------------+");
	// for (int i = dim.getY(); i >=0; i++) {
	// for (int j = 0; j < dim.getX(); j++) {
	// getFieldByCoord(new CoordVector(j, i));
	// }
	// }
	// }
}

// private void calcCoords(CoordVector size){
// ArrayList<CoordVector> coords = new ArrayList<>();
//
// //be�ll�tjuk a Permut�ci�t
// int[] n = new int[size.dimension];
// int[] Nr = new int[size.dimension];
// for (int i = 0; i < size.dimension; i++) {
// Nr[i]=size.getCoordofDim(i+1)-1; //ez el�g fura de a dimenzi�t 1-t�l
// sz�moljuk a getCoordofDim pedig 0-t�l indexelve adja vissza de ez is
// dimenzi�s.
// }
// printPermutations(n, Nr, 0);
// }
//
// /**
// * Hozz�adja az aviableCoords list�hoz hozz�ad CoordVector t�pus�
// objektumokat, amik
// * lefedik az �sszes lehets�ges koordin�ta permut�ci�j�t. �gy kigener�l�dik a
// megadott
// * dimenzi�k szerint az el�rhet� CoordVector lista. pl.: 3 dimenzi�n�l
// * bemenet int[]{1,2,1}
// * gener�lt koordin�t�k [0,0,0],[0,1,0], teh�t ilyenkor ne hozzunk l�tre 3
// elemet,
// * mert nem tudja feltenni az ar�na
// *
// * @param n seg�dt�mb
// * @param Nr maxim�lis koordin�t�kat t�rol� t�mb
// * @param idx kezd� index ahonnan gener�l
// * @link
// http://stackoverflow.com/questions/9632677/combinatorics-generate-all-states-array-combinations
// */
// public static void printPermutations(int[] n, int[] Nr, int idx) {
// if (idx == n.length) { //stop condition for the recursion [base clause]
// //System.out.println(Arrays.toString(n)); //csak ki�rat�shoz kell
// try {
// aviableCoords.add(new CoordVector(n)); //hozz�adjuk az el�rhet� koordin�t�k
// list�j�hoz
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return;
// }
// for (int i = 0; i <= Nr[idx]; i++) {
// n[idx] = i;
// printPermutations( n, Nr, idx+1); //recursive invokation, for next elements
// }
// }

// private void genAll(){
// fields = new ArrayList<Field>();
// patches = new ArrayList<Patch>();
// SafeZone s = new SafeZone("s");
// SafeZone s1 = new SafeZone("s1");
// SafeZone s2 = new SafeZone("s2");
// SafeZone s3 = new SafeZone("s3");
// Oil o = new Oil("o");
// Putty p = new Putty("p");/*
// SafeZone s3 = new SafeZone("s3");
// SafeZone s1 = new SafeZone("s1");
// SafeZone s2 = new SafeZone("s2");
// SafeZone s3 = new SafeZone("s3");*/
// ArrayList<Field> neighbours0 = new ArrayList<Field>();
// ArrayList<Field> neighbours1 = new ArrayList<Field>();
// ArrayList<Field> neighbours2 = new ArrayList<Field>();
// }
// private void putPuttyGen(){
// fields = new ArrayList<Field>();
// patches = new ArrayList<Patch>();
// ArrayList<Field> neighbours0 = new ArrayList<Field>();
//
// ArrayList<Field> neighbours1 = new ArrayList<Field>();
//
// ArrayList<Field> neighbours2 = new ArrayList<Field>();
//
// SafeZone s1 = new SafeZone("s1");
// SafeZone s2 = new SafeZone("s2");
// SafeZone s3 = new SafeZone("s3");
// s1.setCoord(aviableCoords.get(aviableCoordsNext++));
// s2.setCoord(aviableCoords.get(aviableCoordsNext++));
// s3.setCoord(aviableCoords.get(aviableCoordsNext++));
//
// Putty p = new Putty();
// p.setFix();
// patches.add(p);
// s3.addPutty(p);
//
// fields.add(s1);
// fields.add(s2);
// fields.add(s3);
//
// neighbours0.add(s2);
// s1.setNeighbours(neighbours0);
//
// neighbours1.add(s1);
// neighbours1.add(s3);
// s2.setNeighbours(neighbours1);
//
// neighbours2.add(s2);
// s3.setNeighbours(neighbours2);
//
// int robot0StartField = 0;
// gamers = new HashMap<String, Robot>();
// addRobot("Robot" + "0");
// Set<String> keys4 = gamers.keySet();
// gamers.get(keys4.toArray()[0]).setField(fields.get(robot0StartField));
//
// }
/*
 * Ezt l�gysz� ne t�r�ld ha �tban van se, nem tudom hogy ez �gy el�g eleg�ns
 * megold�s e az el�z� vagy ink�bb ezzel k�ne folytatni? ez el�g bonyolult�
 * teszi ha m�r �gy nem az. HashMap<String,String> b = new
 * HashMap<String,String>(); b.put("size", "CoordVector"); b.put("Alma",
 * "String"); b.put("K�rte", "String"); ArrayList<HashMap<String,String>> l =
 * new ArrayList<HashMap<String,String>>(); l.add(b);
 * //Skeleton.printLastCalledFunction(arenaID, b);
 */
