package numberPractice;

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
import utility.NumberList;

public class NumberMainMenu extends VBox {

	// Initialize buttons
	private final Button EASY_BUTTON;
	private final Button HARD_BUTTON;
	private final Button MAIN_MENU;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;

	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public NumberMainMenu() {
		
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
		SUBTITLE = new Label("Please select a level: ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(App.getRegFont());
		
		// Set up buttons
		EASY_BUTTON = new Button(" Practice 1-9 ");
		HARD_BUTTON = new Button("Practice 1-99");
		MAIN_MENU = new Button("Main Menu");
		EASY_BUTTON.setScaleX(2);
		EASY_BUTTON.setScaleY(2);
		MAIN_MENU.setScaleX(2);
		MAIN_MENU.setScaleY(2);
		HARD_BUTTON.setScaleX(2);
		HARD_BUTTON.setScaleY(2);
		EASY_BUTTON.setFont(App.getRegFont());
		HARD_BUTTON.setFont(App.getRegFont());
		MAIN_MENU.setFont(App.getRegFont());
		setUpActions(); 
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(EASY_BUTTON);
		getChildren().add(HARD_BUTTON);
		getChildren().add(MAIN_MENU);
		
	}
	
	/**
	 * Sets up action event handlers for the buttons of this pane
	 */
	private void setUpActions() {
		// Event handler for EASY_BUTTON
		EASY_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Generate a list of easy questions
				NumberList.generateEasy();
				
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
		// Event handler for HARD_BUTTON
		HARD_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Generate a list of hard questions
				NumberList.generateHard();
				
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
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
	}
	
}
