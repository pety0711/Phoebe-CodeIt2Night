package main;

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
		}
		else if (c.equals("f")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = false;
			System.out.println("Selected output: " + c + " as File");
		}
		else if (c.equals("b")) {
			fileOutput = true;
			filePath = path;
			consoleOutput = true;			
			System.out.println("Selected output: " + c + " as Both");
		}
	}
	
	private void ReadTest(String path) {
		
	}
	
	private void PutPatch(String id, String type) {
		
	}
	
	private void SetSpeed(String id, int x, int y) {
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
					r.speed = new CoordVector(x, y);
				}
			}
		}
	}
	
	private void Step(String id) {
		
	}
	
	private void Step(String id, String fieldID) {
		
	}
	
	private void Collide(String r0ID, String r1ID, String type) {
		
	}
	
	private void Timeout(String id) {
		
	}
	
	private void Terminate() {
		
	}
}
