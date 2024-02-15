package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage pantallaInicio) throws Exception {
        // Cargar la interfaz de usuario desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
        Parent root = loader.load();

        // Configurar la ventana principal
        pantallaInicio.setTitle("Rateflix");
        pantallaInicio.setScene(new Scene(root, 600, 600));
        pantallaInicio.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
