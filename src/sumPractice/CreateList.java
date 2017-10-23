package sumPractice;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.App;

public class CreateList extends VBox{

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
	
	
	public CreateList() {
		// Set up background image
		setBackground(App.getPatternBackground());
		
		NUMBER1 = new TextField();
		NUMBER1.setScaleX(2);
		NUMBER1.setScaleY(2);
		NUMBER1.setFont(App.getRegFont());
		NUMBER2 = new TextField();
		NUMBER2.setScaleX(2);
		NUMBER2.setScaleY(2);
		NUMBER2.setFont(App.getRegFont());
		ADD_BOX = new HBox();
		ADD_BOX.setAlignment(Pos.CENTER);
		ADD_BOX.getChildren().addAll(NUMBER1,NUMBER2);
		
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
		SAVE_CANCEL_BOX.setSpacing(40);
		SAVE_CANCEL_BOX.getChildren().addAll(CANCEL,SAVE);
		
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
				
				App.getMainStage().setScene(new Scene(new SavedQuestions(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
	}
	
}
