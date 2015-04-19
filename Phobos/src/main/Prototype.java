package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class Prototype {

	private Arena arena;
	private ArrayList<Robi> gamers;

	private boolean consoleOutput = true;
	private boolean fileOutput = false;

	private String filePath;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void decodeCommand(String line) {
		String[] command = line.split(" ");
		switch (command[0]) {
		case "NewGame":
			newGame(command[1]);
			break;
		case "SetOutput":
			SetOutput(command[1], command[2]);
			break;
		case "ReadTest":
			ReadTest(command[0]);
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

	private void newGame(String path) {
		arena = new Arena(path);
		gamers = arena.getGamers();
	}

	private void SetOutput(String c, String path) {
		if (c.equals("c")) {
			consoleOutput = true;
			fileOutput = true;
			System.out.println("Selected output: " + c + " as Console");
		} else if (c.equals("f")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = false;
			System.out.println("Selected output: " + c + " as File");
		} else if (c.equals("b")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = true;
			System.out.println("Selected output: " + c + " as Both");
		}
	}

	// vivi
	private void ReadTest(String path) {

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

	private void PutPatch(String id, String type) {
		for(Robi r : gamers) {
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
	
	private void SetSpeed(String id, String x, String y) {
		/*
		 * 
		 * SetSpeed <Robot azonosító> <sebesség: x> <sebesség: y>
			Leírás: Robot sebességének megváltoztatása
			Opciók:
				<Robot azonosító>: A robot azonosítója
				<sebesség: x>: Sebességvektor x koordinátája (egész szám)
				<sebesség: y>: Sebességvektor y koordinátája (egész szám)
			Példa: SetSpeed R00 [2, 1]*/
		if(!gamers.isEmpty()){
			for(Robi r : gamers){
				if(r.id==id){
					r.speed = new CoordVector(Integer.parseInt(x), Integer.parseInt(y));
				}
			}
		}
	}

	private void Step(String id) {
		for(Robi r : gamers) {
			if (!r.id.equals(id)) {
				r.setSpeed(new CoordVector());
			}
		}
		
		arena.tick();
	}

	private void Step(String id, String fieldID) {
		for(Robi r : gamers) {
			if (r.id.equals(id)) {
				Field f = arena.getFieldById(fieldID);
//				CoordVector speed = f.getCoord() - r.getField().getCoord();
			}
		}
	}

	private void Collide(String r0ID, String r1ID, String type) {

	}

	// vivi
	private void Timeout(String id) {

	}

	private void Terminate() {

	}
}
