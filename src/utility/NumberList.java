package utility;

import java.util.Stack;

public class NumberList {
	
	private static Stack<Integer> _numbers = new Stack<Integer>();
	
	/**
	 * Method for returning the next number from the question pool
	 * @return the next number
	 */
	public static int getNumber() {
		return _numbers.pop();
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
	
}
