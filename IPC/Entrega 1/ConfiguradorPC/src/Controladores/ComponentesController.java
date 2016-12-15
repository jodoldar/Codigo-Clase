
package Controladores;

import Modelos.Producto;
import es.upv.inf.Product;
import es.upv.inf.Database;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josep Dols
 */
public class ComponentesController implements Initializable {

    @FXML
    private Button filtrar;
    @FXML
    private Button aceptar;
    @FXML
    private Button cancelar;
    @FXML
    private CheckBox stock;
    @FXML
    private ComboBox rango;
    @FXML
    private ComboBox categoria;
    @FXML
    private TextField descripcion;
    @FXML
    private TableView<Producto> tabla2;
    @FXML
    private TableColumn<Producto,String> tablaComponente;
    @FXML
    private TableColumn<Producto,Double> tablaPrecio;
    @FXML
    private TableColumn<Producto,Integer> tablaStock;
    
    private int position;
    private Stage segundaVentana;
    private FXMLDocumentController mainController;
    public ObservableList<Producto> miLista = null;
    public ObservableList<Producto> catalogo = null;
    private ResourceBundle bundle;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Vistas/FXMLDocument.fxml"),bundle);
        try{
            cargador.load();
            this.mainController = cargador.<FXMLDocumentController>getController();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tablaComponente.setCellValueFactory(celda1 -> new SimpleStringProperty(celda1.getValue().getDescripcion()));
        tablaPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        tablaStock.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("stock"));
        tabla2.setItems(catalogo);
        
        ArrayList<String> rangos = new ArrayList();
        rangos.add("Sin rango");
        rangos.add("1€--50€");
        rangos.add("51€--100€");
        rangos.add("101€--200€");
        rangos.add("+201€");
        ObservableList<String> rangos2 = FXCollections.observableList(rangos);
        rango.setItems(rangos2);
        rango.getSelectionModel().selectFirst();
        
        ArrayList<Product.Category> categorias = new ArrayList();
        categorias.add(Product.Category.CASE);
        categorias.add(Product.Category.CPU);
        categorias.add(Product.Category.DVD_WRITER);
        categorias.add(Product.Category.FAN);
        categorias.add(Product.Category.GPU);
        categorias.add(Product.Category.HDD);
        categorias.add(Product.Category.HDD_SSD);
        categorias.add(Product.Category.KEYBOARD);
        categorias.add(Product.Category.MOTHERBOARD);
        categorias.add(Product.Category.MOUSE);
        categorias.add(Product.Category.MULTIREADER);
        categorias.add(Product.Category.POWER_SUPPLY);
        categorias.add(Product.Category.RAM);
        categorias.add(Product.Category.SCREEN);
        categorias.add(Product.Category.SPEAKER);
        ObservableList<Product.Category> categorias2 = FXCollections.observableList(categorias);
        categoria.setItems(categorias2);
        
        //componente.setCellValueFactory(new PropertyValueFactory<>("Componente"));
        //precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        //tabla.setItems(catalogo);
    }    

    void initStage(Stage actualStage) {
        segundaVentana = actualStage;
        segundaVentana.setTitle("Componentes");
    }
    
    public void initData(ObservableList data){
        this.miLista = data;
    }
    
    public void initForAdd(){
        aceptar.setText(bundle.getString("button.Aceptar"));
    }
    public void initForEdit(int x){
        aceptar.setText(bundle.getString("button.Editar"));
        this.position = x;
    }
    
    public void initProduct(Product p, int pos){
        if(p != null) {
            //nombre.setText(p.getNombre());
            //apellido.setText(p.getApellidos());
            this.position = pos;
        } else {
            this.position = -1;
        }
    }
    
    @FXML 
    public void cancelAction(ActionEvent evento){
        Node nodo1 = (Node) evento.getSource();
        nodo1.getScene().getWindow().hide();
    }
    
    @FXML
    public void filterAction(ActionEvent evento){
        List<Product> aux;
        if(null != categoria.getValue()){
            
            if(!rango.getValue().equals("Sin rango")){
                //Si tengo rango, hay que mirar si hay descripción
                
                if(descripcion.getText().equals("")){
                    
                    String id = (String) rango.getValue();
                    if(id.startsWith("1€")){
                        aux = Database.getProductByCategoryAndPrice((Product.Category)categoria.getValue(),
                            1, 50, stock.isSelected());
                    }else if(id.startsWith("51€")){
                        aux = Database.getProductByCategoryAndPrice((Product.Category)categoria.getValue(),
                            51, 100, stock.isSelected());
                    }else if(id.startsWith("101€")){
                        aux = Database.getProductByCategoryAndPrice((Product.Category)categoria.getValue(),
                            101, 200, stock.isSelected());
                    }else{
                        aux = Database.getProductByCategoryAndPrice((Product.Category)categoria.getValue(),
                            201, Integer.MAX_VALUE, stock.isSelected());
                    }
                }else{
                    
                    String id = (String) rango.getValue();
                    if(id.startsWith("1€")){
                        aux = Database.getProductByCategoryDescriptionAndPrice((Product.Category)categoria.getValue(),
                                descripcion.getText(), 1, 50, stock.isSelected());

                    }else if(id.startsWith("51€")){
                        aux = Database.getProductByCategoryDescriptionAndPrice((Product.Category)categoria.getValue(),
                                descripcion.getText(), 51, 100, stock.isSelected());

                    }else if(id.startsWith("101€")){
                        aux = Database.getProductByCategoryDescriptionAndPrice((Product.Category)categoria.getValue(),
                                descripcion.getText(), 101, 200, stock.isSelected());

                    }else{
                        aux = Database.getProductByCategoryDescriptionAndPrice((Product.Category)categoria.getValue(),
                                descripcion.getText(), 201, Integer.MAX_VALUE, stock.isSelected());
                    }
                }
            } else {
                //Si no tengo rango, hay que mirar si hay descripción
                
                if(descripcion.getText().equals("")){
                    
                    aux = Database.getProductByCategoryAndDescription((Product.Category)categoria.getValue(),"",stock.isSelected());
                    
                }else{
                    
                    aux = Database.getProductByCategoryAndDescription((Product.Category)categoria.getValue(), descripcion.getText(), stock.isSelected());
                }
            }
            ObservableList<Producto> salida = FXCollections.observableArrayList();
            for(Product producto : aux){
                salida.add(new Producto(producto));
            }
            
            tabla2.setItems(salida);
        }else{
            
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle(bundle.getString("filterAction.Title"));
            alerta.setHeaderText(bundle.getString("filterAction.Header"));
            alerta.setContentText(bundle.getString("filterAction.Content"));
            alerta.show();
        }
    }
    @FXML
    public void buttonAction(ActionEvent evento){
        if(aceptar.getText().equals(bundle.getString("button.Aceptar"))){
            añadirAction(evento);
        }else{
            editarAction(evento);
        }
    }
    
    public void añadirAction(ActionEvent evento){
        Producto nuevo = tabla2.getSelectionModel().getSelectedItem();
        if(nuevo!=null){
            List<Product> aux = Database.getProductByCategoryAndDescription(nuevo.getCategoria(), nuevo.getDescripcion(), true);
            //this.mainController.getData().add(nuevo);
            miLista.add(new Producto(aux.get(0)));
        
            this.mainController.initData(miLista);
            Node nodo1 = (Node)evento.getSource();
            nodo1.getScene().getWindow().hide();
        }else{
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle(bundle.getString("añadirAction.Title"));
            alerta.setHeaderText(bundle.getString("añadirAction.Header"));
            alerta.setContentText(bundle.getString("añadirAction.Content"));
            alerta.showAndWait();
        }
    }
    public void editarAction(ActionEvent evento){
        Producto nuevo = tabla2.getSelectionModel().getSelectedItem();
        if(nuevo!=null){
            List<Product> aux = Database.getProductByCategoryAndDescription(nuevo.getCategoria(), nuevo.getDescripcion(), true);
            //this.mainController.getData().add(nuevo);
            miLista.set(this.position,new Producto(aux.get(0)));
        
            this.mainController.initData(miLista);
            Node nodo1 = (Node)evento.getSource();
            nodo1.getScene().getWindow().hide();
        }else{
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle(bundle.getString("editarAction.Title"));
            alerta.setHeaderText(bundle.getString("editarAction.Header"));
            alerta.setContentText(bundle.getString("editarAction.Content"));
            alerta.showAndWait();
        }
    }
}
