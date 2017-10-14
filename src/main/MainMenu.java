package main;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import numberPractice.NumberMainMenu;

public class MainMenu extends VBox{

	public MainMenu() {
		App.getMainStage().setScene(new Scene(new NumberMainMenu(), App.APP_WIDTH,App.APP_HEIGHT));
	}
	
}
