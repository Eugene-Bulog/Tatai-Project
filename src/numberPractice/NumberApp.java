package numberPractice;



import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NumberApp extends Application{
	
	// The main stage of the application
	private static Stage _mainStage;
	
	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 600;
	
	private static Font _maoriFont;
	private static Font _regularFont;
	private static Background _patternBackground;
	private static Image _tick;
	private static Image _cross;
	
	/**
	 * Getter method for the main stage of the application
	 * @return: The main stage being used by the application
	 */
	public static Stage getMainStage() {
		return _mainStage;
	}
	
	public static void start(String[] args) {
		// Main launches the application
        launch(args);
    }
	

	// Start application
	@Override
	public void start(Stage mainStage) throws Exception {
		_mainStage = mainStage;
		loadAssets();
		
		// Set up main stage title
		_mainStage.setTitle("TĀTAI! Practice Module");
		_mainStage.setResizable(false);
		
		
		
	}
	
	/**
	 * Getter method for the main "Maori font" to be used throughout the app.
	 * Size 110, font name ldj_maori.ttf
	 * @return the font object representing this font
	 */
	public static Font getMaoriFont() {
		return _maoriFont;
	}
	
	/**
	 * Getter method for the main "regular font" to be used throughout the app.
	 * Size 220, font name MyriadPro-Regular.ttf
	 * @return the font object representing this font
	 */
	public static Font getRegFont() {
		return _regularFont;
	}
	
	/**
	 * Getter method for the main background to be used throughout the app
	 * @return the BackgroundImage object representing this image
	 */
	public static Background getPatternBackground() {
		return _patternBackground;
	}
	
	/**
	 * Getter method for the tick icon
	 * @return the Image object representing this image
	 */
	public static Image getTickIcon() {
		return _tick;
	}
	
	/**
	 * Getter method for the cross icon
	 * @return the Image object representing this image
	 */
	public static Image getCrossIcon() {
		return _cross;
	}

	
	/**
	 * Loads in the background and font assets for the app, on a background thread.
	 * Once loading is finished, launches the app
	 */
	public void loadAssets() {
		
		// Creates a service subclass that will execute our loading task on a background thread
		Service<Void> service = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				
				// Create a task object
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						// Load the Maori font
						_maoriFont = Font.loadFont(this.getClass().getResource("/Resources/Tuamotu.ttf").toExternalForm(), 75);
						// Loads the regular font
						_regularFont = Font.loadFont(this.getClass().getResource("/Resources/MyriadPro-Regular.ttf").toExternalForm(), 14);
						// Loads the background
						_patternBackground = new Background(new BackgroundImage(new Image(this.getClass().getResource("/Resources/maoripattern.png").toExternalForm()
								,2000,477,true,true), BackgroundRepeat.REPEAT, 
						        BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
						          BackgroundSize.DEFAULT));
						_tick = new Image(getClass().getResourceAsStream("/Resources/Tick.png"),10,10,true,true);
						_cross = new Image(getClass().getResourceAsStream("/Resources/Cross.png"),10,10,true,true);
						return null;
					}
				};
			}
			
			@Override
			protected void succeeded() {
				// Set up the main menu scene
				_mainStage.setScene(new Scene(new MainMenu(), APP_WIDTH,APP_HEIGHT));
				// Make the stage visible
				_mainStage.show();
				_mainStage.sizeToScene();
			}
		};
		service.start();
	}


}
