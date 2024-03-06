package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ControllerAcceso {
	
	@FXML
    private AnchorPane accessAnchorPane;
	
	@FXML
	public void ButtonLogin(ActionEvent event) {
		try {
		       
	        Stage pantallaInicioStage = (Stage) accessAnchorPane.getScene().getWindow();
	        pantallaInicioStage.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaSignIn.fxml"));
			Parent root = loader.load();
			ControllerSignIn controllerSignIn = loader.getController();
			controllerSignIn.setInvisible();
			controllerSignIn.setLoginIn();
			Scene scene = new Scene(root);
			
			Stage signInStage = new Stage();

			signInStage.setTitle("Rateflix");
			signInStage.setScene(scene);

			
			signInStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void ButtonSignIn(ActionEvent event) {
		try {
		       
	        Stage pantallaInicioStage = (Stage) accessAnchorPane.getScene().getWindow();
	        pantallaInicioStage.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaSignIn.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			Stage signInStage = new Stage();

			signInStage.setTitle("Rateflix");
			signInStage.setScene(scene);

			
			signInStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
