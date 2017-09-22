package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utility.NumberList;

public class QuestionAsk extends VBox{

	// The number being asked for this question
	private int _number;
	private Label _numberLabel;
	private Button _recordButton;
	
	public QuestionAsk() {
		// Pops the next question and sets the label to that value
		_number = NumberList.getNumber();
		_numberLabel = new Label(Integer.toString(_number));
		
		// Set up record button
		_recordButton = new Button("Record Answer");
		_recordButton.setScaleX(2);
		_recordButton.setScaleY(2);
		
		// Set up Vbox and add children
		setAlignment(Pos.CENTER);
		setSpacing(80);
		
		getChildren().add(_numberLabel);
		getChildren().add(_recordButton);
		
	}
	
}
