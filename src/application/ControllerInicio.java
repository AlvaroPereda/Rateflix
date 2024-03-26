package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerInicio {
	
	@FXML
	private Label name;
		
	public void initialize()  {
		ControllerSignIn data = new ControllerSignIn();
		
		name.setText(data.getSQLusername());
	}

}
