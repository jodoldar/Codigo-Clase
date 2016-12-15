
package Controladores;

import Modelos.Configuracion;
import Modelos.Producto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Josep Dols
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MenuItem salir;
    @FXML
    private MenuItem guardar;
    @FXML
    private MenuItem cargar;
    @FXML
    private MenuItem cerrar;
    @FXML
    private MenuItem gamaBaja;
    @FXML
    private MenuItem gamaMedia;
    @FXML
    private MenuItem gamaAlta;
    @FXML
    private MenuItem gamaUltra;
    @FXML
    private MenuItem informacion;
    @FXML
    private Button añadir;
    @FXML
    private Button editar;
    @FXML
    private Button borrar;
    @FXML
    private Button add;
    @FXML
    private Button sub;
    @FXML
    private Button generar;
    @FXML
    private Label subtotal;
    @FXML
    private Label iva;
    @FXML
    private Label total;
    @FXML
    private TextField cant;
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
    
    private List<Producto> data2 = new ArrayList<Producto>();
    public ObservableList<Producto> datos = FXCollections.observableList(data2);
    private String nombre = "";
    private ResourceBundle bundle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        
        subtotal.setText("0 €");
        iva.setText("0 €");
        total.setText("0 €");
        
        tablaComponente.setCellValueFactory(celda1 -> new SimpleStringProperty(celda1.getValue().getDescripcion()));
        tablaPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("Precio"));
        tablaCantidad.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("Cantidad"));
        tablaTotal.setCellValueFactory(new PropertyValueFactory<Producto,Double>("Total"));
        
        tabla.setItems(datos);
        editar.setDisable(true);
        borrar.setDisable(true);
        
        datos.addListener(new ListChangeListener<Producto>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Producto> c) {
                double sub2 = 0;
                for(Producto product : datos){
                sub2 += product.getTotal();
                }
                subtotal.setText(String.format(Locale.US,"%.2f", sub2));
            }
        });
        
        subtotal.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                iva.setText(String.format(Locale.US,"%.2f",Double.parseDouble(subtotal.getText())*0.21));
            }
            
        });
        iva.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                total.setText(String.format(Locale.US,"%.2f", (Double.parseDouble(subtotal.getText())+Double.parseDouble(iva.getText()))));
            }
        });
        
        tabla.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(tabla.isFocused()){
                    editar.setDisable(false);
                    borrar.setDisable(false);
                }
            }
        });
    }    
    
    public void initData(ObservableList data){
        this.datos = data;
        tabla.setItems(datos);
    }
    
    public ObservableList getData(){
        return this.datos;
    }
    
    @FXML void añadirProducto(ActionEvent evento){
        try{
            Stage actualStage = new Stage();
            
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vistas/Componentes.fxml"),this.bundle);

            BorderPane root = (BorderPane) cargador.load();
            cargador.<ComponentesController>getController().initStage(actualStage);

            cargador.<ComponentesController>getController().initProduct(null, -1);
            cargador.<ComponentesController>getController().initData(datos);
            cargador.<ComponentesController>getController().initForAdd();

            Scene scene = new Scene(root);
            actualStage.setScene(scene);
            actualStage.initModality(Modality.APPLICATION_MODAL);
            //cargador.<ComponentesController>getController().initStage(actualStage);
            actualStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML void editarProducto(ActionEvent evento){
        try{
            Stage actualStage = new Stage();
            
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vistas/Componentes.fxml"),bundle);
            
            BorderPane root = (BorderPane) cargador.load();
            cargador.<ComponentesController>getController().initStage(actualStage);

            //cargador.<ComponentesController>getController().initProduct(null, -1);
            cargador.<ComponentesController>getController().initData(datos);
            cargador.<ComponentesController>getController().initForEdit(tabla.getSelectionModel().getSelectedIndex());
            
            Scene scene = new Scene(root);
            actualStage.setScene(scene);
            actualStage.initModality(Modality.APPLICATION_MODAL);
            //cargador.<ComponentesController>getController().initStage(actualStage);
            actualStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML void borrarProducto(ActionEvent evento){
        int i = tabla.getSelectionModel().getSelectedIndex();
        if(i>=0){
            datos.remove(i);
        }
    }
    
    @FXML void incrementaCantidad(ActionEvent evento){
        
        if(!tabla.getSelectionModel().isEmpty()){
            int aux = tabla.getSelectionModel().getSelectedIndex();
            Producto aux2 = datos.get(aux);
            if((aux2.getCantidad()+1)>aux2.getStock()){
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle(bundle.getString("incrementaCanitdad.Title"));
                alerta.setContentText(bundle.getString("incrementaCantidad.Content"));
                alerta.showAndWait();
            }else{
                aux2.setCantidad(aux2.getCantidad()+1);
                aux2.setTotal(aux2.getPrecio()*aux2.getCantidad());
                datos.set(aux, aux2);
            }
            tabla.setItems(datos);
        }
    }
    
    @FXML void decrementarCantidad(ActionEvent evento){
        if(!tabla.getSelectionModel().isEmpty()){
            int aux = tabla.getSelectionModel().getSelectedIndex();
            Producto aux2 = datos.get(aux);
            if(aux2.getCantidad()-1>0){
                aux2.setCantidad(aux2.getCantidad()-1);
                aux2.refreshTotal();
                datos.set(aux,aux2);
            }else{
                Alert alerta = new Alert(AlertType.WARNING);
                alerta.setTitle(bundle.getString("decrementarCantidad.Title"));
                alerta.setContentText(bundle.getString("decrementarCantidad.Content"));
                alerta.showAndWait();
            }
            tabla.setItems(datos);
        }
    }
    
    @FXML void salirAction(ActionEvent evento){
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(bundle.getString("salirAction.Title"));
        alerta.setHeaderText(bundle.getString("salirAction.Header"));
        alerta.setContentText(bundle.getString("salirAction.Content"));
        
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            System.exit(0);
        }else{
            //Do nothing
        }
    }
    
    @FXML void guardarAction(ActionEvent evento){
        if(condicionCompleta()){
            Configuracion config = new Configuracion(null,datos);
            FileChooser selector = new FileChooser();
            Stage stage = new Stage();
            File archivo = selector.showSaveDialog(stage.getOwner());
        
            if(archivo!=null){
                config.setNombre(archivo.getName());
                this.nombre = archivo.getName();
                try {
                    PrintWriter pw = new PrintWriter(archivo);
                    pw.println(config.getNombre());
                    for(Producto product:datos){
                        pw.print(product.parseOut());
                    }
                    pw.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle(bundle.getString("guardarAction.Title"));
            alerta.setHeaderText(bundle.getString("guardarAction.Header"));
            alerta.setContentText(bundle.getString("guardarAction.Content"));
            alerta.showAndWait();
        }
    }
    
    @FXML void cargarAction(ActionEvent evento){
        if(this.datos.isEmpty()){
            FileChooser selector = new FileChooser();
            Stage stage = new Stage();
            File archivo = selector.showOpenDialog(stage);
            if(archivo!=null){
                try {
                    Scanner in = new Scanner(archivo);
                    String aux = in.nextLine();
                    List<Producto> auxP = new ArrayList<Producto>();
                    while(in.hasNext()){
                        auxP.add(Producto.parseIn(in.nextLine()));
                    }
                    Configuracion conf = new Configuracion(aux, auxP);
                    this.nombre = archivo.getName();
                    this.datos.addAll(conf.getLista());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setContentText(bundle.getString("cargarAction.Content"));
            alerta.showAndWait();
        }
    }
    
    @FXML void cerrarAction(ActionEvent e){
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(bundle.getString("cerrarAction.Title"));
        alerta.setHeaderText(bundle.getString("cerrarAction.Header"));
        alerta.setContentText(bundle.getString("cerrarAction.Content"));
        alerta.showAndWait();
        
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            List<Producto> auxData2 = new ArrayList<Producto>();
            ObservableList<Producto> auxDatos = FXCollections.observableList(data2);
            this.datos.clear();
        }else{
            //Do nothing
        }
    }
    
    @FXML void generarAction(ActionEvent e){
        if(condicionCompleta()){
            try {
                Configuracion salida = new Configuracion(this.nombre, this.getData());
                Stage actualStage = new Stage();
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vistas/PresupuestoFinal.fxml"),bundle);
                BorderPane root = (BorderPane) cargador.load();
                //cargador.<PresupuestoFinalController>getController().initData(datos);
                //cargador.<PresupuestoFinalController>getController().initConfig(salida);
                cargador.<PresupuestoFinalController>getController().initStage(actualStage,this.datos,salida);

                Scene scene = new Scene(root);
                actualStage.setScene(scene);
                actualStage.initModality(Modality.APPLICATION_MODAL);
                actualStage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle(bundle.getString("generarAction.Title"));
            alerta.setHeaderText(bundle.getString("generarAction.Header"));
            alerta.setContentText(bundle.getString("generarAction.Content"));
            alerta.showAndWait();
        }
    }
    
    private boolean condicionCompleta(){
        boolean motherboard = false; boolean cpu = false;   boolean ram = false; 
        boolean gpu = false;         boolean drive = false; boolean tower = false;
        for(Producto product: datos){
            switch(product.getCategoria()){
                case MOTHERBOARD:
                    motherboard = true;
                    break;
                case CPU:
                    cpu = true;
                    break;
                case RAM:
                    ram = true;
                    break;
                case GPU:
                    gpu = true;
                    break;
                case HDD:
                    drive = true;
                    break;
                case HDD_SSD:
                    drive = true;
                    break;
                case CASE:
                    tower = true;
                    break;
            }
        }
        return motherboard && cpu && ram && gpu && drive && tower;
    }
    
    @FXML void cargarFastAction(ActionEvent evento){
        MenuItem aux = (MenuItem)evento.getSource();
        if(this.datos.isEmpty()){
            String name = "";
            switch(aux.getText()){
                case "Gama Baja": name = "GamaBaja";break;
                case "Gama Media": name = "GamaMedia";break;
                case "Gama Alta": name = "GamaAlta";break;
                case "Gama Ultra": name = "GamaUltra";break;
                
            }
            
            File archivo = new File(name);
            if(archivo!=null){
                try {
                    Scanner in = new Scanner(archivo);
                    String aux2 = in.nextLine();
                    List<Producto> auxP = new ArrayList<Producto>();
                    while(in.hasNext()){
                        auxP.add(Producto.parseIn(in.nextLine()));
                    }
                    Configuracion conf = new Configuracion(aux2, auxP);
                    this.nombre = archivo.getName();
                    this.datos.addAll(conf.getLista());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setContentText(bundle.getString("cargarFastAction.Content"));
            alerta.showAndWait();
        }
    }
    
    @FXML void mostrarInformacionAction(ActionEvent evento){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(bundle.getString("mostrarInformacion.Title"));
        alerta.setHeaderText(bundle.getString("mostrarInformacion.Header"));
        alerta.setContentText(bundle.getString("mostrarInformacion.Content"));
        alerta.showAndWait();
    }
}
