package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class SaveData{
	/**
	 * This class is used for loading and saving user scores and data, primarily via serialization.
	 */
	
	private static UserData _instance = null;
	private static final Integer[] NEW_USER_VALS = {1,0,0,0,0,0};
	
	/**
	 * Initializes userdata by either loading or creating a new instance
	 */
	public static void initHighScores() {
		// _instance already exists, returns it
		if (_instance == null) {
			// If the ser file with userData exists, loads it as an object
			if (new File(SaveData.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
					+ "UserData.ser").exists()) {
				
				// Tries to load in the UserData
				try {
					
					FileInputStream fIn = new FileInputStream(SaveData.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
					+ "UserData.ser");
					
					ObjectInputStream oIn = new ObjectInputStream(fIn);
					_instance = (UserData)oIn.readObject();
					oIn.close();
					fIn.close();
				}
				catch (Exception e) {
					// If a problem is encountered, creates a new instance
					e.printStackTrace();
					_instance = new UserData();
				}
				
			}
			else {
				// If the save location isn't found, creates a new instance
				_instance = new UserData();
			}
		}
		
		
		
	}
	
	
	/**
	 * Saves the current user data to a ser file at current directory/UserData.ser
	 */
	private static void save() {
		// Attempts to serialize _instance
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(SaveData.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
						+ "UserData.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(_instance);
	         out.close();
	         fileOut.close();
	      }catch(Exception i) {
	    	  // empty
	      }
	}
	
	
	/**
	 * Call once the user has logged in. Checks if their name exists in save data,
	 * and if it doesn't, creates the user
	 */
	public static void login() {
		if (!_instance._data.containsKey(main.App.getName())) {
			_instance._data.put(main.App.getName(), NEW_USER_VALS);
		}
	}
	
	/**
	 * Logs the specified user score. Handles saving high scores 7 checking if skill level should be advanced
	 * @param scorePercent the percentage score of this session
	 * @return a 2 member Boolean array, where the first member is true if this is a new high score for this
	 * skill level, and the second member is true if the user has progressed to a new skill level
	 */
	public static boolean[] logScore(int score, int numberOfQuestions) {
		boolean[] returnBool = {false,false};
		
		// Gets current values
		Integer[] currentData = _instance._data.get(main.App.getName());
		
		// Adds score and number of questions for this session
		currentData[1] += score;
		currentData[2] += numberOfQuestions;
		
		// gets a rounded integer percentage of this sessions score
		int sessionPercent = Math.round((float)score / (float) numberOfQuestions * 100);
		int overallPercent = Math.round((float)currentData[1] / (float) currentData[2] * 100);
		
		switch (currentData[0]) {
		case 1:
			// If a highscore, sets first return as true, and updates highscore for this level
			if (sessionPercent > currentData[3]) {
				returnBool[0] = true;
				currentData[3] = sessionPercent;
			}
			break;
		case 2:
			// If a highscore, sets first return as true, and updates highscore for this level
			if (sessionPercent > currentData[4]) {
				returnBool[0] = true;
				currentData[4] = sessionPercent;
			}
			break;
		case 3:
			// If a highscore, sets first return as true, and updates highscore for this level
			if (sessionPercent > currentData[5]) {
				returnBool[0] = true;
				currentData[5] = sessionPercent;
			}
			break;
		}
		
		// If overall percent for this level >= 80, progress to next level and reset overalls
		if (overallPercent >= 80 && currentData[0] < 3) {
			currentData[0]++;
			currentData[1] = 0;
			currentData[2] = 0;
			returnBool[1] = true;
		}
		
		
		// Save the edited data to the map in UserData, and save UserData
		_instance._data.put(main.App.getName(),currentData);
		save();
		return returnBool;
	}
	
	
	@SuppressWarnings("serial")
	private static class UserData implements Serializable{
		/**
		 * The inner class that contains the save data.
		 */
		
		// String: User
		// Integer[0]: skill level (1-3)
		// Integer[1]: correct qs for this level
		// Integer[2]: number of qs answered for this level
		// Integer[3]: high score easy
		// Integer[4]: high score mid
		// Integer[5]: high score hard
		HashMap<String,Integer[]> _data = new HashMap<String,Integer[]>();
		
	}
	
}



