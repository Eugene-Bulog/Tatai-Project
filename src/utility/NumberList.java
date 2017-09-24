package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumberList {
	
	private static Stack<Integer> _numbers = new Stack<Integer>();
	private static List<Integer> _questionLog = new ArrayList<Integer>();
	private static List<Boolean> _answerLog = new ArrayList<Boolean>();
	private static boolean _hardMode = false;
	
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
		
		_hardMode = false;
		
		// Clear to ensure _numbers & logs don't have any pre-existing members
		_numbers.clear();
		_answerLog.clear();
		_questionLog.clear();
		
		// Pushes a random number from 1-9 into the stack
		for (int i = 0; i < 10; i++) {
			_numbers.push(new java.util.Random().nextInt(9) + 1);
		}
	}
	
	/**
	 * Generates a 10-number question pool using numbers from 1-99
	 */
	public static void generateHard() {
		
		_hardMode = true;
		
		// Clear to ensure _numbers doesn't have any pre-existing members
		_numbers.clear();
		_answerLog.clear();
		_questionLog.clear();
		
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
		_questionLog.add(question);
		_answerLog.add(correct);
	}
	
	/**
	 * Gets the number asked at the specified question
	 * @param pos: the index of the question asked
	 * @return: the number asked at that index
	 */
	public static int getNumberAt(int pos) {
		return _questionLog.get(pos);
	}
	
	/**
	 * Gets the users answer to the specified question
	 * @param pos: the index of the question asked
	 * @return: the user's answer to the question
	 */
	public static String getAnswerAt(int pos) {
		if (_answerLog.get(pos)) {
			return "Correct";
		}
		else {
			return "Incorrect";
		}
	}
	
	/**
	 * Gets the score of this session
	 * @return: a count of the number of questions answered correctly
	 */
	public static int getSessionScore() {
		int count = 0;
		for (boolean answer: _answerLog) {
			if (answer) {
				count++;
			}
		}
		
		return count;
	}
	
	
	/**
	 * Getter for whether the application is in hard mode or not
	 * Returns true if in hard mode, false otherwise
	 */
	public static boolean isHard() {
		return _hardMode;
	}
}
