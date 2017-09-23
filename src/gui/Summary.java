package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import utility.NumberList;

public class Summary extends VBox{

	// The list which will summarize all the questions and answers this session
	private ListView<String> _answerList = new ListView<String>();
	private ObservableList<String> _questions = FXCollections.observableArrayList();
	
	/**
	 * Constructor for the summary VBox, sets up the summary list
	 */
	public Summary() {
		
		// Adds each question and answer to the list
		for (int i = 0; i < 10; i++) {
			_questions.add(NumberList.getNumberAt(i) + ": " + 
					NumberList.getAnswerAt(i));
		}
		
		// Adds the ObservableList to the ListView
		_answerList.setItems(_questions);
		// Adds the ListView to the VBox
		getChildren().add(_answerList);
		
	}
}
