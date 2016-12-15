/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Configuracion;
import Modelos.Producto;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josep Dols
 */
public class PresupuestoFinalController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Label fecha;
    @FXML
    private Label subtotal;
    @FXML
    private Label iva;
    @FXML
    private Label total;
    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TableColumn<Producto,String> tablaComponente;
    @FXML
    private TableColumn<Producto,Double> tablaPrecio;
    @FXML
    private TableColumn<Producto,Integer> tablaCantidad;
    @FXML
    private TableColumn<Producto,Double> tablaTotal;

    private Stage segundaVentana;
    public ObservableList<Producto> miLista = null;
    private Configuracion config;
    private ResourceBundle bundle;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
    }    
    
    void initStage(Stage actualStage,ObservableList data,Configuracion lista) {
        segundaVentana = actualStage;
        segundaVentana.setTitle("Presupuesto");
        this.miLista = data;
        this.config = lista;
        
        subtotal.setText(String.format("%.2f", config.getSubtotal())+" €");
        iva.setText(String.format("%.2f", config.getIva())+" €");
        total.setText(String.format("%.2f",config.getTotal())+" €");
        fecha.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + 
                (Calendar.getInstance().get(Calendar.MONTH)+1) + "/" +
                Calendar.getInstance().get(Calendar.YEAR));
        
        tablaComponente.setCellValueFactory(celda1 -> new SimpleStringProperty(celda1.getValue().getDescripcion()));
        tablaPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        tablaCantidad.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        tablaTotal.setCellValueFactory(new PropertyValueFactory<Producto,Double>("total"));
        
        tabla.setItems(miLista);
        
        if(config.getNombre()==null || "".equals(config.getNombre())){
            this.nombre.setText("Sin nombre");
        }else{
            this.nombre.setText(config.getNombre()); 
        }
    }
    
    public void initData(ObservableList data){
        this.miLista = data;
    }
    public void initConfig(Configuracion lista){
        this.config = lista;
    }
}
