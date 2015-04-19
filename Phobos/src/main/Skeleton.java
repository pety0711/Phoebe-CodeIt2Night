///**
// * 
// */
//package main;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class Skeleton.
// *
// * @author Dávid & Ács
// */
//public class Skeleton {
//	
//	/** The selected usecase. */
//	public static int selectedUsecase = 1;
////	StackTraceElement[] ste = Thread.currentThread().getStackTrace();
////	for (StackTraceElement s : ste) {
////		System.out.println(s.getClassName() + " " + s.getMethodName());
////	}
////	System.out.println(ste.length);
//	
//	/** The use case options. */
//static String[] useCaseOptions = {
//
//			"Close tester",
//			"New game",
//			"Put putty",
//			"Put oil",
//			"Step on a putty",
//			"Step on an oil",
//			"Step on a safezone",
//			"Step on a dangerzone",
//			"Collision",
//			"Finish game"
//	};
//
//    
//
//	/** The required inputs int. */
//	static int[] requiredInputsInt = 
//		{0,1,2,3,4,5,6,7,8,9};
//	
//	/** The options type. */
//	static String[] optionsType = {
//			"Program",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case",
//			"Use-Case"
//	};
//	
//	
//	/**
//	 * The Enum UseCaseType.
//	 */
//	public static enum UseCaseType {
//    	
//	    /** The New_game. */
//	    New_game,
///** The Put_putty. */
//Put_putty,
///** The Put_oil. */
//Put_oil,
///** The Step_on_a_putty. */
//Step_on_a_putty,
//    	
//	    /** The Step_on_an_oil. */
//	    Step_on_an_oil,
///** The Step_on_a_safezone. */
//Step_on_a_safezone,
///** The Step_on_a_dangerzone. */
//Step_on_a_dangerzone,
//    	
//	    /** The Collision. */
//	    Collision,
///** The Finish_game. */
//Finish_game,
///** The Close_tester. */
//Close_tester
//    }
//    
//    /** The current use case. */
//    public static UseCaseType currentUseCase = UseCaseType.New_game;
//    
//    /**
//     * Gets the use case options.
//     *
//     * @param i the i
//     * @return the use case options
//     */
//    private static String getUseCaseOptions(int i){
//    	return useCaseOptions[Math.max(i, 0)];
//    }
//    
//    /**
//     * Gets the use case string.
//     *
//     * @param us the us
//     * @return the use case string
//     */
//    private static String getUseCaseString(UseCaseType us){
//    	boolean run = true;
//    	int index = 0;
//    	String s = us.toString().replace('_', ' ');
//    	while ((index<useCaseOptions.length)&&run
//    			) {
//    		if(s.compareTo(useCaseOptions[index])==0)
//    			run=false;
//    		else
//    			index++;			
//		}
//    	return useCaseOptions[index];
//    }
//    
//    /**
//     * Gets the use case type.
//     *
//     * @param i the i
//     * @return the use case type
//     */
//    private static UseCaseType getUseCaseType(int i){
//    	return UseCaseType.valueOf(getUseCaseOptions(i).replace(' ', '_'));
//    }
//    
//	/**
//	 * The main method.
//	 *
//	 * @param args the arguments
//	 */
////	public static void main(String [ ] args)
////	{ 	
////		
////		boolean runMain = true;
////		printOutINFO("<init> CodeIt2Night Use-Case Tester - OK");
////		do {
////			insertSpace();
////			printOutINFO("Menu Items List");
////
////
////		     currentUseCase = getUseCaseType(printMenuItems()); //Bekérjük a felhasználótól a választandó Use-case számát
////		     System.out.println();
////		    printOutINFO("Selected Menu item:\t"+getUseCaseString(currentUseCase));
////		     System.out.println();
////		     switch(currentUseCase){
////				case Close_tester:
////					runMain = false;
////			    	 insertSpace();
////			    	 printOutINFO("<close> CodeIt2Night Use-Case Tester - OK");	
////					break;
////				case Collision:
////					Arena arenaCollision = new Arena();
////					arenaCollision.tick();
////					break;
////				case Finish_game:
////					Arena arenaFinish = new Arena();	
////					drawLine();
////					arenaFinish.finishGame();
////					drawLine();
////					break;
////				case New_game:
////					Arena arenaNew = new Arena();
////					drawLine();
////					arenaNew.tick();
////					break;
////				case Put_oil:
////					Arena arenaPutoil = new Arena();
////					arenaPutoil.tick();
////					break;
////				case Put_putty:
////					Arena arenaPutputty = new Arena();
////					arenaPutputty.tick();
////					break;
////				case Step_on_a_dangerzone:
////					Arena arenaDangerzone = new Arena();
////					arenaDangerzone.tick();
////					break;
////				case Step_on_a_safezone:
////					Arena arenaSafezone = new Arena();
////					arenaSafezone.tick();
////					break;
////				case Step_on_a_putty:
////					Arena arenaStepputty = new Arena();
////					arenaStepputty.tick();
////					break;
////				case Step_on_an_oil:
////					Arena arenaStepoil = new Arena();
////					arenaStepoil.tick();
////					break;
////				default:
////					break;
////		     }
////		} while (runMain);
////	}
//	
//	/**
//	 * Insert space.
//	 */
//	private static void insertSpace(){
//		for (int i = 0; i < 70; i++) {
//			System.out.print('_');
//		}
//		System.out.println("");
//		for (int i = 0; i < 70; i++) {
//			System.out.print('_');
//		}
//		System.out.println("");
//		System.out.println("");
//	}
//	
//	/**
//	 * Draw line.
//	 */
//	public static void drawLine(){
//		for (int i = 0; i < 70; i++) {
//			System.out.print("+");
//		}	
//		System.out.println();
//	}
//	//Kiiratások---------------------------------------------------------------------------------------------------------
//
//	/**
//	 * Prints the menu items.
//	 *
//	 * @return the char
//	 */
//	public static int printMenuItems(){
//		int result = -1;
//		
//		try {
//			result = requestUserInput(useCaseOptions, 
//					requiredInputsInt,optionsType);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
//	
//	/** The c functions. */
//	private static StackTraceElement[] cFunctions = Thread.currentThread().getStackTrace();
//	
//	/**
//	 * Gets the lc class name.
//	 *
//	 * @return the lc class name
//	 */
//	private static String getlcClassName(){
//		//c - Called
//		//lc-LastCalled
//		StackTraceElement lcElement = cFunctions[2];
//		String s = lcElement.getClassName().toString();
//		String[] lcSplittedClassName = s.split("\\."); //\\ muszáj anélkül nem vág a pontnál
//		String lcClassName = lcSplittedClassName[lcSplittedClassName.length-1];	
//		return lcClassName;
//	}
//	
//	/**
//	 * A függvénnyel megkapható az utoljára hívott osztály neve.
//	 *
//	 * @param o the o
//	 * @return the class name
//	 */
//	public static String getClassName(Object o){
//		String[] split  =  o.getClass().toString().split("\\.");
//		if(split.length>0)
//			return split[split.length-1];
//		else
//			return "";
//	}
//	
//	/**
//	 * A függvénnyel megkapható az utoljára hívott metódus neve. Ha ez inicializáló metódus volt
//	 * Változik a visszaadott érték <<create>> -re.
//	 *
//	 * @return String egy metódusnak a neve
//	 */
//	private static String getlcMethodName(){
//		//c - Called
//		//lc-LastCalled
//		StackTraceElement lcElement = cFunctions[2];
//		String lcMethodName = lcElement.getMethodName();
//		
//		if(lcMethodName=="<init>")
//			//return getlcClassName();
//			return "<<create>>";
//		return lcMethodName;
//	}
//	
//	/**
//	 * Visszatér a Stack mélységével.
//	 *
//	 * @return Stack mélysége
//	 */
//	private static int getCallDepth(){
//		return cFunctions.length;
//	}
//	
//	/**
//	 * A sorok behúzását végzi figyelembevéve a Stack mélységét.
//	 *
//	 * @return visszatér annyi tabulátorral, amennyivel beljebb kell lennie az adott hívásnak
//	 */
//	private static String pullLineIn(){
//		String tabs = new String(); 
//		for (int i = 0; i < getCallDepth()-5; i++) {
//			tabs += "\t";
//		}
//		return tabs;
//	}
//
//	/**
//	 * printLastCalledFunction(String id,String[] parameters)
//	 * A függvény kiírja, melyik függvényt hívtuk utoljára. {id}:<ClassName> - <functionName>({parameters}) formátumban.
//	 * Célszerû a paraméter stringbe vesszõket tenni a paraméterek elválasztására, ezt ez a függvény nem teszi meg! 
//	 *
//	 * @author  Akos Recse & David Sebok
//	 * @version 2.1
//	 * @param id - annak az objektumnak az azonosítója, amelyiken a függvényhívást végeztük.
//	 * @param parameters - a hívott függvénynek átadott paraméterek String[] tömb amiben érték párok vannak
//	 * elõl a név hátul a típusa
//	 * @since   2015-03-20
//	 */
//	public static void printLastCalledFunction(String id,String[] parameters){
//		cFunctions = Thread.currentThread().getStackTrace();
//		
//		//Append parameters
//		String appendedParameters = new String(); 
//		for (int i = 0; i < parameters.length; i++) {
//			if((i<parameters.length-1)&&(i>0)&&(i%2==0))
//				appendedParameters+=", ";
//			if(i%2==1){
//				appendedParameters += " : ";
//			}
//			appendedParameters += parameters[i];
//			
//		}
//		
//		printOutDEBUG(pullLineIn() + id +": " + getlcClassName()+ " - " + getlcMethodName() + "(" + appendedParameters + ")");
//	} 
//
//	/**
//	 * printLastCalledFunction(String id)
//	 * Ez a függvény a printLastCalledFunction(String id,String[] parameters) paraméter nélküli változata.
//	 * Paraméterként csak egy String id-t kap meg.
//	 *
//	 * @param id a hívó fél id
//	 */
//	public static void printLastCalledFunction(String id){
//		cFunctions = Thread.currentThread().getStackTrace();
//
//		printOutDEBUG(pullLineIn() + id +": " + getlcClassName()+ " - " + getlcMethodName() + "()");
//	} 
//	/*
//	//Esz logikailag jobb
//	/**
//	 * Prints the last called function.
//	 *
//	 * @param id the id
//	 * @param parameters the parameters
//	 *//*
//	public static void printLastCalledFunction(String id, HashMap<String,String> parameters){
//		String appendedParameters = new String("");
//
//		parameters.values();
//		parameters.keySet();
//		String[] types = (String[]) parameters.values().toArray();
//		String[] names = (String[]) parameters.keySet().toArray();
//		for(int i= 0;i<names.length;i++){
//			appendedParameters+=names[i]+" : "+types[i];
//			if(i<names.length-1)
//				appendedParameters+=", ";
//		}
//		
//		printOutDEBUG(pullLineIn() + id +": " + getlcClassName()+ " - " + getlcMethodName() + "(" + appendedParameters + ")");
//		
//	} */
//
//	/**
//	 * Prints the out info.
//	 *
//	 * @param s the s
//	 */
//	public static void printOutINFO(String s) {
//		System.out.println("INFO>\t" + s);
//	}
//	
//	/**
//	 * Prints the out debug.
//	 *
//	 * @param s the s
//	 */
//	public static void printOutDEBUG(String s) {
//		System.out.println("DEBUG>\t" + s);
//	}
//
//	/**
//	 * Request user input.
//	 *
//	 * @param description the description
//	 * @param requiredInputs the required inputs
//	 * @param optionsType the options type
//	 * @return the char
//	 * @throws Exception the exception
//	 */
//	public static int requestUserInput(String[] description, int[] requiredInputs, String[] optionsType) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println();
//		for(int i = 0; i < description.length; i++) {
//			System.out.println("INFO> "+optionsType[i]+":\t" +"Please press '"+  requiredInputs[i]+ "'" +" for "+ description[i] );
//		}
//		
//		System.out.println();
//		//TODO - csak ekkor várunk user inputot?
//		System.out.println("Please select a USE-CASE from the list above!\n");
//		System.out.println("HINT: only the last character counts.");
//		System.out.print("WAITING_FOR_USER_INPUT> ");
//		
//		String s = "";
//		
//		// ha talál egyezést, kilép, különben pedig addig kérünk tõle inputot, amíg nem ad jó választ.
//		int cnt = 10;
//		while (cnt > 0) {
//			try {
//				s = br.readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			if(!s.isEmpty()) {
//				char[] c = s.substring(s.length() - 1).toCharArray();
//				int input = Character.getNumericValue(c[0]);//Beparzoljuk a bemenetet.
//				for (int i = 0; i < requiredInputs.length; i++) {
//					if(i==input)
//						return input;
//				}
//			}
//			
//			//ha ide eljutottunk, nincs jó input
//			System.out.println("Please select a USE-CASE from the list above!");
//			System.out.print("WAITING_FOR_USER_INPUT> ");
//			cnt --;
//		}
//		//elvileg csak 0 lehet itt már...
//		if (cnt < 1) {
//			System.out.println("Error: User Input failed");
//			System.out.println("Description: You failed 10 times or more. The program now stops for your safety.");
//		}
//		
//		return -1;
//	}
//	//Kiiratások---------------------------------------------------------------------------------------------------------
//}
