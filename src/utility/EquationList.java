package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EquationList {
	
	private static Stack<String> _equations = new Stack<String>();
	private static List<String> _questionLog = new ArrayList<String>();
	private static List<Boolean> _answerLog = new ArrayList<Boolean>();
	private static boolean _hardMode = false;
	
	/**
	 * Generates a question list with answers from 1-99
	 */
	public static void generateHard(int length) {
		
		_hardMode = false;
		
		// Clear to ensure _numbers & logs don't have any pre-existing members
		_equations.clear();
		_answerLog.clear();
		_questionLog.clear();
		
		for (int i = 0; i < length; i++) {
			
		}
		
	}

}
