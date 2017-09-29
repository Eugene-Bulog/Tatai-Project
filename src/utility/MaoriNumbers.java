package utility;

import java.util.*;


public class MaoriNumbers {
	
	// A String array where the index of the array corresponds to that number's pronunciation in Maori.
	private static final String[] _maoriNumbers = {"", "tahi", "rua", "toru", "whaa", "rima", "ono",
															"whitu", "waru", "iwa", "tekau"};                  
	
	
	
	
	/**
	 * Method that returns the correct Maori pronunciation of an integer between 1-99, for use 
	 * in comparing the result with the user's pronunciation attempt.
	 * @param number The number of which to retrieve the maori pronunciation.
	 * @return A string representing the Maori pronunciation of the number between 1-99. 
	 * If number is out of range, then returns null.
	 */
	public static String getMaoriPronunciation(int number) {
		
		if ((number < 1) || (number > 99)) {
			
			return null;
					
		} else {
			// Get the number of digits in the number (can only be 1 or 2)
			int numDigits = String.valueOf(number).length();
			
			switch (numDigits) {
				
				case 1: // Number is only one digit

					
					return _maoriNumbers[number]; 
					
				case 2: // Number is two digits 
					
					if (number==10) {
						return _maoriNumbers[number];
					}
					
					// Get each digit
					int firstDigit = Character.getNumericValue((String.valueOf(number).toCharArray()[0]));
					int secondDigit = Character.getNumericValue((String.valueOf(number).toCharArray()[1]));

					
					if (number%10 ==0) {
						
						return _maoriNumbers[firstDigit] + " tekau";
						
					} else if (number>10 && number<20 ){
						
						return "tekau maa " + _maoriNumbers[secondDigit];
						
					} else {
						
						return _maoriNumbers[firstDigit] + " tekau maa " + _maoriNumbers[secondDigit];
					}					
					
				default:
					
					return null;
			}
		}
	}
	
	
}
