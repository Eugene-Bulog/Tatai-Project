package sumPractice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.App;
import utility.EquationList;

public class SumMainMenu extends VBox {

	// Initialize buttons
	private final Button START_BUTTON;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;
	private final Spinner<Integer> LENGTH;

	
	
	/**
	 * Constructor for MainMenu VBox object, sets up the layout and functions of this view
	 */
	public SumMainMenu() {
		
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
		SUBTITLE = new Label("Please select how many questions you wish to play: ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(App.getRegFont());
		
		// Set up buttons
		START_BUTTON = new Button(" Start ");
		START_BUTTON.setScaleX(2);
		START_BUTTON.setScaleY(2);
		START_BUTTON.setFont(App.getRegFont());
		setUpActions(); 
		
		// Set up length spinner
		LENGTH = new Spinner<Integer>(1, 99, 5);
		LENGTH.setEditable(true);
		LENGTH.setScaleX(2);
		LENGTH.setScaleY(2);
		LENGTH.setMaxWidth(70);
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(LENGTH);
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
				EquationList.generateHard(LENGTH.getValue());
				
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
	}
	
}
