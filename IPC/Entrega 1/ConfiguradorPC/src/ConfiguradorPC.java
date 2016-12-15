


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
public class ConfiguradorPC extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Locale local = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("configuradorpc.strings", local);
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/FXMLDocument.fxml"),rb);
        
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
