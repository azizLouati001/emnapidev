
package projet;
import entities.conseil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Crudcons;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import gui.EspaceMedecinController;

/**
 *
 * @author Lenovo
 */

public class Projet extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/EspaceMedecin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bienvenue à HealthHaven !");
        stage.show();
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}