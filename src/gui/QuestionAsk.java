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
import utility.BashProcess;
import utility.MaoriNumbers;
import utility.NumberList;

public class QuestionAsk extends VBox{

	// The number being asked for this question
	private int _number;
	private Label _numberLabel;
	private BashProcess bash = BashProcess.getInstance();
	
	// Whether or not this is the second attempt for this question
	private boolean _secondAttempt;
	
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
			_numberLabel.setPadding(new Insets(-200, 0, 58, 0));
			
			// Set up record & cancel buttons
			_recordButton = new Button("Record Answer");
			_recordButton.setScaleX(2);
			_recordButton.setScaleY(2);
			_recordButton.setFont(App.getRegFont());
			_cancelButton = new Button("Main Menu");
			_cancelButton.setScaleX(2);
			_cancelButton.setScaleY(2);
			_cancelButton.setFont(App.getRegFont());
			setUpAction();
			
			// Set up Vbox and add children
			setAlignment(Pos.CENTER);
			setSpacing(40);

			getChildren().add(_numberLabel);
			getChildren().add(_recordButton);
			getChildren().add(_cancelButton);
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
				
				// Create service to run the bash process
				Service<Void> service = new Service<Void>() {

					@Override
					protected Task<Void> createTask() {
						return new Task<Void>() {

							@Override
							protected Void call() throws Exception {
								// Gets the user's speech
								_userAttempt = bash.recordAndRetrieve();
								System.out.println(_userAttempt);
								return null;
								
							}
							
						};
					}
					
					@Override
					protected void succeeded() {
						// Once recording is finished, checks user's speech against answer
						if (_userAttempt.equals(MaoriNumbers.getMaoriPronunciation(_number))) { 
							
							// User gets question correct	
							App.getMainStage().setScene(new Scene(new QuestionResult(true,_secondAttempt,_number),App.APP_WIDTH,App.APP_HEIGHT));
							
						} else {
							
							//User gets question wrong
							App.getMainStage().setScene(new Scene(new QuestionResult(false,_secondAttempt,_number),App.APP_WIDTH,App.APP_HEIGHT));
							
						}
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
