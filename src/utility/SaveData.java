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
	
	/**
	 * Singleton getter & loader for UserData
	 * @return
	 */
	public static UserData getHighScores() {
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
		
		
		return _instance;
	}
	
	
	/**
	 * Saves the current user data to a ser file at current directory/UserData.ser
	 */
	public static void save() {
		
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
	
	@SuppressWarnings("serial")
	private static class UserData implements Serializable{
		/**
		 * The inner class that contains the save data.
		 */
		private static HashMap<String,Integer[]> _Data;
	}
	
}



