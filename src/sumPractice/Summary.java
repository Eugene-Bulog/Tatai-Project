package sumPractice;

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
import main.App;
import utility.MaoriNumbers;
import utility.EquationList;

public class Summary extends VBox{

	
	// The label for score
	private Label _score;
	private SummaryBox _summaryBox;
	private boolean[] _mileStones;
	private Label _notification;
	
	// Buttons
	private Button _mainMenu;
	private Button _playAgain;
	
	/**
	 * Constructor for the summary VBox, sets up the summary list
	 */
	public Summary() {
		
		// set the background
		setBackground(App.getPatternBackground());
		
		_summaryBox = new SummaryBox();
		
		// Set up notification label & log score for saving
		_notification = new Label("");
		if (!utility.EquationList.isCustom()) {
			_mileStones = utility.SaveData.logScore(utility.EquationList.getSessionScore(), 
					utility.EquationList.getNumberAnswered());
			
			// highscore but same level
			if (_mileStones[0] && !_mileStones[1]) {
				_notification.setText("Congratulations! You beat your highscore for this level!");
			}
			// level up but no highscore
			else if (!_mileStones[0] && _mileStones[1]) {
				_notification.setText("Congratulations! You have moved up a skill level!");
			}
			// Highscore & level up
			else if (_mileStones[0] && _mileStones[1]) {
				_notification.setText("Congratulations! You beat your highscore for this level\n"
						+ "and moved up a skill level!");
			}
		}
		else {
			_notification.setText("");
		}
		
		_notification.setScaleX(2);
		_notification.setScaleY(2);
		_notification.setFont(App.getRegFont());
		_notification.setTextFill(Color.RED);
		_notification.setAlignment(Pos.CENTER);
		_notification.setPadding(new Insets(-120, 0, -20, 0));
		
		
		
		
		// Set up score label
		_score = new Label(EquationList.getSessionScore() + "/" + EquationList.getNumberAnswered());
		_score.setFont(App.getRegFontLarge());
		_score.setTextFill(Color.web("#964B00"));
		_score.setPadding(new Insets(-120, 0, -20, 0));
		
		// Set up buttons
		_mainMenu = new Button("Maths Menu");
		_playAgain = new Button("Play Again");
		_mainMenu.setFont(App.getRegFont());
		_playAgain.setFont(App.getRegFont());
		_playAgain.setScaleX(2);
		_playAgain.setScaleY(2);
		_mainMenu.setScaleX(2);
		_mainMenu.setScaleY(2);
		setUpActions();
		
		setSpacing(40);
		setAlignment(Pos.CENTER);
		
		
		// Adds the components to the VBox
		getChildren().add(_notification);
		getChildren().add(_score);
		getChildren().add(_summaryBox);
		if (!utility.EquationList.isCustom()) {
			getChildren().add(_playAgain);
		}

		getChildren().add(_mainMenu);
		
	}
	
	private void setUpActions() {
		_playAgain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				// Check users level and create appropriate list
				switch (utility.SaveData.getUserLevel()) {
				case 1:
					// Generate a list of easy questions
					EquationList.generateEasy(EquationList.getNumberAnswered());
					break;
				case 2:
					// Generate a list of medium questions
					EquationList.generateMid(EquationList.getNumberAnswered());
					break;
				case 3:
					// Generate a list of hard questions
					EquationList.generateHard(EquationList.getNumberAnswered());
					break;
				}

				// Move to the question scene
				App.getMainStage().setScene(new Scene(new QuestionAsk(),
						App.APP_WIDTH,App.APP_HEIGHT));
			}
		});
		
		
		_mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				App.getMainStage().setScene(new Scene(new SumMainMenu(),
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
			_labels = new Label[EquationList.getNumberAnswered()];
			
			// Loops through each question and alters the appropriate label
			for (int i = 0; i < EquationList.getNumberAnswered(); i++) {
				
				// Sets the label text as the question, followed by if the answer was
				// right or wrong
				_labels[i] = new Label(EquationList.getQuestionAt(i) + " = " + 
						EquationList.getAnswerAt(i) + " (" + 
						MaoriNumbers.getMaoriPronunciation(EquationList.getAnswerAt(i)) + ")");
				
				// Set scale and font
				_labels[i].setScaleX(1.5);
				_labels[i].setScaleY(1.5);
				_labels[i].setFont(App.getRegFont());
				
				
				// If the answer was Correct, colors the text green, otherwise red
				if (EquationList.getUserAnswerAt(i)) {
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
