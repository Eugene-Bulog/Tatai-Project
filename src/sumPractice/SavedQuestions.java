package sumPractice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.App;
import main.MainMenu;

public class SavedQuestions extends VBox{

	
	private final Button MATHS_MENU;
	
	
	public SavedQuestions() {
		// Set up background image
		setBackground(App.getPatternBackground());
		
		MATHS_MENU = new Button("Return to Maths Menu");
		MATHS_MENU.setScaleX(1.5);
		MATHS_MENU.setScaleY(1.5);
		MATHS_MENU.setFont(App.getRegFont());

	//	setAlignment(MATHS_MENU, Pos.);
		
		setUpActions();
		
		setAlignment(Pos.CENTER);
		getChildren().add(MATHS_MENU);
		
	}

	
	
	/**
	 * Sets up action listeners for all buttons in this scene
	 */
	private void setUpActions() {
		//Set up actions for continue button
		MATHS_MENU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				App.getMainStage().setScene(new Scene(new SumMainMenu(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
	}
	
	
	
	
}
