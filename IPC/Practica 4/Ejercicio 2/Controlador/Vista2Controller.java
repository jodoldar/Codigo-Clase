
package Controlador;


import Modelo.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josep Dols
 */
public class Vista2Controller implements Initializable {

    private Stage segundaVentana;
    //private boolean modifica = false;
    private int position;
    private VistaListaControlador mainController;
    private ObservableList<Persona> data;
    
    @FXML private TextField nombre;
    @FXML private TextField apellido;
    @FXML private Button cancel;
    @FXML private Button accept;
    
    public void initStage(Stage ventana){
        segundaVentana = ventana;
        segundaVentana.setTitle("Propiedades");
    }
    
    public void initData(ObservableList data){
        this.data = data;
    }
    
    public void initPersona(Persona p, int pos){
        if(p != null) {
            nombre.setText(p.getNombre());
            apellido.setText(p.getApellidos());
            this.position = pos;
        } else {
            this.position = -1;
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vista/VistaLista.fxml"));
        try{
            cargador.load();
            this.mainController = cargador.<VistaListaControlador>getController();
        } catch (IOException ex) {
            Logger.getLogger(Vista2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML 
    public void cancelAction(ActionEvent evento){
        Node nodo1 = (Node)evento.getSource();
        nodo1.getScene().getWindow().hide();
    }
    
    @FXML
    public void acceptAction(ActionEvent evento){
        Persona nueva = new Persona(nombre.getText(),apellido.getText());
        if(position == -1){
            data.add(nueva);
        }else{
            data.set(position, nueva);
        }        
        mainController.initData(data);
        Node nodo1 = (Node)evento.getSource();
        nodo1.getScene().getWindow().hide();
        //nodo1.setUserData(nueva);
    }
}
