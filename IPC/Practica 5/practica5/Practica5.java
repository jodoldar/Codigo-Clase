
package practica5;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Josep Dols
 */
public class Practica5 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Seleccionar el locale por defecto
        Locale locale = Locale.getDefault();
        // Cargar el bundle (p.e., strings_es_ES.properties)
        ResourceBundle bundle = ResourceBundle.getBundle("practica5.strings", locale);
        // Pasar el bundle al FXMLLoader
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"), bundle);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
