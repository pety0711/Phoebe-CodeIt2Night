package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
		String[] command = line.split(" ");
		switch (command[0]) {
		case "NewGame":
			newGame(command[1]);
			break;
		case "SetOutput":
			SetOutput(command[1], command[2]);
			break;
		case "ReadTest":
			ReadTest(command[1]);
			break;
		case "PutPatch":
			PutPatch(command[1], command[2]);
			break;
		case "SetSpeed":
			SetSpeed(command[1], command[2], command[3]);
			break;
		case "Step":
			if (command.length <= 2)
				Step(command[1]);
			else
				Step(command[1], command[2]);
			break;
		case "Collide":
			Collide(command[1], command[2], command[3]);
			break;
		case "Timeout":
			Timeout(command[1]);
			break;
		case "Terminate":
			Terminate();
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
			fileOutput = true;
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
				if (r.id == id) {
					r.setSpeed(new CoordVector(Integer.parseInt(x), Integer
							.parseInt(y)));
				}
			}
		}
	}

	private static void Step(String id) {
		for (Robi r : gamers) {
			if (!r.id.equals(id)) {
				r.setSpeed(new CoordVector());
			}
		}

		arena.tick();
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
	}

	private static void Collide(String r0ID, String r1ID, String type) {
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
				if (r.id == r0ID) {
					R1 = r;
					r1 = true;
				}
				if (r.id == r0ID) {
					R2 = r;
					r2 = true;
				}
			}
		}
		if (r1 && r2) {
			switch (type) {
			case "0":
				CoordVector c = R1.getField().getCoord()
						.getDiff(R2.getField().getCoord());
				CoordVector newSpeed = new CoordVector(c.getX(), c.getY());
				R2.setSpeed(newSpeed);
				R1.setSpeed(newSpeed);
				arena.tick();
				break;
			case "1":
				R2.setSpeed(new CoordVector());
				R1.setSpeed(R2.getField().getCoord());
				arena.tick();
				break;
			case "2":
				R1.setSpeed(new CoordVector());
				R2.setSpeed(R1.getField().getCoord());
				arena.tick();
				break;
			default:
				break;
			}
		}
	}

	public static void drawMap(Arena arena) {
		List<String> mapToDraw = new ArrayList<String>();

		int rowSize = arena.getDim().getX();
		String rowToDraw = "";

		for (Field field : arena.getFields()) {
			rowToDraw += field.id;
			rowToDraw += "  ";
			rowSize--;
			if (rowSize <= 0) {
				mapToDraw.add(rowToDraw);
				rowSize = arena.getDim().getX();
				rowToDraw = "";
			}
		}
		for (int i = 0; i <= mapToDraw.size() + 1; i++) {
			printOut(mapToDraw.get(mapToDraw.size() - 1));
			mapToDraw.remove(mapToDraw.size() - 1);
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
				text.append(f.id + " " + temp.id);
				isOil = true;
				// Putty timeout
			} else if ((("Putty").equals(className))) {
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				((Putty) temp).decreaseLife();
				text.append(f.id + " " + temp.id);
				isPutty = true;
			} else
				++i;
		}
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
			FileWriter fw;
			try {
				fw = new FileWriter(new File(filePath));
				fw.append(text);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
