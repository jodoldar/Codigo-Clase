
package practica5;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Josep Dols
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label etiqueta;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private WebView webView;
    @FXML
    private MenuItem cerrar;
    @FXML
    private MenuItem lanza1;
    @FXML
    private MenuItem lanza2;
    @FXML
    private MenuItem lanza3;
    @FXML
    private MenuItem lanza4;
    @FXML
    private MenuItem lanza5;
    @FXML
    private RadioMenuItem amazonOption;
    @FXML
    private RadioMenuItem ebayOption;
    
    private ResourceBundle bundle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        amazonOption.selectedProperty().set(true);
        etiqueta.setVisible(false);
        bundle = rb;
        /*
        ImageView imagen1 = new ImageView("/");
        imagen1.setFitHeight(65);imagen1.setFitWidth(65);imagen1.preserveRatioProperty();imagen1.setSmooth(true);
        boton1.setGraphic(imagen1);
        
        ImageView imagen2 = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Blogger_icon.svg/768px-Blogger_icon.svg.png");
        imagen2.setFitHeight(65);imagen2.setFitWidth(65);imagen2.preserveRatioProperty();imagen2.setSmooth(true);
        boton2.setGraphic(imagen2);
        
        ImageView imagen3 = new ImageView("https://d13yacurqjgara.cloudfront.net/users/1857/screenshots/729847/ebay-revision-01.png");
        imagen3.setFitHeight(65);imagen3.setFitWidth(65);imagen3.preserveRatioProperty();imagen3.setSmooth(true);
        boton3.setGraphic(imagen3);
        
        ImageView imagen4 = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/F_icon.svg/2000px-F_icon.svg.png");
        imagen4.setFitHeight(65);imagen4.setFitWidth(65);imagen4.preserveRatioProperty();imagen4.setSmooth(true);
        boton4.setGraphic(imagen4);
        
        ImageView imagen5 = new ImageView("http://icons.iconarchive.com/icons/marcus-roberto/google-play/512/Google-plus-icon.png");
        imagen5.setFitHeight(65);imagen5.setFitWidth(65);imagen5.preserveRatioProperty();imagen5.setSmooth(true);
        boton5.setGraphic(imagen5);*/
    }    
    
    @FXML
    private void cerrarTodo(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("alertTitle.Close"));
        alert.setHeaderText(bundle.getString("alertHeader.Close"));
        alert.setContentText(bundle.getString("alertContent.Close"));
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            System.exit(0);
        }
    }
    
    @FXML
    private void abirAmazon(ActionEvent event){
        if(amazonOption.isSelected()){
            //webView.getEngine().load("https://www.amazon.es/");
            etiqueta.setText(bundle.getString("labelInfo.Amazon"));
            etiqueta.setVisible(true);
            
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle(bundle.getString("alertTitle.Amazon"));
            alerta.setHeaderText(bundle.getString("alertHeader.Amazon"));
            alerta.setContentText(bundle.getString("alertContent.Amazon"));
            alerta.showAndWait();
        }else{
            etiqueta.setVisible(false);
            
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle(bundle.getString("alertTitle.AmazonFail"));
            alerta.setHeaderText(bundle.getString("alertHeader.AmazonFail"));
            alerta.setContentText(bundle.getString("alertContent.AmazonFail"));
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void abrirEbay(ActionEvent event){
        if(ebayOption.isSelected()){
            etiqueta.setText(bundle.getString("labelInfo.Ebay"));
            etiqueta.setVisible(true);
            
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle(bundle.getString("alertTitle.Ebay"));
            alerta.setHeaderText(bundle.getString("alertHeader.Ebay"));
            alerta.setContentText(bundle.getString("alertContent.Ebay"));
            alerta.showAndWait();
        }else{
            etiqueta.setVisible(false);
            
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle(bundle.getString("alertTitle.EbayFail"));
            alerta.setHeaderText(bundle.getString("alertHeader.EbayFail"));
            alerta.setContentText(bundle.getString("alertContent.EbayFail"));
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void abrirBlogger(ActionEvent event){
        List<String> choices = new ArrayList<>();
        choices.add("El blog de Athos");
        choices.add("El blog de Porthos");
        choices.add("El blog de Aramis");
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("El blog de Athos",choices);
        dialog.setTitle(bundle.getString("dialogTitle.Blog"));
        dialog.setHeaderText(bundle.getString("dialogHeader.Blog"));
        dialog.setContentText(bundle.getString("dialogContent.Blog"));
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            etiqueta.setText(bundle.getString("labelInfo.Blog") + result.get());
            etiqueta.setVisible(true);
        }
        
    }
    
    @FXML
    private void abrirFacebook(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(bundle.getString("dialogTitle.Facebook"));
        dialog.setHeaderText(bundle.getString("dialogHeader.Facebook"));
        dialog.setContentText(bundle.getString("dialogContent.Facebook"));
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            etiqueta.setText(bundle.getString("labelInfo.Facebook") + result.get());
            etiqueta.setVisible(true);
        }
    }
    
    @FXML
    private void abrirGoogle(ActionEvent event){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(bundle.getString("dialogTitle.Google"));
        alerta.setHeaderText(bundle.getString("dialogHeader.Google"));
        alerta.setContentText(bundle.getString("dialogContent.Google"));
        alerta.showAndWait();
        
        etiqueta.setVisible(false);
    }
}
