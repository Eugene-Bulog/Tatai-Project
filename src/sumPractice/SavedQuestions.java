package sumPractice;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.App;
import utility.SaveData;

public class SavedQuestions extends VBox{

	
	private final Button MATHS_MENU;
	private final Button CREATE_NEW;
	private ObservableList<String> _obsList;
	private final ListView<String> LIST_VIEW;
	private final Button PLAY;
	private final Button DELETE;
	private final Alert CONFIRM_DELETE;
	
	
	public SavedQuestions() {
		// Set up background image
		setBackground(App.getPatternBackground());
		
		
		// confirmation dialog
		CONFIRM_DELETE = new Alert(AlertType.CONFIRMATION);
		CONFIRM_DELETE.setTitle("Confirm");
		CONFIRM_DELETE.setHeaderText("");
		CONFIRM_DELETE.setContentText("Are you sure you wish to delete this list?");
		
		// Set up list of existing custom lists
		_obsList = FXCollections.observableArrayList(utility.SaveData.getCustomNames());
		LIST_VIEW = new ListView<String>(_obsList);
		LIST_VIEW.setMaxHeight(250);
		
		// Set cell factory to allow for setting font and alignment etc
		LIST_VIEW.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				return new ListCell<String>() {
					@Override 
					protected void updateItem(String item, boolean empty) {
						// Call overridden method
						super.updateItem(item, empty);

						// Custom style 
						setText(item);
						setFont(App.getRegFontMed());
						setAlignment(Pos.CENTER);
					}
				};
			};

		});
		
		
		// delete selected button
		DELETE = new Button("Delete Selected List");
		DELETE.setScaleX(2);
		DELETE.setScaleY(2);
		DELETE.setFont(App.getRegFont());
		
		
		// play selected button
		PLAY = new Button("Play Selected List");
		PLAY.setScaleX(2);
		PLAY.setScaleY(2);
		PLAY.setFont(App.getRegFont());
		
		// create new list button
		CREATE_NEW = new Button("Create New List");
		CREATE_NEW.setScaleX(2);
		CREATE_NEW.setScaleY(2);
		CREATE_NEW.setFont(App.getRegFont());
		
		// return to menu button
		MATHS_MENU = new Button("Return to Maths Menu");
		MATHS_MENU.setScaleX(2);
		MATHS_MENU.setScaleY(2);
		MATHS_MENU.setFont(App.getRegFont());

		
		setUpActions();
		
		setAlignment(Pos.CENTER);
		setSpacing(40);
		getChildren().addAll(LIST_VIEW,PLAY,DELETE,CREATE_NEW,MATHS_MENU);
		
	}

	
	
	/**
	 * Sets up action listeners for all buttons in this scene
	 */
	private void setUpActions() {
		//Set up actions for return to menu button
		MATHS_MENU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				App.getMainStage().setScene(new Scene(new SumMainMenu(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
		
		
		//Set up actions for create new button
		CREATE_NEW.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				App.getMainStage().setScene(new Scene(new CreateList(), App.APP_WIDTH, App.APP_HEIGHT));
			}
			
		});
		
		
		//Set up actions for play selected button
		PLAY.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				// Gets the selected list name
				String selected = LIST_VIEW.getSelectionModel().getSelectedItem();
				
				if (selected != null) {
					// launches playing the selected list
					SaveData.playCustomList(selected);
					App.getMainStage().setScene(new Scene(new QuestionAsk(), App.APP_WIDTH, App.APP_HEIGHT));
				}
			}
		
		});
		
		
		//Set up actions for play selected button
		DELETE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				// Gets the selected list name
				String selected = LIST_VIEW.getSelectionModel().getSelectedItem();
				
				if (selected != null) {
					// opens confirm dialog
					Optional<ButtonType> result = CONFIRM_DELETE.showAndWait();
					if (result.get() == ButtonType.OK){
						// deletes the selected list
						SaveData.deleteCustom(selected);
						_obsList.remove((String)(selected));
					}
					
				}
			}
			
		});
		
	}
	
	
	
	
}
