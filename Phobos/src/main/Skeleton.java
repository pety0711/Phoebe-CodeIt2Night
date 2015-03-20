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
	
	public static void main(String [ ] args)
	{
	     PrintOutINFO("Start Test!");
	     PrintOutDEBUG("Debug");
	     //New main
	}
	
	public static void PrintOutINFO(String s) {
		System.out.println("INFO> " + s + ";");
	}
	
	public static void PrintOutDEBUG(String s) {
		System.out.println("DEBUG> " + s + ";");
	}
	
	public static char RequestUserInput(String[] description, char[] requiredInputs) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < description.length; i++) {
			System.out.println("INFO> Use-Case: " + description[i] + " - please press - '" + requiredInputs[i] + "'");
		}
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
}
