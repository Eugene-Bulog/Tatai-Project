package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BashProcess {

	private static final  BashProcess INSTANCE = new BashProcess();
	private ProcessBuilder pb;
	
	
	private BashProcess() {
		
	}
	
	public static BashProcess getInstance() {
		
		return INSTANCE;
	}
	
	/**
	 * This method runs record commands in Bash (using the supplied GoSpeech script file).
	 *  
	 */
	public String recordAndRetrieve() {
			
			Process recordingProcess = null;
			pb = new ProcessBuilder("bash", "-c", "./GoSpeech");
			pb.directory(new File("HTK/MaoriNumbers"));
			try {
				recordingProcess = pb.start();
				recordingProcess.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return getUserAttempt(recordingProcess);
			
	}
	
	//read recout.mlf and join lines, return user attempt as string
	public String getUserAttempt(Process recordingProcess)  {
		
			if (recordingProcess == null) {
				return null;
			} 
			
			BufferedReader br;
			try {
				
				br = new BufferedReader(new FileReader("HTK/MaoriNumbers/recout.mlf"));
			
				String line;
				List<String> userAttempt = new ArrayList<String>();
				while ((line = br.readLine())!=null) {
					
					if (line.equals("sil")) {
						
						//go to the next line after "sil"
						line = br.readLine();
						
						while (!line.equals("sil")) {
							
							userAttempt.add(line);
							line = br.readLine();
						}
					}
				}
				
				br.close();
				return String.join(" ", userAttempt);
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}	
	
	
}
