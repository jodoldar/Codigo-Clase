
package Aplicacion;

import Controlador.VistaListaControlador;
import Modelo.Persona;
import java.beans.Visibility;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Josep Dols
 */
public class ListViewEjemplo1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaLista.fxml"));
        Parent root = loader.load();
        VistaListaControlador controller = loader.<VistaListaControlador>getController();
        ArrayList<Persona> listaAux = new ArrayList<>();
        listaAux.add(new Persona("Pepe", "García"));
        listaAux.add(new Persona("María", "Pérez"));
        ObservableList<Persona> data = FXCollections.observableArrayList(listaAux);
        controller.initData(data);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Demo vista de lista");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
