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

public class StartNew extends VBox{

	// Initialize components
	private final Button START_BUTTON;
	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;
	private final Spinner<Integer> LENGTH;
	private final Button MATHS_MENU;
	
	public StartNew() {
		// Set up background image
				setBackground(App.getPatternBackground());
				
				// Set up title
				TITLE = new Label("TATAI!");
				TITLE.setFont(App.getMaoriFont());
				
				// Set up accent for title
				ACCENT = new Label("-     ");
				ACCENT.setFont(App.getMaoriFont());
				ACCENT.setTextFill(Color.web("#964B00"));
				ACCENT.setPadding(new Insets(-295, 0, 0, 0));


				TITLE.setTextFill(Color.web("#964B00"));
				TITLE.setPadding(new Insets(-246, 0, 0, 0));
				SUBTITLE = new Label("Please select how many questions you wish to play (1-10): ");
				SUBTITLE.setScaleX(1.5);
				SUBTITLE.setScaleY(1.5);
				SUBTITLE.setFont(App.getRegFont());
				
				// Set up buttons
				START_BUTTON = new Button("Start");
				START_BUTTON.setScaleX(2);
				START_BUTTON.setScaleY(2);
				START_BUTTON.setFont(App.getRegFont());
				MATHS_MENU = new Button("Maths Menu");
				MATHS_MENU.setScaleX(2);
				MATHS_MENU.setScaleY(2);
				MATHS_MENU.setFont(App.getRegFont());
				setUpActions(); 
				
				// Set up length spinner
				LENGTH = new Spinner<Integer>(1, 10, 10);
				LENGTH.setEditable(false);
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
				getChildren().add(MATHS_MENU);
				
	}
	
	
	/**
	 * Sets up action event handlers for the buttons of this pane
	 */
	private void setUpActions() {
		// Event handler for EASY_BUTTON
		START_BUTTON.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Check users level and create appropriate list
				switch (utility.SaveData.getUserLevel()) {
				case 1:
					// Generate a list of easy questions
					EquationList.generateEasy(LENGTH.getValue());
					break;
				case 2:
					// Generate a list of medium questions
					EquationList.generateMid(LENGTH.getValue());
					break;
				case 3:
					// Generate a list of hard questions
					EquationList.generateHard(LENGTH.getValue());
					break;
				}
				
				
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
		
		// Event handler for main menu button
		MATHS_MENU.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Returns to main menu
				App.getMainStage().setScene(new Scene(new SumMainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		
	}
	
}
