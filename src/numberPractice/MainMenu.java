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
import utility.NumberList;

public class MainMenu extends VBox {

	// Initialize buttons
	private final Button EASY_BUTTON;
	private final Button HARD_BUTTON;
	private final Label TITLE;
	private final Label SUBTITLE;
	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public MainMenu() {
		
		// Set up background image
		setBackground(NumberApp.getPatternBackground());
		
		// Set up title
		TITLE = new Label("TATAI!");
		TITLE.setFont(NumberApp.getMaoriFont());
		

		TITLE.setTextFill(Color.web("#964B00"));
		TITLE.setPadding(new Insets(-200, 0, 0, 0));
		SUBTITLE = new Label("Please select a level: ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(NumberApp.getRegFont());
		
		// Set up buttons
		EASY_BUTTON = new Button(" Practice 1-9 ");
		HARD_BUTTON = new Button("Practice 1-99");
		EASY_BUTTON.setScaleX(2);
		EASY_BUTTON.setScaleY(2);
		HARD_BUTTON.setScaleX(2);
		HARD_BUTTON.setScaleY(2);
		EASY_BUTTON.setFont(NumberApp.getRegFont());
		HARD_BUTTON.setFont(NumberApp.getRegFont());
		setUpActions(); 
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
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
				NumberApp.getMainStage().setScene(new Scene(new QuestionAsk(),NumberApp.APP_WIDTH,NumberApp.APP_HEIGHT));
			}
			
		});
		
		// Event handler for HARD_BUTTON
		HARD_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Generate a list of hard questions
				NumberList.generateHard();
				
				// Move to the question scene
				NumberApp.getMainStage().setScene(new Scene(new QuestionAsk(),NumberApp.APP_WIDTH,NumberApp.APP_HEIGHT));
			}
			
		});
	}
	
}
