package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BashProcess {

	private static final  BashProcess INSTANCE = new BashProcess();
	private ProcessBuilder pb, pb2;
	private static String _userAttempt;
	
	
	private BashProcess() {
		
	}
	
	public static BashProcess getInstance() {
		
		return INSTANCE;
	}
	
	/**
	 * This method runs record commands in Bash (using the supplied GoSpeech script file),
	 * and then makes a call to getUserAttempt to return the user's spoken words.
	 * @return A String representing the user's MaoriPronunciation.
	 */
	public String recordAndRetrieve() {
			
			Process recordingProcess = null;
			pb = new ProcessBuilder("bash", "-c", "./GoSpeech");
			pb.directory(new File("HTK/MaoriNumbers"));
			try {
				recordingProcess = pb.start();
				recordingProcess.waitFor();
				return getUserAttempt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null;
			
	}
	
	/**
	 * This method is called from BashProcess#recordAndRetrieve() and thus is only called
	 * when the BashProcess is finished. It reads the file "recout.mlf" to retrieve the user's spoken
	 * Maori syllables, and returns these as a single string.
	 * 
	 * @return A string representing the user's spoken Maori syllables.
	 */
	public String getUserAttempt()  {
		 
			
			BufferedReader br;
			try {
				
				br = new BufferedReader(new FileReader("HTK/MaoriNumbers/recout.mlf"));
			
				String line;
				List<String> userAttemptList = new ArrayList<String>();
				while ((line = br.readLine())!=null) {
					
					if (line.equals("sil")) {
						
						//go to the next line after "sil"
						line = br.readLine();
						
						while (!line.equals("sil")) {
							
							userAttemptList.add(line);
							line = br.readLine();
						}
					}
				}
				
				br.close();
				_userAttempt = String.join(" ", userAttemptList);
				return _userAttempt;
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}	
	
	@SuppressWarnings("unused")
	public void hearRecording() {
		
		pb2 = new ProcessBuilder("bash", "-c", "aplay foo.wav");
		pb2.directory(new File("HTK/MaoriNumbers"));
		
		Process hearUsersAttempt = null;
		try {
			
			hearUsersAttempt = pb2.start();
			hearUsersAttempt.waitFor();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
}
