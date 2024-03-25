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
	
	
	private static int idusername;
	private Boolean signIn=true;
	static Connection connection = null;
	static String databaseName = "rateflix";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=UTC";
 
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
		
		String sql = "SELECT idusername FROM data WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail.getText());

            // Ejecutar la consulta SQL
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idusername = resultSet.getInt("idusername");
                }
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
		
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
	
	private void guardarCredenciales(String username, String email, String password) throws SQLException {

		connection = DriverManager.getConnection(url,usernameGlobal,passwordGlobal);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `data` (`email`, `password`, `username`) VALUES ('"+email+"', '"+password+"', '"+username+"');");
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Database was connection");
			System.out.println("Record was INSERTED");
		}
    }
	 private boolean comprobarCredenciales(String email, String password) {
		 try {
	            // Establecer la conexión
	            connection = DriverManager.getConnection(url, usernameGlobal, passwordGlobal);

	            // Consulta SQL para verificar las credenciales
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
	
	public void setInvisible()
	{
		userNameText.setVisible(false);
		userNameLabel.setVisible(false);
	}
	public void setLoginIn() {
		this.signIn = false;
	}
	public int getIdusername() {
		return idusername;
	}
}
