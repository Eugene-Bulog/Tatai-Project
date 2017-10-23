package sumPractice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.App;

public class SavedQuestions extends VBox{

	
	private final Button MATHS_MENU;
	private final Button CREATE_NEW;
	
	
	public SavedQuestions() {
		// Set up background image
		setBackground(App.getPatternBackground());
		
		// create new list button
		CREATE_NEW = new Button("Create New List");
		CREATE_NEW.setScaleX(2);
		CREATE_NEW.setScaleY(2);
		CREATE_NEW.setFont(App.getRegFont());
		
		// return to menu button
		MATHS_MENU = new Button("Return to Maths Menu");
		MATHS_MENU.setScaleX(2);
		MATHS_MENU.setScaleY(2);
		MATHS_MENU.setFont(App.getRegFont());

		
		setUpActions();
		
		setAlignment(Pos.CENTER);
		setSpacing(40);
		getChildren().addAll(CREATE_NEW,MATHS_MENU);
		
	}

	
	
	/**
	 * Sets up action listeners for all buttons in this scene
	 */
	private void setUpActions() {
		//Set up actions for return to menu button
		MATHS_MENU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				App.getMainStage().setScene(new Scene(new SumMainMenu(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
		
		
		//Set up actions for create new button
		CREATE_NEW.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				App.getMainStage().setScene(new Scene(new CreateList(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
	}
	
	
	
	
}
