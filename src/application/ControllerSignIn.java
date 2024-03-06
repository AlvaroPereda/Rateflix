package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ControllerSignIn {
	
	@FXML
	private TextField userEmail,userNameText;
	@FXML
	private PasswordField userPassword;
	@FXML
	private Label error,userNameLabel;
	
	private Boolean signIn=true;
	
	@FXML
	public void acceptButton(ActionEvent event) {
		if(signIn) {
			if(userEmail.getText().isBlank()==true	|| userPassword.getText().isBlank()==true	|| userNameText.getText().isBlank()==true) {
				error.setText("Please enter name, email and password"); 
			}
			else {				
				guardarCredenciales(userNameText.getText(),userEmail.getText(), userPassword.getText());
			}
		}
		else {
			if(userEmail.getText().isBlank()==true	|| userPassword.getText().isBlank()==true) {
				error.setText("Please enter email and password"); 
			}
			else if(comprobarCredenciales(userEmail.getText(), userPassword.getText())) {
				error.setText("Welcome"); 
			}
			else {
				error.setText("Email or password are not correct");
			}
		}
	}
	
	private void guardarCredenciales(String name, String email, String password) {
        try {
            FileWriter writer = new FileWriter("base_datos.txt", true); // "true" para añadir al final del archivo
            writer.write("Name: " + name + "\n");
            writer.write("Email:" + email);
            writer.write("Password:" + password + "\n\n");
            writer.close();
            System.out.println("Credenciales guardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar las credenciales: " + e.getMessage());
        }
    }
	 private boolean comprobarCredenciales(String email, String password) {
	        try {
	            File file = new File("base_datos.txt");
	            Scanner scanner = new Scanner(file);

	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                if (line.contains("Email:" + email) && line.contains("Password:" + password)) {
	                    scanner.close();
	                    return true;
	                }
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("El archivo no existe o no se puede acceder a él: " + e.getMessage());
	        }

	        return false;
	    }
	
	public void setInvisible()
	{
		userNameText.setVisible(false);
		userNameLabel.setVisible(false);
	}
	public void setLoginIn() {
		this.signIn = false;
	}
	
}
