package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EquationList {
	
	private static Stack<String> _equations = new Stack<String>();
	private static Stack<Integer> _answers = new Stack<Integer>();
	private static List<String> _questionLog = new ArrayList<String>();
	private static List<Integer> _answerLog = new ArrayList<Integer>();
	private static List<Boolean> _userAnswerLog = new ArrayList<Boolean>();
	private static boolean _hardMode = false;
	
	
	
	/**
	 * Generates an atomic equation
	 * @param max the maximum value that the equation can equal
	 * @return a string array, where the first member is the equation,
	 * and the second member is the answer
	 */
	public static String[] generatePart(int max) {
	
		String eq = "";
		int ans = 0;
		
		int a;
		int b;
		
		switch (new java.util.Random().nextInt(4)) {
		// +
		case 0:
			a = new java.util.Random().nextInt(max - 1) + 1;
			b = new java.util.Random().nextInt(max - a) + 1;
			eq = a + " + " + b;
			ans = a + b;
			break;
		// -
		case 1:
			a = new java.util.Random().nextInt(max - 1) + 2;
			b = new java.util.Random().nextInt(a - 1) + 1;
			eq = a + " - " + b;
			ans = a - b;
			break;
		// x
		case 2:
			a = new java.util.Random().nextInt((int)(max / 2)) + 1;
			b = new java.util.Random().nextInt(max / a) + 1;
			eq = a + " X " + b;
			ans = a * b;
			break;
		// /
		case 3:
			a = new java.util.Random().nextInt((int)(max / 2)) + 1;
			b = new java.util.Random().nextInt(max / a) + 1;
			eq = (a*b) + " / " + b;
			ans = a;
			break;
		}
		
		String[] returnVal = {eq,Integer.toString(ans)};
		return returnVal;
		
	}
	
	

}
