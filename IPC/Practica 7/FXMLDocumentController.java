/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 *
 * @author jodoldar
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private LineChart<String,Number> chart;
    @FXML
    private AreaChart<String,Number> chart2;
    @FXML
    private BarChart<String,Number> chart3;
    @FXML
    private PieChart chart4;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis2;
    @FXML
    private NumberAxis yAxis2;
    @FXML
    private CategoryAxis xAxis3;
    @FXML
    private NumberAxis yAxis3;
    
    private int[] hist;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hist = new int[10];
        for (int i = 0; i < hist.length; i++){
            hist[i] = 0;
        }
        for (int j = 0; j < 1000; j++) {
            double value = Math.random() * 10;
            for (int i = 0; i < hist.length; i++){
                if (i <= value && value < i+1) {
                    hist[i]++;
                    break;
                }
            }
        }
        
        initLineChart();
        initAreaChart();
        initBarChart();
        initPieChart();
    }    
    
    private void initLineChart(){
        xAxis.setLabel("Ranges");
        yAxis.setLabel("Frequencies");
        XYChart.Series<String,Number> series = new XYChart.Series();
        for (int i = 0; i < hist.length; i++){
            series.getData().add(new XYChart.Data<>(i + "-" + (i+1), hist[i]));
        }
        series.setName("Histogram");
        chart.getData().add(series);
    }
    private void initAreaChart(){
        xAxis2.setLabel("Ranges");
        yAxis2.setLabel("Frequencies");
        XYChart.Series<String,Number> series2 = new XYChart.Series();
        for(int i=0; i<hist.length; i++){
            series2.getData().add(new XYChart.Data<>(i + "-" + (i+1), hist[i]));
        }
        series2.setName("Areagram");
        chart2.getData().add(series2);
    }
    private void initBarChart(){
        xAxis3.setLabel("Ranges");
        yAxis3.setLabel("Frequencies");
        XYChart.Series<String,Number> series3 = new XYChart.Series();
        series3.setName("Rangos");
        for(int i=0; i<hist.length; i++){
            series3.getData().add(new XYChart.Data<>(i + "-" + (i+1), hist[i]));
        }
        series3.setName("Bargram");
        chart3.getData().addAll(series3);
    }
    private void initPieChart(){
        ObservableList<PieChart.Data> series4 = FXCollections.observableArrayList();
        for(int i=0; i<hist.length; i++){
            series4.add(new PieChart.Data(i + "-" + (i+1),hist[i]));
        }
        chart4.setData(series4);
    }
}
