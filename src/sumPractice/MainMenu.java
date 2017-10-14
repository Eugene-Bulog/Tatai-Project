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
import utility.EquationList;

public class MainMenu extends VBox {

	// Initialize buttons
	private final Button START_BUTTON;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;

	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public MainMenu() {
		
		// Set up background image
		setBackground(SumApp.getPatternBackground());
		
		// Set up title
		TITLE = new Label("TATAI!");
		TITLE.setFont(SumApp.getMaoriFont());
		
		// Set up accent for title
		ACCENT = new Label("-     ");
		ACCENT.setFont(SumApp.getMaoriFont());
		ACCENT.setTextFill(Color.web("#964B00"));
		ACCENT.setPadding(new Insets(-200, 0, 0, 0));


		TITLE.setTextFill(Color.web("#964B00"));
		TITLE.setPadding(new Insets(-200, 0, 0, 0));
		SUBTITLE = new Label("Please select a level: ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(SumApp.getRegFont());
		
		// Set up buttons
		START_BUTTON = new Button(" Start ");
		START_BUTTON.setScaleX(2);
		START_BUTTON.setScaleY(2);
		START_BUTTON.setFont(SumApp.getRegFont());
		setUpActions(); 
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(START_BUTTON);
		
	}
	
	/**
	 * Sets up action event handlers for the buttons of this pane
	 */
	private void setUpActions() {
		// Event handler for EASY_BUTTON
		START_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Generate a list of easy questions
				EquationList.generateHard(5);
				
				// Move to the question scene
				SumApp.getMainStage().setScene(new Scene(new QuestionAsk(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
			}
			
		});
	}
	
}
