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
import javafx.scene.text.TextAlignment;
import utility.EquationList;
import utility.MaoriNumbers;

public class QuestionResult extends VBox{
	
	private String[] _question;
	private Button _button;
	private Button _cancelButton;
	private Label _numberLabel;
	private Label _resultLabel;
	private Label _currentScore;
	private boolean _secondAttempt;
	private boolean _correct;
	
	public QuestionResult(boolean correct,boolean secondAttempt, String[] question) {
		
		setBackground(SumApp.getPatternBackground());
		_question = question;
		_secondAttempt = secondAttempt;
		_correct = correct;
		
		// Sets the label to the question value
		_numberLabel = new Label(_question[0]);
		_numberLabel.setFont(SumApp.getMaoriFont());
		_numberLabel.setTextFill(Color.web("#964B00"));
		_numberLabel.setPadding(new Insets(-140, 0, 0, 0));
		
		
		// Label for currentscore
		_currentScore = new Label("Current Score: " + EquationList.getSessionScore() + "/" + EquationList.getNumberAnswered());
		_currentScore.setTextAlignment(TextAlignment.CENTER);
		_currentScore.setFont(SumApp.getRegFont());
		_currentScore.setScaleX(2);
		_currentScore.setScaleY(2);
		
		if (correct) {
			_resultLabel = new Label("Correct!");
		} else {
			if (secondAttempt) {
				
				String theirAttempt = QuestionAsk.getUserAttempt();
				if (theirAttempt.isEmpty()) {
					
					// If no recording is picked up:
					_resultLabel = new Label("Incorrect! The correct answer was '" + MaoriNumbers.getMaoriPronunciation(Integer.parseInt(_question[1]))+"', \nbut you didn't say anything.");
				} else {
					
					// They got it wrong on their second attempt:
					_resultLabel = new Label("Incorrect! You said '"+theirAttempt+"',\nbut the correct answer was '" + MaoriNumbers.getMaoriPronunciation(Integer.parseInt(_question[1]))+"'.");
				}
			} else {
				_resultLabel = new Label("Incorrect! Try again!");
			}
		}
		_resultLabel.setTextAlignment(TextAlignment.CENTER);
		_resultLabel.setFont(SumApp.getRegFont());
		_resultLabel.setScaleX(2);
		_resultLabel.setScaleY(2);

		// Set up record button
		if (!secondAttempt && !correct) {
			_button = new Button("Try again");
		}
		else {
			_button = new Button("Continue");
		}
		_button.setFont(SumApp.getRegFont());
		_button.setScaleX(2);
		_button.setScaleY(2);
		_cancelButton = new Button("Main Menu");
		_cancelButton.setScaleX(2);
		_cancelButton.setScaleY(2);
		_cancelButton.setFont(SumApp.getRegFont());
		setUpAction();
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		setSpacing(40);

		getChildren().add(_numberLabel);
		getChildren().add(_resultLabel);
		getChildren().add(_button);
		getChildren().add(_cancelButton);
		getChildren().add(_currentScore);
		
	}
	
	/**
	 * Sets up the action handler for the button, action depends on whether the answer was correct,
	 * and which attempt this is (first or second)
	 */
	private void setUpAction() {
		
		
		// Action for continue / try again button
		_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// If the answer was correct, logs this as correct
				if (_correct) {
					EquationList.logAnswer(_question[0],Integer.parseInt(_question[1]), _correct);
					
					// If there are no more questions left, goes to summary scene, otherwise next question
					if (EquationList.noQuestions()) {
						SumApp.getMainStage().setScene(new Scene(new Summary(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
					}
					else {
						SumApp.getMainStage().setScene(new Scene(new QuestionAsk(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
					}
				}
				else {
					// If wrong answer & second attempt at this question, logs it as wrong
					if (_secondAttempt) {
						EquationList.logAnswer(_question[0],Integer.parseInt(_question[1]), _correct);
						
						// If there are no more questions left, goes to summary scene, otherwise next question
						if (EquationList.noQuestions()) {
							SumApp.getMainStage().setScene(new Scene(new Summary(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
						}
						else {
							SumApp.getMainStage().setScene(new Scene(new QuestionAsk(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
						}
					}
					// If first attempt and wrong answer, user can try again
					else {
						SumApp.getMainStage().setScene(new Scene(new QuestionAsk(_question),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
					}
				}
			}
		});
		
		
		// Set up action for cancel button to take user back to main menu
				_cancelButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						SumApp.getMainStage().setScene(new Scene(new MainMenu(),SumApp.APP_WIDTH,SumApp.APP_HEIGHT));
					}
					
				});
				
	}
	
}
