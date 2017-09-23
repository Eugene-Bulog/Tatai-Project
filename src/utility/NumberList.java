package utility;

import java.util.LinkedHashMap;
import java.util.Stack;

public class NumberList {
	
	private static Stack<Integer> _numbers = new Stack<Integer>();
	private static LinkedHashMap<Integer,Boolean> _questionLog = new LinkedHashMap<Integer,Boolean>();
	
	/**
	 * Method for returning the next number from the question pool
	 * @return the next number
	 */
	public static int getNumber() {
		return _numbers.pop();
	}
	
	/**
	 * Checks if there are any more questions remaining
	 * @return true if no more questions to ask, false otherwise
	 */
	public static boolean noQuestions() {
		if (_numbers.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Generates a 10-number question pool using numbers from 1-9
	 */
	public static void generateEasy() {
		
		// Clear to ensure _numbers doesn't have any pre-existing members
		_numbers.clear();
		
		// Pushes a random number from 1-9 into the stack
		for (int i = 0; i < 10; i++) {
			_numbers.push(new java.util.Random().nextInt(9) + 1);
		}
		
	}
	
	/**
	 * Generates a 10-number question pool using numbers from 1-99
	 */
	public static void generateHard() {
		
		// Clear to ensure _numbers doesn't have any pre-existing members
		_numbers.clear();
		
		// Pushes a random number from 1-99 into the stack
		for (int i = 0; i < 10; i++) {
			_numbers.push(new java.util.Random().nextInt(99) + 1);
		}
	}
	
	/**
	 * Logs the answer to a question
	 * @param question: the number being asked by the question
	 * @param correct: whether the answer is true or not
	 */
	public static void logAnswer(int question, boolean correct) {
		_questionLog.put(question, correct);
	}
	
}
