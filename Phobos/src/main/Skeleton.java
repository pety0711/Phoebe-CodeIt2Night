/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Dávid & Ács
 *
 */
public class Skeleton {
	
	
	public static void main(String [ ] args)
	{
		 printOutINFO("Start Test!");
	     printOutDEBUG("Debug");
	     Robot robot = new Robot();
	     robot.putOil();
//	     char r = printMenuItems();
//	     System.out.print(r);
	     //New main

	}
	
	public static char printMenuItems(){
		String[] description = {"New game",
				"Put putty",
				"Put oil",
				"Stepping on a putty",
				"Stepping on an oil",
				"Step on a safezone",
				"Stepping on a dangerzone",
				"Collision",
				"Finish game"};
		
//		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
//		for (StackTraceElement s : ste) {
//			System.out.println(s.getClassName() + " " + s.getMethodName());
//		}
//		System.out.println(ste.length);

		char[] requiredInputs = {'1','2','3','4','5','6','7','8','9'};
		char result = '0';
		try {
			result = requestUserInput(description, requiredInputs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//Kiiratások---------------------------------------------------------------------------------------------------------

	/**
	 * A függvény kiírja, melyik függvényt hívtuk utoljára. {id}:<ClassName> - <functionName>({parameters}) formátumban. 
	 * @param id - annak az objektumnak az azonosítója, amelyiken a függvényhívást végeztük.
	 * @param parameters - a hívott függvénynek átadott paraméterek
	 * @author  Akos Recse
	 * @version 1.0
	 * @since   2015-03-20
	 */
	public static void printLastCalledFunction(String id,String parameters){
		
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement last = ste[2]; 
		//mindig a stack 2. eleme szükséges, mert LIFO.
		
		//A sor elején lévõ tabulátorok számát határozzuk meg, és tesszük bele egy stringbe, amit csak oda kell tenni
		//annak a sornak az elejére, ami kiírja a függvényt.
		String tabs = new String(); 
		for (int i = 0; i < ste.length-4; i++) {
			tabs += "\t";
		}
		
//		for (StackTraceElement s : ste) {
//			System.out.println(s.getClassName() + " " + s.getMethodName());
//		}
		
		printOutDEBUG(tabs + id +": " + last.getClassName()+ " - " + last.getMethodName() + "(" + parameters + ")");
		
	} 
	
	public static void printOutINFO(String s) {
		System.out.println("INFO>\t" + s);
	}
	
	public static void printOutDEBUG(String s) {
		System.out.println("DEBUG>\t" + s);
	}
	
	public static char requestUserInput(String[] description, char[] requiredInputs) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < description.length; i++) {
			System.out.println("INFO> Use-Case: " + description[i] + " - please press - '" + requiredInputs[i] + "'");
		}
		//TODO - csak ekkor várunk user inputot?
		System.out.println("Please select a USE-CASE from the list above!");
		System.out.print("WAITING_FOR_USER_INPUT> ");
		
		String s = "";
		
		// ha talál egyezést, kilép, különben pedig addig kérünk tõle inputot, amíg nem ad jó választ.
		int cnt = 10;
		while (cnt > 0) {
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!s.isEmpty()) {
				char[] c = s.substring(s.length() - 1).toCharArray();
				for (int i = 0; i < requiredInputs.length; i++) {
					if(Character.toUpperCase(requiredInputs[i]) == Character.toUpperCase(c[0]))
						return c[0];
				}
			}
			
			//ha ide eljutottunk, nincs jó input
			System.out.println("Please select a USE-CASE from the list above!");
			System.out.print("WAITING_FOR_USER_INPUT> ");
			cnt --;
		}
		//elvileg csak 0 lehet itt már...
		if (cnt < 1)
			throw new Exception("User Input failed");
		
		return '0';
	}
	//Kiiratások---------------------------------------------------------------------------------------------------------
}
