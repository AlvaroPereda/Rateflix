package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ControllerInicio {
	
	@FXML
	private Label name;
	
	
	
	
	public void initialize() {
		
		ControllerSignIn data = new ControllerSignIn();
		
		System.out.println(data.getIdusername());
		
	}

}
