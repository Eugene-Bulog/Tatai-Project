package main;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Login extends VBox {
	
	private final Label TITLE;
	private final Label ACCENT;
	private final Label SUBTITLE;
	private final TextField NAME;
	private final Button CONTINUE;
	private final Label INVALID_NAME;

	
	public Login() {
		
		// Set up background image
		setBackground(App.getPatternBackground());
		

		// Set up accent for title
		ACCENT = new Label("-     ");
		ACCENT.setFont(App.getMaoriFont());
		ACCENT.setTextFill(Color.web("#964B00"));
		ACCENT.setPadding(new Insets(-307, 0, 0, 0));

		
		// Set up title and subtitle
		TITLE = new Label("TATAI!");
		TITLE.setFont(App.getMaoriFont());
		TITLE.setTextFill(Color.web("#964B00"));
		TITLE.setPadding(new Insets(-259, 0, 0, 0));
		SUBTITLE = new Label("Please enter your name : ");
		SUBTITLE.setScaleX(1.5);
		SUBTITLE.setScaleY(1.5);
		SUBTITLE.setFont(App.getRegFont());
		
		// Set up name textfield
		NAME = new TextField();
		NAME.setMaxWidth(200);
		NAME.setScaleX(1.5);
		NAME.setScaleY(1.5);
		
		// Set up continue button
		CONTINUE = new Button("Continue");
		CONTINUE.setScaleX(2);
		CONTINUE.setScaleY(2);
		CONTINUE.setFont(App.getRegFont());
		setUpActions();
		
		
		// Set up warning label for invalid name
		INVALID_NAME = new Label("");
		INVALID_NAME.setScaleX(1.5);
		INVALID_NAME.setScaleY(1.5);
		INVALID_NAME.setTextFill(Color.RED);

		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		setSpacing(40);
		getChildren().add(ACCENT);
		getChildren().add(TITLE);
		getChildren().add(SUBTITLE);
		getChildren().add(NAME);
		getChildren().add(CONTINUE);
		getChildren().add(INVALID_NAME);

		
		
	}
	
	/**
	 * Sets up action listeners for all buttons in this scene
	 */
	private void setUpActions() {
		
		//Set up actions for continue button
		CONTINUE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Checks that something has been entered
				if (NAME.getText() != null && NAME.getText().matches("[\\w ]*") && !NAME.getText().trim().isEmpty()) {
					// Goes to the main menu & logs in
					App.setName(NAME.getText());
					utility.SaveData.initSave();
					utility.SaveData.login();
					App.getMainStage().setScene(new Scene(new MainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
				} else {
					
					INVALID_NAME.setText("   Please enter a valid name! Letters, numbers, and spaces only!");
					
				}
			}
			
		});
	}

}
