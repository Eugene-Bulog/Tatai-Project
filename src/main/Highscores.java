package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utility.SaveData;

public class Highscores extends VBox{

	private final Button MAIN_MENU;
	private final Label USERNAME;
	private final Label LEVEL;
	private final Label SCORE1;
	private final Label SCORE2;
	private final Label SCORE3;
	private final Label AVERAGE;
	
	
	public Highscores() {
		
		// Set up background image
		setBackground(App.getPatternBackground());
		
		// Display user stats and set up corresponding labels
		USERNAME = new Label(App.getName());
		USERNAME.setScaleX(3);
		USERNAME.setScaleY(3);
		USERNAME.setFont(App.getRegFont());
		USERNAME.setTextFill(Color.web("#964B00"));
		switch (SaveData.getUserLevel()) {
		default:
			LEVEL = new Label("Your maths level: 1 (easy)");
			break;
		case 2: 
			LEVEL = new Label("Your maths level: 2 (medium)");
			break;
		case 3:
			LEVEL = new Label("Your maths level: 3 (hard)");
			break;
		}
		
		LEVEL.setScaleX(2);
		LEVEL.setScaleY(2);
		LEVEL.setFont(App.getRegFont());
		LEVEL.setTextFill(Color.web("#964B00"));
		SCORE1 = new Label("Highscore on level 1: " + SaveData.getHSEasy() + "%");
		SCORE1.setScaleX(2);
		SCORE1.setScaleY(2);
		SCORE1.setFont(App.getRegFont());
		SCORE1.setTextFill(Color.web("#964B00"));
		SCORE2 = new Label("Highscore on level 2: " + SaveData.getHSMid() + "%");
		SCORE2.setScaleX(2);
		SCORE2.setScaleY(2);
		SCORE2.setFont(App.getRegFont());
		SCORE2.setTextFill(Color.web("#964B00"));
		SCORE3 = new Label("Highscore on level 3: " + SaveData.getHSHard() + "%");
		SCORE3.setScaleX(2);
		SCORE3.setScaleY(2);
		SCORE3.setFont(App.getRegFont());
		SCORE3.setTextFill(Color.web("#964B00"));
		AVERAGE = new Label("Average score on level " + SaveData.getUserLevel() + ": "
				+ SaveData.getLevelAvg() + "%");
		AVERAGE.setScaleX(2);
		AVERAGE.setScaleY(2);
		AVERAGE.setFont(App.getRegFont());
		AVERAGE.setTextFill(Color.web("#964B00"));
		
		
		// Return to main menu button
		MAIN_MENU = new Button("Return to Main Menu");
		MAIN_MENU.setScaleX(2);
		MAIN_MENU.setScaleY(2);
		
		
		setAlignment(Pos.CENTER);
		setSpacing(40);
		getChildren().addAll(USERNAME,LEVEL,SCORE1,SCORE2,SCORE3,AVERAGE,MAIN_MENU);
		
		setUpActions();
		
	}


	private void setUpActions() {
		
		// Event handler for help button
		MAIN_MENU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Move to help screen
				App.getMainStage().setScene(new Scene(new sumPractice.SumMainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
	}
	
}
