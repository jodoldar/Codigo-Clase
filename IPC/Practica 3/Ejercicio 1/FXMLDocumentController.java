/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author jodoldar
 */
public class FXMLDocumentController {
    
    @FXML
    private Label resultado;
    @FXML
    private Button uno;
    @FXML 
    private Button cinco;
    @FXML
    private Button diez;
    @FXML
    private CheckBox resta;
    @FXML
    private Label aviso;
    @FXML
    private TextField valor;
    @FXML
    private Button operacion;
    
    private boolean restara;
    public void initialize() {
        resultado.setText("0.0");
        aviso.setVisible(false);
        resta.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               if(resta.isSelected()){
                   restara = true;
                   aviso.setVisible(restara);
               }else{
                   restara = false;
                   aviso.setVisible(restara);
               }
            }
        });
    }

    public void operacionFija(ActionEvent event){
        double aux = Double.parseDouble(resultado.getText());
        Button clicked = (Button) event.getSource();
        if(restara){
            if (clicked.getId().equals("uno")){
                aux-=1;
            }else if(clicked.getId().equals("cinco")){
                aux-=5;
            }else{
                aux-=10;
            }
        }else{
            if (clicked.getId().equals("uno")){
                aux+=1;
            }else if(clicked.getId().equals("cinco")){
                aux+=5;
            }else{
                aux+=10;
            }
        }
        resultado.setText(Double.toString(aux));
    }
    
    public void operacionVariable(){
        double aux = Double.parseDouble(resultado.getText());
        double cantidad = 0;
        if(!valor.getText().equals("")){
            cantidad = Double.parseDouble(valor.getText());
        }
        if(restara){
            aux -= cantidad;
        }else{
            aux += cantidad;
        }
        resultado.setText(Double.toString(aux));
    }
}
