package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenu extends VBox{


	// Initialize buttons
	private final Button NUMBER_PRACTICE;
	private final Button SUM_PRACTICE;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;

	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public MainMenu() {
		
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
		NUMBER_PRACTICE = new Button("Practice Numbers");
		SUM_PRACTICE = new Button(" Practice Maths ");
		NUMBER_PRACTICE.setScaleX(2);
		NUMBER_PRACTICE.setScaleY(2);
		SUM_PRACTICE.setScaleX(2);
		SUM_PRACTICE.setScaleY(2);
		NUMBER_PRACTICE.setFont(App.getRegFont());
		SUM_PRACTICE.setFont(App.getRegFont());
		setUpActions(); 
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(NUMBER_PRACTICE);
		getChildren().add(SUM_PRACTICE);
		
	}
	
	/**
	 * Sets up action event handlers for the buttons of this pane
	 */
	private void setUpActions() {
		// Event handler for number practice button
		NUMBER_PRACTICE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			
				// Move to the number menu
				App.getMainStage().setScene(new Scene(new numberPractice.NumberMainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
		// Event handler for sum practice button
		SUM_PRACTICE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Move to the sum menu
				App.getMainStage().setScene(new Scene(new sumPractice.SumMainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
	}
	
}


