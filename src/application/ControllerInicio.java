package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ControllerInicio {
	
	@FXML
	public void ButtonLogin(ActionEvent event) {
		System.out.println("Esta entrando");
	}
	
	@FXML
	public void ButtonSignIn(ActionEvent event) {
		System.out.println("Se esta registrando");
	}

}
