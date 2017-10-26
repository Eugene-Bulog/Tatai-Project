package sumPractice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.App;
import main.Highscores;

public class SumMainMenu extends VBox {

	// Initialize buttons
	private final Button START_BUTTON;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;
	private final Button MAIN_MENU;
	private final Button CUSTOM;
	private final Button LEADERBOARD;

	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public SumMainMenu() {
		
		// high scores button
		LEADERBOARD = new Button("Highscores");
		LEADERBOARD.setScaleX(2);
		LEADERBOARD.setScaleY(2);
		LEADERBOARD.setFont(App.getRegFont());
		
		// Set up background image
		setBackground(App.getPatternBackground());
		
		// Set up title
		TITLE = new Label("TATAI!");
		TITLE.setFont(App.getMaoriFont());
		
		// Set up accent for title
		ACCENT = new Label("-     ");
		ACCENT.setFont(App.getMaoriFont());
		ACCENT.setTextFill(Color.web("#964B00"));
		ACCENT.setPadding(new Insets(-200, 0, 0, 0));


		TITLE.setTextFill(Color.web("#964B00"));
		TITLE.setPadding(new Insets(-200, 0, 0, 0));
		SUBTITLE = new Label("Please select a mode: ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(App.getRegFont());
		
		// Set up buttons
		START_BUTTON = new Button("Play New Questions");
		START_BUTTON.setScaleX(2);
		START_BUTTON.setScaleY(2);
		START_BUTTON.setFont(App.getRegFont());
		CUSTOM = new Button("Saved Questions");
		CUSTOM.setScaleX(2);
		CUSTOM.setScaleY(2);
		CUSTOM.setFont(App.getRegFont());
		MAIN_MENU = new Button("Main Menu");
		MAIN_MENU.setScaleX(2);
		MAIN_MENU.setScaleY(2);
		MAIN_MENU.setFont(App.getRegFont());
		setUpActions(); 
		
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(START_BUTTON);
		getChildren().add(CUSTOM);
		getChildren().add(LEADERBOARD);
		getChildren().add(MAIN_MENU);
		
	}
	
	/**
	 * Sets up action event handlers for the buttons of this pane
	 */
	private void setUpActions() {
		// Event handler for START_BUTTON
		START_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new StartNew(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
		
		// Event handler for main menu button
		MAIN_MENU.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Returns to main menu
				App.getMainStage().setScene(new Scene(new main.MainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		// Event handler for saved questions button
		CUSTOM.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Goes to saved questions menu
				App.getMainStage().setScene(new Scene(new SavedQuestions(),App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		// Event handler for high scores button
		LEADERBOARD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Move to help screen
				App.getMainStage().setScene(new Scene(new Highscores(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
	}
	
}
