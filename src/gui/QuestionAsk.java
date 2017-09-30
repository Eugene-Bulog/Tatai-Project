package gui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import utility.BashProcess;
import utility.MaoriNumbers;
import utility.NumberList;

public class QuestionAsk extends VBox{

	// The number being asked for this question
	private int _number;
	private Label _numberLabel;
	private Label _currentScore;
	
	// Whether or not this is the second attempt for this question
	private boolean _secondAttempt;
	
	private Button _hearRecording;
	private Button _submit;
	private Button _recordButton;
	private Button _cancelButton;
	
	// The user's attempted Maori pronunciation
	private static String _userAttempt = "";
	/**
	 * Constructor for QuestionAsk VBox
	 */
	public QuestionAsk() {
		
		_secondAttempt = false;
		_number = NumberList.getNumber(); // Pop next question value and set number to this
	
		setUpGUI();
	}
	
	/**
	 * Constructor for QuestionAsk second attempt
	 * @param number: the number for which to do a second attempt
	 */
	public QuestionAsk(int number) {
		_secondAttempt = true;
		_number = number;
		
		setUpGUI();
	}

	private void setUpGUI() {
			setBackground(App.getPatternBackground());
		
			// Sets the label to the question value
			_numberLabel = new Label(Integer.toString(_number));
			_numberLabel.setFont(App.getMaoriFont());
			_numberLabel.setTextFill(Color.web("#964B00"));
			_numberLabel.setPadding(new Insets(-140, 0, 58, 0));
			
			// Label for currentscore
			_currentScore = new Label("Current Score: " + NumberList.getSessionScore() + "/" + NumberList.getNumberAnswered());
			_currentScore.setTextAlignment(TextAlignment.CENTER);
			_currentScore.setFont(App.getRegFont());
			_currentScore.setScaleX(2);
			_currentScore.setScaleY(2);
			
			// Set up record & cancel buttons
			_recordButton = new Button("Record Answer");
			_recordButton.setScaleX(2);
			_recordButton.setScaleY(2);
			_recordButton.setFont(App.getRegFont());
			_cancelButton = new Button("Main Menu");
			_cancelButton.setScaleX(2);
			_cancelButton.setScaleY(2);
			_cancelButton.setFont(App.getRegFont());
			
			_hearRecording = new Button("Hear Recording");
			_hearRecording.setScaleX(2);
			_hearRecording.setScaleY(2);
			_hearRecording.setFont(App.getRegFont());
			_submit = new Button("Submit");
			_submit.setScaleX(2);
			_submit.setScaleY(2);
			_submit.setFont(App.getRegFont());
			setUpAction();
			
			// Set up Vbox and add children
			setAlignment(Pos.CENTER);
			setSpacing(40);

			
			getChildren().add(_numberLabel);
			getChildren().add(_recordButton);
			getChildren().add(_cancelButton);
			getChildren().add(_currentScore);
	}
	
	/**
	 * Sets up the event handler for the record button
	 */
	private void setUpAction() {

		// Set up action for record button
		_recordButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Disable buttons while recording
				_recordButton.setDisable(true);
				_cancelButton.setDisable(true);
				_submit.setDisable(true);
				_hearRecording.setDisable(true);
				
				// Create service to run the bash process
				Service<Void> service = new Service<Void>() {

					@Override
					protected Task<Void> createTask() {
						return new Task<Void>() {

							@Override
							protected Void call() throws Exception {
								// Gets the user's speech
								_userAttempt = BashProcess.getInstance().recordAndRetrieve();
								System.out.println(_userAttempt);
								return null;
								
							}
							
						};
					}
					
					@Override
					protected void succeeded() {
						
						_recordButton.setText("Re-Record");
						// Changes order of buttons
						if (!getChildren().contains(_submit)) {
							getChildren().remove(_cancelButton);
							getChildren().remove(_currentScore);
							getChildren().add(_hearRecording);
							getChildren().add(_submit);
							getChildren().add(_cancelButton);
							getChildren().add(_currentScore);
						}
						_numberLabel.setPadding(new Insets(-42, 0, 20, 0));
						// Makes buttons clickable again
						_recordButton.setDisable(false);
						_cancelButton.setDisable(false);
						_submit.setDisable(false);
						_hearRecording.setDisable(false);
					}
					
				};
				
				// Start service
				service.start();
			
			
			}

		});
		
		// Set up action for cancel button to take user back to main menu
		_cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				App.getMainStage().setScene(new Scene(new MainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
		
		// Set up action for hear recording
		_hearRecording.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Disable buttons while playing
				_recordButton.setDisable(true);
				_cancelButton.setDisable(true);
				_submit.setDisable(true);
				_hearRecording.setDisable(true);
				
				Service<Void> service = new Service<Void>() {

					@Override
					protected Task<Void> createTask() {
						return new Task<Void>() {

							@Override
							protected Void call() throws Exception {
								
								
								BashProcess.getInstance().hearRecording();								
								
								return null;
							}
							
						};
					}

					@Override
					protected void succeeded() {
						_recordButton.setDisable(false);
						_cancelButton.setDisable(false);
						_submit.setDisable(false);
						_hearRecording.setDisable(false);
					}
					
				};
				
				service.start();
			}
			
		});
		
		
		// Set up action for submit button
		_submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Once recording is finished, checks user's speech against answer
				if (_userAttempt.equals(MaoriNumbers.getMaoriPronunciation(_number))) { 
					
					// User gets question correct	
					App.getMainStage().setScene(new Scene(new QuestionResult(true,_secondAttempt,_number),App.APP_WIDTH,App.APP_HEIGHT));
					
				} else {
					
					//User gets question wrong
					App.getMainStage().setScene(new Scene(new QuestionResult(false,_secondAttempt,_number),App.APP_WIDTH,App.APP_HEIGHT));
					
				}
			}
			
		});
	}
	
	
	/**
	 * Getter method that returns the users attempted Maori pronunciation.
	 * 
	 * @return A String representing the user's Maori pronunciation attempt.
	 */
	public static String getUserAttempt() {
		return _userAttempt;
	}
	
}
