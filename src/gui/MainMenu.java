package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import utility.NumberList;

public class MainMenu extends VBox {

	// Initialize buttons
	private final Button EASY_BUTTON;
	private final Button HARD_BUTTON;
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public MainMenu() {
		// Set up buttons
		EASY_BUTTON = new Button("Practise 1-9");
		HARD_BUTTON = new Button("Practise 1-99");
		EASY_BUTTON.setScaleX(2);
		EASY_BUTTON.setScaleY(2);
		HARD_BUTTON.setScaleX(2);
		HARD_BUTTON.setScaleY(2);
		setUpActions(); 
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		setSpacing(80);
		
		getChildren().add(EASY_BUTTON);
		getChildren().add(HARD_BUTTON);
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
	}
	
}
