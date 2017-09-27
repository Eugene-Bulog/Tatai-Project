package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utility.NumberList;

public class Summary extends VBox{

	
	// The label for score
	private Label _score;
	private SummaryBox _summaryBox;
	
	// Buttons
	private Button _mainMenu;
	private Button _playAgain;
	private Button _nextLevel;
	
	/**
	 * Constructor for the summary VBox, sets up the summary list
	 */
	public Summary() {
		
		// set the background
		setBackground(App.getPatternBackground());
		
		_summaryBox = new SummaryBox();
		
		// Set up score label
		_score = new Label(NumberList.getSessionScore() + "/10");
		_score.setFont(App.getMaoriFont());
		_score.setTextFill(Color.web("#964B00"));
		_score.setPadding(new Insets(-120, 0, -20, 0));
		
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
		setAlignment(Pos.CENTER);
		
		
		// Adds the components to the VBox
		getChildren().add(_score);
		getChildren().add(_summaryBox);
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
				App.getMainStage().setScene(new Scene(new QuestionAsk(),
						App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		_mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				App.getMainStage().setScene(new Scene(new MainMenu(),
						App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		_nextLevel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Generate a list of hard questions
				NumberList.generateHard();
				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),
						App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
	}
	
	private class SummaryBox extends VBox {
		
		// An array of labels which will display the questions and results
		private Label[] _labels;
		
		/**
		 * Constructor for summarybox, does all of the GUI setup required
		 */
		public SummaryBox() {
			
			// Set center alignment
			setAlignment(Pos.CENTER);
			
			// Defines _labels
			_labels = new Label[10];
			
			// Loops through each question and alters the appropriate label
			for (int i = 0; i < 10; i++) {
				
				// Sets the label text as the question, followed by if the answer was
				// right or wrong
				_labels[i] = new Label(NumberList.getNumberAt(i) + ": " + 
						NumberList.getAnswerAt(i));
				
				// Set scale and font
				_labels[i].setScaleX(1.5);
				_labels[i].setScaleY(1.5);
				_labels[i].setFont(App.getRegFont());
				
				
				// If the answer was Correct, colors the text green, otherwise red
				if (NumberList.getAnswerAt(i).equals("Correct")) {
					_labels[i].setTextFill(Color.web("#50B948"));
					_labels[i].setGraphic(new ImageView(App.getTickIcon()));
				}
				else {
					_labels[i].setTextFill(Color.web("#CC0000"));
					_labels[i].setGraphic(new ImageView(App.getCrossIcon()));
				}
				
				// adds the labels to the vbox
				getChildren().add(_labels[i]);
			}
		}
	}
	
}
