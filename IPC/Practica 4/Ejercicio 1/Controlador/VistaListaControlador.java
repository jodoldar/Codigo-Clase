
package Controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modelo.Persona;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Josep Dols
 */
public class VistaListaControlador implements Initializable{
    @FXML 
    private ListView<Persona> vistadeListafxID;
    //@FXML 
    //private TextField textFieldfxID;
    //@FXML 
    //private TextField textFieldApellidofxID;
    @FXML 
    private Button BAddfxID;
    @FXML 
    private Button BBorrarfxID;
    @FXML 
    private Button Modify;
    
    /*@FXML void addAccion(ActionEvent event) {
        // añade a la colección si los campos no son vacíos y no contienen únicamente blancos
         if ((!textFieldfxID.getText().isEmpty())
        	&& (textFieldfxID.getText().trim().length()!=0)
        	&& (!textFieldApellidofxID.getText().isEmpty())
        	&& (textFieldApellidofxID.getText().trim().length()!=0))
         { 
           datos.add(new Persona(textFieldfxID.getText(),textFieldApellidofxID.getText()));
           textFieldfxID.clear();
           textFieldApellidofxID.clear();
           textFieldfxID.requestFocus();  //cambio del foco al textfield.
        	 
         } 
    }*/
    
    @FXML void addAction(ActionEvent event){
        try{
            Stage actualStage = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vista/Vista2.fxml"));
            VBox root = (VBox) cargador.load();
            cargador.<Vista2Controller>getController().initPersona(null, -1);
            cargador.<Vista2Controller>getController().initData(datos);
            
            Scene scene = new Scene(root);
            actualStage.setScene(scene);
            actualStage.initModality(Modality.APPLICATION_MODAL);
            cargador.<Vista2Controller>getController().initStage(actualStage);
            actualStage.show();
        }catch(IOException e){
            System.err.println("Error al crear la nueva ventana");
        }
    }
    @FXML void editAction(ActionEvent event){
        try{
            Stage actualStage = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vista/Vista2.fxml"));
            VBox root = (VBox) cargador.load();
            
            cargador.<Vista2Controller>getController().initPersona(vistadeListafxID.getSelectionModel().getSelectedItem(),vistadeListafxID.getSelectionModel().getSelectedIndex());
            cargador.<Vista2Controller>getController().initData(datos);
            
            Scene scene = new Scene(root);
            actualStage.setScene(scene);
            actualStage.initModality(Modality.APPLICATION_MODAL);
            cargador.<Vista2Controller>getController().initStage(actualStage);
            actualStage.show();
        }catch(IOException e){
            System.err.println("Error al crear la nueva ventana");
        }
    }

    @FXML void borrarAccion(ActionEvent event) {
    	int i = vistadeListafxID.getSelectionModel().getSelectedIndex();
    	if (i>=0) datos.remove(i);
    }
    
    public ObservableList<Persona> datos = null; // Colección vinculada a la vista.
    
    public void initData(ObservableList data){
        this.datos = data;
        vistadeListafxID.setItems(datos);
        vistadeListafxID.setCellFactory(c -> new PersonListCell());
    }
    
    public ObservableList getPersons() {
        return datos;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        ArrayList<Persona> misdatos = new ArrayList<>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        datos = FXCollections.observableArrayList(misdatos);
        vistadeListafxID.setItems(datos); // vinculación entre la vista y el modelo
        vistadeListafxID.setCellFactory(c -> new PersonListCell());
        // inhabilitar botones al arrancar.
        BAddfxID.setDisable(false);
        BBorrarfxID.setDisable(true);
        Modify.setDisable(true);
        
        // oyente de foco para el listView
        vistadeListafxID.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (vistadeListafxID.isFocused()) {
                    BBorrarfxID.setDisable(false);
                    Modify.setDisable(false);
                }						
            }			
        });
    }
}

class PersonListCell extends ListCell<Persona>{
    @Override
    protected void updateItem(Persona item,boolean empty){
        super.updateItem(item, empty);
        if(item==null || empty){
            setText(null);
        }else{
            setText(item.getNombre() + ", " + item.getApellidos());
        }
    }
}
