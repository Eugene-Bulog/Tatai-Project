package sumPractice;





import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.App;
import main.MainMenu;
import utility.SaveData;

public class CreateList extends VBox{

	private String[][] _questionList = {};
	
	
	// GUI elements
	private final HBox SAVE_CANCEL_BOX;
	private final HBox ADD_BOX;
	private final TextField NUMBER1;
	private final TextField NUMBER2;
	private final Button ADD;
	private final Button SAVE_QUESTION;
	private final Button EDIT;
	private final Button DELETE;
	private final Button SAVE;
	private final Button CANCEL;
	private final Button CANCEL_ADD;
	private final ComboBox<Character> OPERATOR;
	private final Label WARNING;
	private final TextField NAME;
	private final Button CONTINUE;
	private final Label NAME_LABEL;
	
	
	public CreateList() {
		// Set up background image
		setBackground(App.getPatternBackground());
		
		// Naming a creation
		NAME_LABEL = new Label("Please enter a name for this list:");
		NAME_LABEL.setScaleX(2);
		NAME_LABEL.setScaleY(2);
		NAME_LABEL.setFont(App.getRegFont());
		
		
		// Naming a creation
		NAME = new TextField();
		NAME.setScaleX(2);
		NAME.setScaleY(2);
		NAME.setMaxWidth(200);
		NAME.setFont(App.getRegFont());
		
		// Continue button
		CONTINUE = new Button("Continue");
		CONTINUE.setScaleX(2);
		CONTINUE.setScaleY(2);
		CONTINUE.setFont(App.getRegFont());
		
		// Sets up elements for question creation
		NUMBER1 = new TextField();
		NUMBER1.setScaleX(2);
		NUMBER1.setScaleY(2);
		NUMBER1.setFont(App.getRegFont());
		NUMBER1.setMaxWidth(40);
		NUMBER2 = new TextField();
		NUMBER2.setScaleX(2);
		NUMBER2.setScaleY(2);
		NUMBER2.setFont(App.getRegFont());
		NUMBER2.setMaxWidth(40);
		OPERATOR = new ComboBox<Character>(FXCollections.observableArrayList('+','-','X','/'));
		OPERATOR.setScaleX(2.5);
		OPERATOR.setScaleY(2.5);
		OPERATOR.setMaxWidth(80);
		ADD_BOX = new HBox();
		ADD_BOX.setAlignment(Pos.CENTER);
		ADD_BOX.setSpacing(80);
		ADD_BOX.getChildren().addAll(NUMBER1,OPERATOR,NUMBER2);
		
		// add new question button
		CANCEL_ADD = new Button("Cancel Adding Question");
		CANCEL_ADD.setScaleX(2);
		CANCEL_ADD.setScaleY(2);
		CANCEL_ADD.setFont(App.getRegFont());
		
		// add new question button
		ADD = new Button("Add Question");
		ADD.setScaleX(2);
		ADD.setScaleY(2);
		ADD.setFont(App.getRegFont());
		
		// save question button
		SAVE_QUESTION = new Button("Save This Question");
		SAVE_QUESTION.setScaleX(2);
		SAVE_QUESTION.setScaleY(2);
		SAVE_QUESTION.setFont(App.getRegFont());
		
		// add new question button
		EDIT = new Button("Edit This Question");
		EDIT.setScaleX(2);
		EDIT.setScaleY(2);
		EDIT.setFont(App.getRegFont());
		
		// Delete question button
		DELETE = new Button("Delete This Question");
		DELETE.setScaleX(2);
		DELETE.setScaleY(2);
		DELETE.setFont(App.getRegFont());
		
		// cancel creation button
		SAVE = new Button("Save Creation");
		SAVE.setScaleX(2);
		SAVE.setScaleY(2);
		SAVE.setFont(App.getRegFont());
		
		// cancel creation button
		CANCEL = new Button("Cancel Creation");
		CANCEL.setScaleX(2);
		CANCEL.setScaleY(2);
		CANCEL.setFont(App.getRegFont());

		// The box containing the cancel and save buttons
		SAVE_CANCEL_BOX = new HBox();
		SAVE_CANCEL_BOX.setAlignment(Pos.CENTER);
		SAVE_CANCEL_BOX.setSpacing(120);
		SAVE_CANCEL_BOX.getChildren().addAll(CANCEL,SAVE);
		
		// The warning label
		WARNING = new Label();
		WARNING.setScaleX(2);
		WARNING.setScaleY(2);
		WARNING.setFont(App.getRegFont());
		WARNING.setTextFill(Color.RED);
		WARNING.setAlignment(Pos.CENTER);
		
		setUpActions();
		
		setAlignment(Pos.CENTER);
		setSpacing(40);
		getChildren().addAll(ADD,SAVE_CANCEL_BOX);
		
	}

	
	/**
	 * Sets up action listeners for all buttons in this scene
	 */
	private void setUpActions() {
		//Set up actions for return to menu button
		CANCEL.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Return to saved lists
				App.getMainStage().setScene(new Scene(new SavedQuestions(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
		
		
		//Set up actions for add new question button
		ADD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				getChildren().clear();
				WARNING.setText("");
				getChildren().addAll(ADD_BOX,SAVE_QUESTION,CANCEL_ADD,WARNING);
			}
			
		});
		
	
		
		// Action handlers for entering characters into NUMBER1 & 2 to ensure only numbers from 1-99 are entered
		// Code for these two handlers adapted from https://stackoverflow.com/questions/8381374/how-to-implement-a-numberfield-in-javafx-2-0
		NUMBER1.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
	         public void handle( KeyEvent t ) {
	             char ar[] = t.getCharacter().toCharArray();
	             char ch = ar[t.getCharacter().toCharArray().length - 1];
	             if (!(ch >= '0' && ch <= '9') || NUMBER1.getText().length() == 2 
	            		 || (NUMBER1.getText().length() == 0 && ch == '0')) {
	                t.consume();
	             }
	          }
	       });
		NUMBER2.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
	         public void handle( KeyEvent t ) {
	             char ar[] = t.getCharacter().toCharArray();
	             char ch = ar[t.getCharacter().toCharArray().length - 1];
	             if (!(ch >= '0' && ch <= '9') || NUMBER2.getText().length() == 2
	            		 || (NUMBER2.getText().length() == 0 && ch == '0')) {
	                t.consume();
	             }
	          }
	       });
		
		
		//Set up actions for save new question button
		SAVE_QUESTION.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				// Checks question has been fully entered
				if (NUMBER1.getText().length() > 0
						&& NUMBER2.getText().length() > 0
						&& OPERATOR.getValue() != null) {
					
					int num1 = Integer.parseInt(NUMBER1.getText());
					int num2 = Integer.parseInt(NUMBER2.getText());
					double ans = 0;
					
					// calculates answer
					switch (OPERATOR.getValue()) {
					case '+':
						ans = (double)(num1 + num2);
						break;
					case '-':
						ans = (double)(num1 - num2);
						break;
					case 'X':
						ans = (double)(num1 * num2);
						break;
					case '/':
						ans = ((double)num1) / ((double)num2);
						break;
					}

					// Checks answer is valid
					if (ans > 99 || ans < 1 || ans % 1 != 0) {
						WARNING.setText("The answer must be a whole number between 1 and 99\n(the answer is currently " + ans + ")");
					}
					else {
						// Adds question to list and returns to list view
						String[] question = {num1 + " " + OPERATOR.getValue() + " " + num2, Integer.toString((int)ans)};
						_questionList[_questionList.length] = question;
						getChildren().clear();
						getChildren().addAll(ADD,SAVE_CANCEL_BOX);
					}
					
				}
				else {
					WARNING.setText("Please finish adding the question or click cancel");
				}
			}
			
		});
		
		
		// Action listener for cancel adding question button
		CANCEL_ADD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
	        	 getChildren().clear();
	        	 getChildren().addAll(ADD,SAVE_CANCEL_BOX);
	          }
	       });
		
		
		// Action listener for save question list button
		SAVE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
	        	 getChildren().clear();
	        	 WARNING.setText("");
	        	 getChildren().addAll(NAME_LABEL,NAME,CONTINUE,CANCEL,WARNING);
	          }
	       });
		
		
		// Action listener for save continue button
		CONTINUE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Checks that name is valid
				if (NAME.getText() != null && NAME.getText().matches("[\\w ]*") && !NAME.getText().trim().isEmpty()) {
					// Attempts to save list
					if (SaveData.saveQuestionList(NAME.getText(), _questionList)) {
						// Return to saved lists
						App.getMainStage().setScene(new Scene(new SavedQuestions(), App.APP_WIDTH, App.APP_HEIGHT));
					}
					else {
						WARNING.setText("A list of this name already exists, please choose another name!");
					}
				} else {
					WARNING.setText("Please enter a valid name! Letters, numbers, and spaces only!");
				}
	          }
	       });
		
	}
	

	
}
