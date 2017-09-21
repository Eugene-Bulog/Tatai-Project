package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {

	// Initialize buttons
	private final Button EASY_BUTTON;
	private final Button HARD_BUTTON;
	
	public MainMenu() {
		// Set up buttons
		EASY_BUTTON = new Button("Practise 1-9");
		HARD_BUTTON = new Button("Practise 1-99");
		EASY_BUTTON.setScaleX(2);
		EASY_BUTTON.setScaleY(2);
		HARD_BUTTON.setScaleX(2);
		HARD_BUTTON.setScaleY(2);
		
		// Set up Vbox and children
		setAlignment(Pos.CENTER);
		setSpacing(80);
		
		getChildren().add(EASY_BUTTON);
		getChildren().add(HARD_BUTTON);
	}
	
}
