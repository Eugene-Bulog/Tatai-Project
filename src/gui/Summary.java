package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import utility.NumberList;

public class Summary extends VBox{

	// The list which will summarize all the questions and answers this session
	private ListView<String> _answerList = new ListView<String>();
	private ObservableList<String> _questions = FXCollections.observableArrayList();
	
	// The label for score
	private Label _score;
	
	// Buttons
	private Button _mainMenu;
	private Button _playAgain;
	private Button _nextLevel;
	
	/**
	 * Constructor for the summary VBox, sets up the summary list
	 */
	public Summary() {
		
		setBackground(App.getPatternBackground());
		
		// Adds each question and answer to the list
		for (int i = 0; i < 10; i++) {
			_questions.add(NumberList.getNumberAt(i) + ": " + 
					NumberList.getAnswerAt(i));
		}
		
		// Adds the ObservableList to the ListView
		_answerList.setItems(_questions);
		_answerList.setPrefHeight(232);
		
		// Set up score label
		_score = new Label("Score: " + NumberList.getSessionScore() + "/10");
		_score.setFont(App.getRegFont());
		_score.setScaleX(2.5);
		_score.setScaleY(2.5);
		
		// Set up buttons
		_mainMenu = new Button("Main Menu");
		_nextLevel = new Button("Next Level");
		_playAgain = new Button("Play Again");
		_mainMenu.setFont(App.getRegFont());
		_nextLevel.setFont(App.getRegFont());
		_playAgain.setFont(App.getRegFont());
		_playAgain.setScaleX(2);
		_playAgain.setScaleY(2);
		_mainMenu.setScaleX(2);
		_mainMenu.setScaleY(2);
		_nextLevel.setScaleX(2);
		_nextLevel.setScaleY(2);
		setUpActions();
		
		setSpacing(40);
		this.setAlignment(Pos.CENTER);
		
		
		// Adds the components to the VBox
		getChildren().add(_score);
		getChildren().add(_answerList);
		getChildren().add(_playAgain);
		getChildren().add(_mainMenu);
		
		// Only offers next level if score is 8 or more, and playing on easy
		if (NumberList.getSessionScore() >= 8 && !NumberList.isHard()) {
			getChildren().add(_nextLevel);
		}
	}
	
	private void setUpActions() {
		_playAgain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (NumberList.isHard()) {
					// Generate a list of hard questions
					NumberList.generateHard();
				}
				else {
					// Generate a list of easy questions
					NumberList.generateEasy();
					
				}

				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		_mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				App.getMainStage().setScene(new Scene(new MainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		_nextLevel.setOnAction(new EventHandler<ActionEvent>() {
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
