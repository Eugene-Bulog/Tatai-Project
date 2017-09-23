package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utility.NumberList;

public class QuestionResult extends VBox{
	
	private int _number;
	private Button _button;
	private Label _numberLabel;
	private boolean _secondAttempt;
	private boolean _correct;
	
	public QuestionResult(boolean correct,boolean secondAttempt, int number) {
		_number = number;
		_secondAttempt = secondAttempt;
		_correct = correct;
		
		// Sets the label to the question value
		_numberLabel = new Label(Integer.toString(_number) +" " + correct);

		// Set up record button
		if (!secondAttempt && !correct) {
			_button = new Button("Try again");
		}
		else {
			_button = new Button("Continue");
		}
		_button.setScaleX(2);
		_button.setScaleY(2);
		setUpAction();
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		setSpacing(80);

		getChildren().add(_numberLabel);
		getChildren().add(_button);
		
	}
	
	/**
	 * Sets up the action handler for the button, action depends on whether the answer was correct,
	 * and which attempt this is (first or second)
	 */
	private void setUpAction() {
		_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// If the answer was correct, logs this as correct
				if (_correct) {
					NumberList.logAnswer(_number, _correct);
					
					// If there are no more questions left, goes to summary scene, otherwise next question
					if (NumberList.noQuestions()) {
						App.getMainStage().setScene(new Scene(new Summary(),App.APP_WIDTH,App.APP_HEIGHT));
					}
					else {
						App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
					}
				}
				else {
					// If wrong answer & second attempt at this question, logs it as wrong
					if (_secondAttempt) {
						NumberList.logAnswer(_number, _correct);
						
						// If there are no more questions left, goes to summary scene, otherwise next question
						if (NumberList.noQuestions()) {
							App.getMainStage().setScene(new Scene(new Summary(),App.APP_WIDTH,App.APP_HEIGHT));
						}
						else {
							App.getMainStage().setScene(new Scene(new QuestionAsk(),App.APP_WIDTH,App.APP_HEIGHT));
						}
					}
					// If first attempt and wrong answer, user can try again
					else {
						App.getMainStage().setScene(new Scene(new QuestionAsk(_number),App.APP_WIDTH,App.APP_HEIGHT));
					}
				}
			}
		});
	}
	
}
