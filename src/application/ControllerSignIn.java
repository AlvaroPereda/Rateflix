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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControllerSignIn {
	
	@FXML
	private TextField userEmail,userNameText;
	@FXML
	private PasswordField userPassword;
	@FXML
	private Label error,userNameLabel;
	@FXML
    private AnchorPane inicioAnchorPane;
	
	
	private static int SQLidusername;
	private static String SQLemail;
	private static String SQLpassword;
	private static String SQLusername;
	
	private Boolean signIn=true;
	static Connection connection = null;
	static String url = "jdbc:mysql://localhost:3306/rateflix?serverTimezone=UTC";
	static String usernameGlobal = "admin";
	static String passwordGlobal = "admin";
	
	@FXML
	public void acceptButton(ActionEvent event) throws SQLException {
		if(signIn) {
			if(userEmail.getText().isBlank()==true	|| userPassword.getText().isBlank()==true	|| userNameText.getText().isBlank()==true) {
				error.setText("Please enter name, email and password"); 
			}
			else {				
				guardarCredenciales(userNameText.getText(),userEmail.getText(), userPassword.getText());
				saveData();
				openWindow();
			}
		}
		else {
			if(userEmail.getText().isBlank()==true	|| userPassword.getText().isBlank()==true) {
				error.setText("Please enter email and password"); 
			}
			else if(comprobarCredenciales(userEmail.getText(), userPassword.getText())) {
				saveData();
				openWindow();
			}
			else {
				error.setText("Email or password are not correct");
			}
		}
		
	}
	
	private void guardarCredenciales(String username, String email, String password) throws SQLException {
		connection = DriverManager.getConnection(url,usernameGlobal,passwordGlobal);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `data` (`email`, `password`, `username`) VALUES ('"+email+"', '"+password+"', '"+username+"');");
		ps.executeUpdate();
    }
	 private boolean comprobarCredenciales(String email, String password) {
		 try {
	            connection = DriverManager.getConnection(url, usernameGlobal, passwordGlobal);
	            String sql = "SELECT * FROM data WHERE email = ? AND password = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, email);
	                statement.setString(2, password);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        return true;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	private void saveData() {
		SQLemail = userEmail.getText();
		SQLpassword = userPassword.getText();
		
		String sql = "SELECT idusername, username FROM data WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail.getText());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    SQLidusername = resultSet.getInt("idusername");
                    SQLusername = resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		
	}
	private void openWindow() {
		try {       
	        Stage pantallaSignInStage = (Stage) inicioAnchorPane.getScene().getWindow();
	        pantallaSignInStage.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			Stage inicioStage = new Stage();

			inicioStage.setTitle("Rateflix");
			inicioStage.setScene(scene);

			
			inicioStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setInvisible() {
		userNameText.setVisible(false);
		userNameLabel.setVisible(false);
	}
	public void setLoginIn() {
		this.signIn = false;
	}
	public int getSQLidusername() {
		return SQLidusername;
	}
	public String getSQLemail() {
		return SQLemail;
	}
	public String getSQLpassword() {
		return SQLpassword;
	}
	public String getSQLusername() {
		return SQLusername;
	}
}
