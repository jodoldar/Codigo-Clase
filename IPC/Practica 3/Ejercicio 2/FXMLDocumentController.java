
package ejercicio2;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 *
 * @author jodoldar
 */
public class FXMLDocumentController {
    
    @FXML
    private Slider variable;
    @FXML
    private Label ratio;
    @FXML
    private TextField input;
    @FXML
    private TextField output;
    @FXML
    private Button conversion;
    @FXML
    private Button limpiar;
    @FXML
    private CheckBox automatico;
    
    @FXML
    public void initialize() {
        input.setText("0.0");
        output.setText("0.0");
        ratio.textProperty().bind(Bindings.format("%.2f", variable.valueProperty()));
        conversion.disableProperty().bind(automatico.selectedProperty());
        conversion.setDefaultButton(true);
    }    
    
    public void convert(){
        double aux = Double.parseDouble(input.getText()) * variable.getValue();
        output.setText(String.format("%.2f", aux));
    }
    
    public void clear(){
        input.setText("0.0");
        output.setText("0.0");
        variable.setValue(0);
    }
    
    public void automatic(){
        DoubleProperty res = new SimpleDoubleProperty(Double.parseDouble(output.getText().replace(',', '.')));
        if(automatico.isSelected()){
            input.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    res.setValue(variable.getValue()*Double.parseDouble(newValue));
                }
            });
            variable.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    res.setValue(newValue.doubleValue()*Double.parseDouble(input.getText()));
                }
            });
            output.textProperty().bind(Bindings.format("%.2f", res));
        }else if(!automatico.isSelected()) {
            output.textProperty().unbind();
        }
    }
}
