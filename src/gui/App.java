package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	// The main stage of the application
	private static Stage _mainStage;
	
	/**
	 * Getter method for the main stage of the application
	 * @return: The main stage being used by the application
	 */
	public static Stage getMainStage() {
		return _mainStage;
	}
	
	public static void main(String[] args) {
		// Main launches the application
        launch(args);
    }
	

	// Start application
	@Override
	public void start(Stage mainStage) throws Exception {
		_mainStage = mainStage;
		
		// Set up main stage title and initial pane
		_mainStage.setTitle("Tātai! Practice Module");
		_mainStage.setScene(new Scene(new MainMenu(), 800,600));
		
		// Make the stage visible
		_mainStage.show();
	}

}
