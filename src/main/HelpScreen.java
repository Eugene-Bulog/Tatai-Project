package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sun.applet.Main;

public class HelpScreen extends VBox{

	private final Label TITLE;
	private final Label SUBTITLE;
	private final Label ACCENT;
	private final Button MAIN_MENU;
	private final Text INFO;

	
	public HelpScreen() {
		
		// Set up background image
		setBackground(App.getPatternBackground());
		
		// Set up title
		TITLE = new Label("TATAI!");
		TITLE.setFont(App.getMaoriFont());
		TITLE.setTextFill(Color.web("#964B00"));
		TITLE.setPadding(new Insets(-200, 0, 0, 0));
		
		// Set up accent for title
		ACCENT = new Label("-     ");
		ACCENT.setFont(App.getMaoriFont());
		ACCENT.setTextFill(Color.web("#964B00"));
		ACCENT.setPadding(new Insets(-200, 0, 0, 0));

		// Set up HELP subtitle
		SUBTITLE  = new Label("HELP");
		SUBTITLE.setFont(App.getRegFontLarge());
		SUBTITLE.setTextFill(Color.web("#964B00"));
	
		MAIN_MENU = new Button("Return to Main Menu");
		MAIN_MENU.setScaleX(2);
		MAIN_MENU.setScaleY(2);
		
		INFO = new Text();
		INFO.setText("This page will be implemented by the final submission\n");
		INFO.setFont(App.getRegFontMed());
		INFO.setFill(Color.web("#964B00"));
		setAlignment(Pos.CENTER);
		setSpacing(40);
		
		getChildren().addAll(ACCENT,TITLE,SUBTITLE,INFO,MAIN_MENU);
		
		setUpActions();
		
	}


	private void setUpActions() {
		
		// Event handler for help button
		MAIN_MENU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				// Move to help screen
				App.getMainStage().setScene(new Scene(new MainMenu(),App.APP_WIDTH,App.APP_HEIGHT));
			}
			
		});
		
	}
}
