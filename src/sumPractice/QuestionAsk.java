package sumPractice;

import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import main.App;
import utility.BashProcess;
import utility.MaoriNumbers;
import utility.EquationList;

public class QuestionAsk extends VBox{

	

	
	// The number being asked for this question
	private String[] _question;
	private Label _numberLabel;
	private Label _currentScore;
	private Alert _confirmExit;
	
	// Whether or not this is the second attempt for this question
	private boolean _secondAttempt;
	
	private Button _hearRecording;
	private Button _submit;
	private Button _recordButton;
	private Button _cancelButton;
	
	// All things concerning the progress bar
	private ProgressBar _pBar;
	private Label _pBarActivity;
	private Timeline _timeline;
		
	// The user's attempted Maori pronunciation
	private static String _userAttempt = "";
	/**
	 * Constructor for QuestionAsk VBox
	 */
	public QuestionAsk() {
		
		_secondAttempt = false;
		_question = EquationList.getQuestion(); // Pop next question value and set number to this
	
		setUpGUI();
	}
	
	/**
	 * Constructor for QuestionAsk second attempt
	 * @param number: the number for which to do a second attempt
	 */
	public QuestionAsk(String[] question) {
		_secondAttempt = true;
		_question = question;
		
		setUpGUI();
	}

	private void setUpGUI() {
			setBackground(App.getPatternBackground());
		
			
			// confirmation dialog
			_confirmExit = new Alert(AlertType.CONFIRMATION);
			_confirmExit.setTitle("Confirm");
			_confirmExit.setHeaderText("");
			_confirmExit.setContentText("Are you sure you wish to return to the maths menu? Your progress for this session will be lost.");
			
			// Sets the label to the question value
			_numberLabel = new Label(_question[0]);
			_numberLabel.setFont(App.getRegFontLarge());
			_numberLabel.setTextFill(Color.web("#964B00"));
			_numberLabel.setPadding(new Insets(-140, 0, 58, 0));
			
			// Label for currentscore
			_currentScore = new Label("Current Score: " + EquationList.getSessionScore() + "/" + EquationList.getNumberAnswered());
			_currentScore.setTextAlignment(TextAlignment.CENTER);
			_currentScore.setFont(App.getRegFont());
			_currentScore.setScaleX(2);
			_currentScore.setScaleY(2);
			
			// Set up record & cancel buttons
			_recordButton = new Button("Record Answer");
			_recordButton.setScaleX(2);
			_recordButton.setScaleY(2);
			_recordButton.setFont(App.getRegFont());
			_cancelButton = new Button("Maths Menu");
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
			
			// Set up the progress bar
			_pBar = new ProgressBar(0);
			_pBar.setScaleX(2);
			_pBar.setScaleY(2);
			_pBar.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
			_pBar.setPrefSize(100, 10);
			_pBar.setStyle("-fx-accent: #964B00");
			_pBar.setVisible(false);

			// Set up the label to indicate what the progress bar represents
			_pBarActivity = new Label("");
			_pBarActivity.setTextAlignment(TextAlignment.CENTER);
			_pBarActivity.setPadding(new Insets(-30, 0, 0, 0));
			_pBarActivity.setScaleX(1.5);
			_pBarActivity.setScaleY(1.5);
			_pBarActivity.setVisible(false);
			
			// Set up Vbox and add children
			setAlignment(Pos.CENTER);
			setSpacing(40);

			
			getChildren().add(_numberLabel);
			getChildren().add(_recordButton);
			getChildren().add(_cancelButton);
			getChildren().add(_currentScore);
			getChildren().add(_pBar);
			getChildren().add(_pBarActivity);

			
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
				
				_pBarActivity.setText("Recording...");
				
				_pBar.setVisible(true);
				_pBarActivity.setVisible(true);
				
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
							getChildren().remove(_pBar);
							getChildren().remove(_pBarActivity);
							
							getChildren().add(_hearRecording);
							getChildren().add(_submit);
							getChildren().add(_cancelButton);
							getChildren().add(_currentScore);
							getChildren().add(_pBar);
							getChildren().add(_pBarActivity);
							
							_pBar.setVisible(false);
							_pBarActivity.setVisible(false);
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
			
				updateProgressBar(_pBar);

			}

		});
		
		// Set up action for cancel button to take user back to main menu
		_cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// opens confirm dialog
				Optional<ButtonType> result = _confirmExit.showAndWait();
				if (result.get() == ButtonType.OK){
					App.getMainStage().setScene(new Scene(new SumMainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
				}
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
				
				_pBarActivity.setText("Playing...");

				_pBar.setVisible(true);
				_pBarActivity.setVisible(true);
				
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
				
				updateProgressBar(_pBar);
				
			}
			
		});
		
		
		// Set up action for submit button
		_submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Once recording is finished, checks user's speech against answer
				//if (MaoriNumbers.getMaoriPronunciation(Integer.parseInt(TEMPTEXT.getText())).equals(MaoriNumbers.getMaoriPronunciation(Integer.parseInt(_question[1])))) { 
				if (_userAttempt.equals(MaoriNumbers.getMaoriPronunciation(Integer.parseInt(_question[1])))) {
					// User gets question correct	
					App.getMainStage().setScene(new Scene(new QuestionResult(true,_secondAttempt,_question),App.APP_WIDTH,App.APP_HEIGHT));
					
				} else {
					
					//User gets question wrong
					App.getMainStage().setScene(new Scene(new QuestionResult(false,_secondAttempt,_question),App.APP_WIDTH,App.APP_HEIGHT));
					
				}
			}
			
		});
	}
	
	
	/**
	 * This method is responsible for progressing the progress bar while the user is recording,
	 * as well as when the user is playing back audio.
	 * @param pbar The progress bar to be updated.
	 */
	private void updateProgressBar(ProgressBar pbar) {
		
		pbar.setProgress(0);
		
		_timeline = new Timeline();
		_timeline.setCycleCount(Timeline.INDEFINITE);

		// Every 0.01 seconds, the progress bar will be incremented.
		KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>(){

			public void handle(ActionEvent e) {
				
				
				pbar.setProgress(pbar.getProgress() + 0.0033333333);
				
				// Stop the timeline once we have completed our progress.
				if (pbar.getProgress() >= 1.0 ) {
					_timeline.stop();
					_pBarActivity.setText("Done!");
					_pBar.setVisible(false);
					_pBarActivity.setVisible(false);
				}
				
			}		
		});

		_timeline.getKeyFrames().add(keyframe);
		_timeline.playFromStart();
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
