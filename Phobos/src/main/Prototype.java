package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Prototype {

	private static Arena arena;
	private static ArrayList<Robi> gamers;

	private static boolean consoleOutput = true;
	private static boolean fileOutput = false;

	private static String filePath;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		printOut("Program is up! Waiting for commands...");
		while (true) {
			try {
				decodeCommand(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void decodeCommand(String line) {
		line = line.toLowerCase();
		String[] command = line.split(" ");
		switch (command[0]) {
		case "n":
			newGame("map.csv");
			break;
		case "m":
			newGame("newmap.csv");
			break;
		case "newgame":
			newGame(command[1]);
			break;
		case "setoutput":
			SetOutput(command[1], command[2]);
			break;
		case "readtest":
			ReadTest(command[1]);
			break;
		case "runtest":
			runTest(command[1]);
			break;
		case "putpatch":
			PutPatch(command[1], command[2]);
			break;
		case "setspeed":
			SetSpeed(command[1], command[2], command[3]);
			break;
		case "step":
			switch (command.length) {
			case 1:
				Step();
				break;
			case 2:
				Step(command[1]);
				break;
			default:
				Step(command[1], command[2]);
				break;
			}
			break;
		case "collide":
			Collide(command[1], command[2], command[3]);
			break;
		case "timeout":
			Timeout(command[1]);
			break;
		case "terminate":
			Terminate();
			break;
		default:
			break;
		}
	}

	private static void runTest(String string) {
		switch (string) {
		case "1":
			ReadTest("testcases/CleanerMasterCollideWithRobot_CleanerMasterIsFaster");
			Prototype.printOut("test running: CleanerMasterCollideWithRobot_CleanerMasterIsFaster");
			break;
		case "2":
			ReadTest("testcases/CleanerMasterStepsOnPatch");
			Prototype.printOut("test running: CleanerMasterStepsOnPatch");
			break;
		case "3":
			ReadTest("testcases/FourStepsOnPutty");
			Prototype.printOut("test running: FourStepsOnPutty");
			break;
		case "4":
			ReadTest("testcases/GameTimeout");
			Prototype.printOut("test running: GameTimeout");
			break;
		case "5":
			ReadTest("testcases/NewGameFromFile");
			Prototype.printOut("test running: NewGameFromFile");
			break;
		case "6":
			ReadTest("testcases/OilTimeout");
			Prototype.printOut("test running: OilTimeout");
			break;
		case "7":
			ReadTest("testcases/RobotCollideWithCleanerMaster_RobotIsFaster");
			Prototype.printOut("test running: RobotCollideWithCleanerMaster_RobotIsFaster");
			break;
		case "8":
			ReadTest("testcases/RobotPutsOil");
			Prototype.printOut("test running: RobotPutsOil");
			break;
		case "9":
			ReadTest("testcases/RobotPutsPutty");
			Prototype.printOut("test running: RobotPutsPutty");
			break;
		case "10":
			ReadTest("testcases/RobotsCollideWithDifferentSpeed");
			Prototype.printOut("test running: RobotsCollideWithDifferentSpeed");
			break;
		case "11":
			ReadTest("testcases/RobotsCollideWithSameSpeed");
			Prototype.printOut("test running: RobotsCollideWithSameSpeed");
			break;
		case "12":
			ReadTest("testcases/RobotStepsBySpeed");
			Prototype.printOut("test running: RobotStepsBySpeed");
			break;
		case "13":
			ReadTest("testcases/RobotStepsOnOil");
			Prototype.printOut("test running: RobotStepsOnOil");
			break;
		case "14":
			ReadTest("testcases/RobotStepsOnPutty");
			Prototype.printOut("test running: RobotStepsOnPutty");
			break;
		case "15":
			ReadTest("testcases/RobotStepsToASpecifiedField");
			Prototype.printOut("test running: RobotStepsToASpecifiedField");
			break;
		case "16":
			ReadTest("testcases/StepOnDangerZone");
			Prototype.printOut("test running: StepOnDangerZone");
			break;
		default:
			
			break;
		}

	}

	private static void newGame(String path) {
		arena = new Arena(path);
		gamers = arena.getGamers();
	}

	private static void SetOutput(String c, String path) {
		if (c.equals("-c")) {
			consoleOutput = true;
			fileOutput = false;
			System.out.println("Selected output: " + c + " as Console");
		} else if (c.equals("-f")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = false;
			System.out.println("Selected output: " + c + " as File");
		} else if (c.equals("-b")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = true;
			System.out.println("Selected output: " + c + " as Both");
		}
	}

	// vivi
	private static void ReadTest(String path) {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				decodeCommand(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void PutPatch(String id, String type) {
		for (Robi r : gamers) {
			if (r.id.equals(id)) {
				if (type.equals("-p")) {
					r.putPutty();
				} else if (type.equals("-o")) {
					r.putOil();
				}
				if ("SafeZone".equals(r.getField().getClass().getName())) {
					((SafeZone) r.getField()).addPatchesNow();
				}
				break;
			}
		}
	}

	private static void SetSpeed(String id, String x, String y) {
		/*
		 * 
		 * SetSpeed <Robot azonos�t�> <sebess�g: x> <sebess�g: y> Le�r�s: Robot
		 * sebess�g�nek megv�ltoztat�sa Opci�k: <Robot azonos�t�>: A robot
		 * azonos�t�ja <sebess�g: x>: Sebess�gvektor x koordin�t�ja (eg�sz sz�m)
		 * <sebess�g: y>: Sebess�gvektor y koordin�t�ja (eg�sz sz�m) P�lda:
		 * SetSpeed R00 [2, 1]
		 */
		if (!gamers.isEmpty()) {
			for (Robi r : gamers) {
				if (r.id.equals(id)) {
					r.setSpeed(new CoordVector(Integer.parseInt(x), Integer
							.parseInt(y)));
				}
			}
		}
	}

	private static void Step() {
		arena.tick();
		Prototype.drawMap(arena);
	}

	private static void Step(String id) {
		for (Robi r : gamers) {
			if (!r.id.equals(id)) {
				r.setSpeed(new CoordVector());
			}
		}

		arena.tick();
		Prototype.drawMap(arena);
	}

	private static void Step(String id, String fieldID) {
		for (Robi r : gamers) {
			if (r.id.equals(id)) {
				Field f = arena.getFieldById(fieldID);
				CoordVector speed = r.getField().getCoord()
						.getDiff(f.getCoord());
				r.setSpeed(speed);
			} else {
				r.setSpeed(new CoordVector());
			}
		}

		arena.tick();
		Prototype.drawMap(arena);
	}

	private static void Collide(String r1ID, String r2ID, String type) {
		/*
		 * Collide <Robot azonos�t�> <Robot azonos�t�> <�tk�z�s t�pusa> Le�r�s:
		 * K�t robot �tk�ztet�se el�re megadott �tk�z�si kimenettel Opci�k:
		 * <Robot azonos�t�>: Az �tk�zni k�v�nt robotok azonost�ja <�tk�z�s
		 * t�pusa>: Az �tk�z�s kimenetel�nek azonos�t�ja, robotok sebess�g�nek
		 * meghat�roz�sa (0 :azonos sebess�ggel �tk�znek, 1 :els� robot
		 * gyorsabb, 2 : m�sodik robot gyorsabb) P�lda: Collide R00 R01 -2
		 */
		boolean r1 = false;
		boolean r2 = false;

		Robi R1 = null;
		Robi R2 = null;
		if (!gamers.isEmpty()) {
			for (Robi r : gamers) {
				if (r.id.equals(r1ID)) {
					R1 = r;
					r1 = true;
				}
				if (r.id.equals(r2ID)) {
					R2 = r;
					r2 = true;
				}
			}
		}
		if (r1 && r2) {
			Field f = arena.getFieldById("s0");
			switch (type) {
			case "-0":
				CoordVector c = R1.getField().getCoord()
						.getDiff(R2.getField().getCoord());
				CoordVector newSpeed = new CoordVector(c.getX(), c.getY());
				R2.setSpeed(newSpeed);
				R1.setSpeed(newSpeed);

				// Robotokat lel�ptetj�k az eredeti hely�kr�l, r�l�ptetj�k pl az
				// S0-ra �s a field-j�ket is s0-ra �ll�tjuk.
				R1.getField().steppedOffYou(R1);
				f.steppedOnYou(R1);
				R2.getField().steppedOffYou(R2);
				f.steppedOnYou(R2);
				R1.setField(f);
				R2.setField(f);
				arena.tick();
				break;
			case "-1":
				R2.setSpeed(new CoordVector());
				R1.setSpeed(R2.getField().getCoord());

				R1.getField().steppedOffYou(R1);
				f.steppedOnYou(R1);
				R2.getField().steppedOffYou(R2);
				f.steppedOnYou(R2);
				R1.setField(f);
				R2.setField(f);
				arena.tick();
				break;
			case "-2":
				R1.setSpeed(new CoordVector());
				R2.setSpeed(R1.getField().getCoord());

				R1.getField().steppedOffYou(R1);
				f.steppedOnYou(R1);
				R2.getField().steppedOffYou(R2);
				f.steppedOnYou(R2);
				R1.setField(f);
				R2.setField(f);
				arena.tick();
				break;
			default:
				break;
			}
		}
		Prototype.drawMap(arena);

	}

	/**
	 * Visualize the map to the conlose created in the parameter.
	 * 
	 * @param arena
	 *            - the method reads the built map from it
	 */
	private static String genSpaces(int count) {
		String s = "";
		for (int i = 0; i < count; i++) {
			s += " ";
		}
		return s;
	}

	private static String writeElement(String id) {
		// 7 hosszu
		String newString = "";
		int cnt = id.length();
		int c = 7 - cnt;
		if (c % 2 == 0) {
			newString += genSpaces(c / 2);
			newString += id;
			newString += genSpaces(c / 2);
		} else {
			newString += genSpaces(c / 2);
			newString += id;
			newString += genSpaces(c / 2);
			newString += " ";
		}
		return newString;
	}

	public static void drawMap(Arena arena) {
		ArrayList<String> mapToDraw = new ArrayList<String>();

		int rowSize = arena.getDim().getX();
		String idToDraw = "";
		String patchToDraw = "";
		String coordToDraw = "";
		String separateLine = "";

		ArrayList<Field> fields = arena.getFields();
		for (Field field : fields) {
			idToDraw += "|";
			idToDraw += writeElement(field.id);

			patchToDraw += "|";
			if (field.robots.size() > 0 || field.patches.size() > 0) {
				String sn = "";
				boolean hasRobot = false;
				for (Robot r : field.robots) {
					sn += r.id;
					hasRobot = true;
					if (field.robots.size() > 1) {
						sn += ",";
					}
				}/*
				 * if (field.robots.size() > 0) {
				 * sn+=field.robots.get(field.robots.size() - 1).id; hasRobot =
				 * true; }
				 */
				// patchToDraw +=
				// writeElement(field.robots.get(field.robots.size() - 1).id);
				if (field.patches.size() > 0) {
					if (hasRobot) {
						sn += ",";
					}
					sn += field.patches.get(field.patches.size() - 1).id;

				}
				patchToDraw += writeElement(sn);

			} else {
				patchToDraw += writeElement("-");
			}
			// Negat�v koordin�t�kra is felk�sz�tve
			int x = field.coord.getX();
			int y = field.coord.getY();
			if (x < 0 && y < 0) {
				coordToDraw += "|";
				coordToDraw += writeElement("[" + field.coord.getX() + ","
						+ field.coord.getY() + "]");
			} else if (x < 0 || y < 0) {
				coordToDraw += "|";
				coordToDraw += writeElement("[" + field.coord.getX() + ","
						+ field.coord.getY() + "]");
			} else {
				coordToDraw += "|";
				coordToDraw += writeElement("[" + field.coord.getX() + ","
						+ field.coord.getY() + "]");
			}

			rowSize--;

			if (rowSize <= 0) {
				idToDraw += "|";
				coordToDraw += "|";
				patchToDraw += "|";

				mapToDraw.add(idToDraw);
				mapToDraw.add(patchToDraw);
				mapToDraw.add(coordToDraw);

				idToDraw = "";
				coordToDraw = "";
				patchToDraw = "";

				rowSize = arena.getDim().getX();
			}
		}
		separateLine += "+";

		for (int i = 0; i < arena.getDim().getX() * 8 - 1; i++) {
			separateLine += "-";
		}
		separateLine += "+";

		printOut(separateLine);
		for (int i = 0; i < arena.getDim().getY(); i++) {
			printOut(mapToDraw.get(mapToDraw.size() - 3));
			printOut(mapToDraw.get(mapToDraw.size() - 2));
			printOut(mapToDraw.get(mapToDraw.size() - 1));
			mapToDraw.remove(mapToDraw.size() - 3);
			mapToDraw.remove(mapToDraw.size() - 2);
			mapToDraw.remove(mapToDraw.size() - 1);
			printOut(separateLine);
		}
	}

	// id: Field ID-ja, amelyen van Oil �s Putty is.
	private static void Timeout(String id) {
		StringBuilder text = new StringBuilder("Timeout  - ");

		Field f = arena.getFieldById(id);
		boolean isOil = false;
		boolean isPutty = false;
		// Robot r = gamers.get(0);
		int i = 0;

		while (!(isOil || isPutty)) {
			Patch temp = f.patches.get(i);
			String className = temp.getClass().getSimpleName();
			// Oil timeout
			if ((("Oil").equals(className))) {
				((Oil) temp).tick();
				((Oil) temp).tick();
				((Oil) temp).tick();
				((Oil) temp).tick();
				((Oil) temp).tick();
				arena.tick();
				text.append(f.id + " " + temp.id);
				isOil = true;
				// Putty timeout
			} else if ((("Putty").equals(className))) {
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				arena.tick();
				text.append(f.id + " " + temp.id);
				isPutty = true;
			} else
				++i;
		}
		Prototype.drawMap(arena);
	}

	private static void Terminate() {
		StringBuilder text = new StringBuilder("Terminatd  - ");
		Robi winner = gamers.get(0);
		for (Robi r : gamers) {
			text.append(r.id + " Robot with " + r.getPoints() + ", ");
			if (r.getPoints() > winner.getPoints()) {
				winner = r;
			}
		}

		text.append(" Winner Robot is: " + winner.id);

		printOut(text.toString());
	}

	public static void printOut(String text) {
		if (consoleOutput) {
			System.out.println(text);
		}
		if (fileOutput) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(new File(filePath), true);
				bw = new BufferedWriter(fw);
				bw.write(text);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fw != null)
						fw.close();
					if (bw != null)
						bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
