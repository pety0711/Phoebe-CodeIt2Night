/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author D�vid & �cs
 *
 */
public class Skeleton {
	
//	StackTraceElement[] ste = Thread.currentThread().getStackTrace();
//	for (StackTraceElement s : ste) {
//		System.out.println(s.getClassName() + " " + s.getMethodName());
//	}
//	System.out.println(ste.length);
	static String[] useCaseOptions = {"New game",
			"Put putty",
			"Put oil",
			"Stepping on a putty",
			"Stepping on an oil",
			"Step on a safezone",
			"Stepping on a dangerzone",
			"Collision",
			"Finish game"};
	
	static char[] requiredInputs = {'1','2','3','4','5','6','7','8','9'};
	public static void main(String [ ] args)
	{
		boolean runMain = true;
		do {
			try {
				Runtime.getRuntime().exec("cls");
			} catch (IOException e) {
				for (int i = 0; i < 50; i++) {
					System.out.println();
				}
			}
			printOutINFO("Start Use-case Test!");
		     printOutDEBUG("Debug");
		     //
		     String[] emptyStrArray = new String[]{""};
		     Arena a = new Arena();
		     
		     char selectedUsecase = printMenuItems(); //Bek�rj�k a felhaszn�l�t�l a v�lasztand� Use-case sz�m�t
		     
		     System.out.println(selectedUsecase);
		     switch(selectedUsecase){
			     case '0': 
			    	 
			    	 break;
			     case '1':
			    	 printOutINFO("Selected Use-case:\t"+useCaseOptions[2]);
			    	 printLastCalledFunction("a", emptyStrArray);
			    	 break;
			     case '2': //doWork
			    	 break;
			     case '3': //doWork
			    	 break;
			     case '4': //doWork
			    	 break;
			     case '5': //doWork
			    	 break;
			     case '6': //doWork
			    	 break;
			     case '7': //doWork
			    	 break;
			     case '8': //doWork
			    	 break;
			     case '9': //doWork
			    	 break;
				 default: 
					 break;
		     }
		     
		} while (runMain);
		
		//Start 
		 
	}
	
	
	//Kiirat�sok---------------------------------------------------------------------------------------------------------

	/**
	 * Prints the menu items.
	 *
	 * @return the char
	 */
	public static char printMenuItems(){
		
		
		char result = 'o';
		
		try {
			result = requestUserInput(useCaseOptions, requiredInputs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	


	/**
	 * printLastCalledFunction
	 * A f�ggv�ny ki�rja, melyik f�ggv�nyt h�vtuk utolj�ra. {id}:<ClassName> - <functionName>({parameters}) form�tumban.
	 * C�lszer� a param�ter stringbe vessz�ket tenni a param�terek elv�laszt�s�ra, ezt ez a f�ggv�ny nem teszi meg! 
	 * @param id - annak az objektumnak az azonos�t�ja, amelyiken a f�ggv�nyh�v�st v�gezt�k.
	 * @param parameters - a h�vott f�ggv�nynek �tadott param�terek
	 * @author  Akos Recse
	 * @version 1.0
	 * @since   2015-03-20
	 */
	public static void printLastCalledFunction(String id,String[] parameters){
		
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement last = ste[2]; 
		//mindig a stack 2. eleme sz�ks�ges, mert LIFO.
		
		//A sor elej�n l�v� tabul�torok sz�m�t hat�rozzuk meg, �s tessz�k bele egy stringbe, amit csak oda kell tenni
		//annak a sornak az elej�re, ami ki�rja a f�ggv�nyt.
		String tabs = new String(); 
		for (int i = 0; i < ste.length-4; i++) {
			tabs += "\t";
		}
		
		//Append parameters
		String appendedParameters = new String(); 
		for (int i = 0; i < parameters.length; i++) {
			appendedParameters += parameters[i];
			if(i < parameters.length - 1){
				appendedParameters += ", ";
			}
		}
		
		
//		for (StackTraceElement s : ste) {
//			System.out.println(s.getClassName() + " " + s.getMethodName());
//		}
		
		printOutDEBUG(tabs + id +": " + last.getClassName()+ " - " + last.getMethodName() + "(" + appendedParameters + ")");
		
	} 
	
	/**
	 * Prints the out info.
	 *
	 * @param s the s
	 */
	public static void printOutINFO(String s) {
		System.out.println("INFO>\t" + s);
	}
	
	public static void printOutDEBUG(String s) {
		System.out.println("DEBUG>\t" + s);
	}
	
	/**
	 * Request user input.
	 *
	 * @param description the description
	 * @param requiredInputs the required inputs
	 * @return the char
	 * @throws Exception the exception
	 */
	public static char requestUserInput(String[] description, char[] requiredInputs) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		for(int i = 0; i < description.length; i++) {
			System.out.println("INFO> Use-Case:\t" +"Please press '"+  requiredInputs[i]+ "'" +" for "+ description[i] );
		}
		System.out.println("INFO> Exit:\t" +"Please press '0'" +" for "+ "Close test program" );
		
		System.out.println();
		//TODO - csak ekkor v�runk user inputot?
		System.out.println("Please select a USE-CASE from the list above!");
		System.out.print("WAITING_FOR_USER_INPUT> ");
		
		String s = "";
		
		// ha tal�l egyez�st, kil�p, k�l�nben pedig addig k�r�nk t�le inputot, am�g nem ad j� v�laszt.
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
			
			//ha ide eljutottunk, nincs j� input
			System.out.println("Please select a USE-CASE from the list above!");
			System.out.print("WAITING_FOR_USER_INPUT> ");
			cnt --;
		}
		//elvileg csak 0 lehet itt m�r...
		if (cnt < 1)
			throw new Exception("User Input failed");
		
		return '0';
	}
	//Kiirat�sok---------------------------------------------------------------------------------------------------------
}
