package Controlador;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jgpx.model.analysis.Chunk;
import jgpx.model.analysis.TrackData;
import jgpx.model.gpx.Track;
import jgpx.model.jaxb.GpxType;
import jgpx.model.jaxb.TrackPointExtensionT;

/**
 *
 * @author Josep Dols
 */
public class MainWindowController implements Initializable {
    
    @FXML Label fechaHora;
    @FXML Label duracion;
    @FXML Label tiempoMovimiento;
    @FXML Label distRecorrida;
    @FXML Label desnivelUp;
    @FXML Label desnivelDown;
    @FXML Label frecUp;
    @FXML Label frecMid;
    @FXML Label frecDown;
    @FXML Label speedUp;
    @FXML Label speedMid;
    @FXML Label cadenceUp;
    @FXML Label cadenceMid;
    
    @FXML ProgressBar progreso;
    @FXML Label status;
    @FXML TabPane panel;
    @FXML Label chartLabel;
    
    @FXML MenuItem cargar;
    @FXML MenuItem cerrar;
    @FXML MenuItem cambiaEje;
    @FXML MenuItem infor;
    
    @FXML AreaChart<Number,Number> chart1;
    @FXML LineChart<Number,Number> chart2;
    @FXML LineChart<Number,Number> chart3;
    @FXML LineChart<Number,Number> chart4;
    @FXML PieChart pieChart;

    
    protected TrackData trackdata;
    protected GpxType activeGpx;
    protected XYChart.Series serie1;
    protected XYChart.Series serie2;
    protected XYChart.Series serie3;
    protected XYChart.Series serie4;
    protected XYChart.Series serie1Tiempo;
    protected XYChart.Series serie2Tiempo;
    protected XYChart.Series serie3Tiempo;
    protected XYChart.Series serie4Tiempo;
    protected ObservableList<PieChart.Data> serieChart;
    private boolean isLoaded;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        habilitaLabels(false);
        status.setText("");
        progreso.setProgress(0);
        isLoaded = false;
        chartLabel.setText("Distancia");

        chart1.getYAxis().setLabel("Altura");
        chart2.getYAxis().setLabel("Velocidad");
        chart3.getYAxis().setLabel("Frec. Cardiaca");
        chart4.getYAxis().setLabel("Cadencia");
        
    }  
    
    @FXML
    public void cambiarEjeAction(ActionEvent evento){
        if(this.progreso.getProgress()!=0 && this.progreso.getProgress()!=1 ){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Ruta cargando");
            alerta.setHeaderText("Espere por favor");
            alerta.setContentText("Hay una ruta cargando, espere a que se cargue para mostrar el cambio");
            alerta.showAndWait();
        }else{
            if(!isLoaded){
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("No hay ruta");
                alerta.setHeaderText("Cargue una ruta");
                alerta.setContentText("Por favor, cargue una ruta antes de aplicar la acción");
                alerta.showAndWait();
            }else{
                if(chartLabel.getText().equals("Distancia")){
                    chart1.getData().removeAll(serie1);
                    chart2.getData().removeAll(serie2);
                    chart3.getData().removeAll(serie3);
                    chart4.getData().removeAll(serie4);
        
                    chart1.getData().addAll(serie1Tiempo);
                    chart2.getData().addAll(serie2Tiempo);
                    chart3.getData().addAll(serie3Tiempo);
                    chart4.getData().addAll(serie4Tiempo);
                    
                    chartLabel.setText("Tiempo");
                }else{
                    chart1.getData().removeAll(serie1Tiempo);
                    chart2.getData().removeAll(serie2Tiempo);
                    chart3.getData().removeAll(serie3Tiempo);
                    chart4.getData().removeAll(serie4Tiempo);
        
                    chart1.getData().addAll(serie1);
                    chart2.getData().addAll(serie2);
                    chart3.getData().addAll(serie3);
                    chart4.getData().addAll(serie4);
                    
                    chartLabel.setText("Distancia");
                }
                
                
                
            }

        }
    }
    
    @FXML
    public void cerrarAction(ActionEvent evento){
        System.exit(0);
    }
    
    @FXML
    public void infoAction(ActionEvent evento){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setHeaderText("Información");
        alerta.setContentText("Aplicación creada por Josep Vicent Dols\nEmail: josepdd@gmail.com");
        alerta.showAndWait();
    }
    @FXML
    public void cargarAction(ActionEvent evento){
        if(this.progreso.getProgress()!=0 && this.progreso.getProgress()!=1 ){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Ruta cargando");
            alerta.setHeaderText("Espere por favor");
            alerta.setContentText("Ya hay una ruta cargando, espere a que se cargue para cargar una nueva");
            alerta.showAndWait();
        }else{
            
            FileChooser fc = new FileChooser();
            Stage stage = new Stage();
            File archivo = fc.showOpenDialog(stage);
            if(archivo!=null){
                try{
                    JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(archivo);
                    GpxType actGpx = (GpxType) root.getValue();
                    if(actGpx!=null){
                        if(isLoaded){
                            chart1.getData().clear();
                            chart2.getData().clear();
                            chart3.getData().clear();
                            chart4.getData().clear();
                            progreso.setProgress(0);
                        }
                        Task<Void> tareaInit = new Task<Void>(){
                            @Override
                            protected Void call() throws Exception {
                                loadRuta(actGpx);
                                return null;
                            }
                            @Override
                            protected void succeeded(){
                                status.setText("Cargando archivo");
                                chartLabel.setText("Distancia");
                                isLoaded = true;
                            }
                        };
                    
                        Thread th1 = new Thread(tareaInit);
                        th1.setDaemon(true);
                        th1.start();
                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }   //COMPLETADO
    private void habilitaLabels(boolean aux){
        fechaHora.setVisible(aux);
        duracion.setVisible(aux);
        tiempoMovimiento.setVisible(aux);
        distRecorrida.setVisible(aux);
        desnivelUp.setVisible(aux);
        desnivelDown.setVisible(aux);
        frecUp.setVisible(aux);
        frecMid.setVisible(aux);
        frecDown.setVisible(aux);
        speedUp.setVisible(aux);
        speedMid.setVisible(aux);
        cadenceUp.setVisible(aux);
        cadenceMid.setVisible(aux);
    }       //COMPLETADO
    protected void loadRuta(GpxType t){
        this.activeGpx = t;
        this.trackdata = new TrackData(new Track(activeGpx.getTrk().get(0)));
        ObservableList<Chunk> chunks = trackdata.getChunks();
        progreso.setProgress(0);
        
        Task<Void> tareaResumen = new Task<Void>(){         
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {loadResumen(trackdata);});
                return null;
            }
            @Override
            protected void succeeded(){
                progreso.setProgress(progreso.getProgress()+0.1);
                habilitaLabels(true);
            }
        };//Tarea para cargar el resumen
        Thread th2 = new Thread(tareaResumen);
        th2.setDaemon(true);
        th2.start();
        
        Task<Void> tareaGraf1 = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                serie1 = loadGraf1(chunks);
                return null;
            }
            @Override
            protected void succeeded(){
                super.succeeded();
                chart1.getData().addAll(serie1);
                progreso.setProgress(progreso.getProgress()+0.18);
                setStatus("Archivo .gpx cargado");
            }
        };  //Tarea para cargar la primera gráfica
        Thread th3 = new Thread(tareaGraf1);
        th3.setDaemon(true);
        th3.start();
        
        Task<Void> tareaGraf2 = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                serie2 = loadGraf2(chunks);
                return null;
            }
            @Override
            protected void succeeded(){
                super.succeeded();
                chart2.getData().addAll(serie2);
                progreso.setProgress(progreso.getProgress()+0.18);
                setStatus("Archivo .gpx cargado");
            }
        };  //Tarea para cargar la segunda gráfica
        Thread th4 = new Thread(tareaGraf2);
        th4.setDaemon(true);
        th4.start();
        
        Task<Void> tareaGraf3 = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                serie3 = loadGraf3(chunks);
                return null;
            }
            @Override
            protected void succeeded(){
                super.succeeded();
                chart3.getData().addAll(serie3);
                progreso.setProgress(progreso.getProgress()+0.18);
                setStatus("Archivo .gpx cargado");
            }
        };  //Tarea para cargar la tercera gráfica
        Thread th5 = new Thread(tareaGraf3);
        th5.setDaemon(true);
        th5.start();
        
        Task<Void> tareaGraf4 = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                serie4 = loadGraf4(chunks);
                return null;
            }
            @Override
            protected void succeeded(){
                super.succeeded();
                chart4.getData().addAll(serie4);
                progreso.setProgress(progreso.getProgress()+0.18);
                setStatus("Archivo .gpx cargado");
            }
        };  //Tarea para cargar la cuarta gráfica
        Thread th6 = new Thread(tareaGraf4);
        th6.setDaemon(true);
        th6.start();
        
        Task<Void> tareaChart = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                serieChart = loadPieChart(chunks);
                return null;
            }
            @Override
            protected void succeeded(){
                super.succeeded();
                pieChart.setData(serieChart);
                pieChart.setLegendVisible(false);
                progreso.setProgress(progreso.getProgress()+0.18);
            }
        };
        Thread th7 = new Thread(tareaChart);
        th7.setDaemon(true);
        th7.start();
    }
    public void loadResumen(TrackData td){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.fechaHora.setText(td.getStartTime().format(dtf));
        
        LocalTime durT = LocalTime.ofNanoOfDay(td.getTotalDuration().toNanos());
        this.duracion.setText(durT.toString());
        
        LocalTime timT = LocalTime.ofNanoOfDay(td.getMovingTime().toNanos());
        this.tiempoMovimiento.setText(timT.toString());
        
        this.distRecorrida.setText(String.format("%.2f",td.getTotalDistance()) + " m.");
        
        this.desnivelUp.setText(String.format("%.2f",td.getTotalAscent()));
        this.desnivelDown.setText(String.format("%.2f", td.getTotalDescend()));
        
        this.speedMid.setText(String.format("%.2f", td.getAverageSpeed()));
        this.speedUp.setText(String.format("%.2f", td.getMaxSpeed()));

        this.frecDown.setText(String.valueOf(td.getMinHeartRate()));
        this.frecMid.setText(String.valueOf(td.getAverageHeartrate()));
        this.frecUp.setText(String.valueOf(td.getMaxHeartrate()));
       
        this.cadenceMid.setText(String.valueOf(td.getAverageCadence()));
        this.cadenceUp.setText(String.valueOf(td.getMaxCadence()));
    }       //COMPLETADO
    public XYChart.Series loadGraf1(ObservableList<Chunk> c){
        XYChart.Series resTiempo = new XYChart.Series();
        XYChart.Series res = new XYChart.Series();
        
        double aux = 0;
        double auxTiempo = 0;
        
        for(Chunk ch:c){
            res.getData().add(new XYChart.Data(aux, ch.getLastPoint().getElevation()));
            resTiempo.getData().add(new XYChart.Data(auxTiempo/60, ch.getLastPoint().getElevation()));
            
            aux+=ch.getDistance();
            auxTiempo+=ch.getDuration().getSeconds();
        }
        serie1Tiempo = resTiempo;
        return res;
    }
    public XYChart.Series loadGraf2(ObservableList<Chunk> c){
        XYChart.Series res = new XYChart.Series();
        XYChart.Series resTiempo = new XYChart.Series();
        
        double aux = 0;
        double auxTiempo = 0;
        
        for(Chunk ch:c){
            res.getData().add(new XYChart.Data(aux,ch.getSpeed()));
            resTiempo.getData().add(new XYChart.Data(auxTiempo/60,ch.getSpeed()));
            
            aux+=ch.getDistance();
            auxTiempo+=ch.getDuration().getSeconds();
        }
        serie2Tiempo = resTiempo;
        return res;
    }
    public XYChart.Series loadGraf3(ObservableList<Chunk> c){
        XYChart.Series res = new XYChart.Series();
        XYChart.Series resTiempo = new XYChart.Series();
        
        double aux = 0;
        double auxTiempo = 0;
        
        for(Chunk ch:c){
            res.getData().add(new XYChart.Data(aux,ch.getAvgHeartRate()));
            resTiempo.getData().add(new XYChart.Data(auxTiempo/60,ch.getAvgHeartRate()));
            
            aux+=ch.getDistance();
            auxTiempo+=ch.getDuration().getSeconds();
        }
        serie3Tiempo = resTiempo;
        return res;
    }
    public XYChart.Series loadGraf4(ObservableList<Chunk> c){
        XYChart.Series res = new XYChart.Series();
        XYChart.Series resTiempo = new XYChart.Series();
        
        double aux = 0;
        double auxTiempo = 0;
        
        for(Chunk ch:c){
            res.getData().add(new XYChart.Data(aux,ch.getAvgCadence()));
            resTiempo.getData().add(new XYChart.Data(auxTiempo/60,ch.getAvgCadence()));
            
            aux+=ch.getDistance();
            auxTiempo+=ch.getDuration().getSeconds();
        }
        serie4Tiempo = resTiempo;
        return res;
    }
    public ObservableList<PieChart.Data> loadPieChart(ObservableList<Chunk> c){
        int FCmax = this.trackdata.getMaxHeartrate();
        ObservableList<PieChart.Data> res = FXCollections.observableArrayList();
        
        Zona z1 = new Zona();
        Zona z2 = new Zona();
        Zona z3 = new Zona();
        Zona z4 = new Zona();
        Zona z5 = new Zona();
        
        for(Chunk ch : c){
            if(ch.getAvgHeartRate()<FCmax*0.6){
                z1.addOne();
            }else if(ch.getAvgHeartRate()<FCmax*0.7){
                z2.addOne();
            }else if(ch.getAvgHeartRate()<FCmax*0.8){
                z3.addOne();
            }else if(ch.getAvgHeartRate()<FCmax*0.9){
                z4.addOne();
            }else{
                z5.addOne();
            }
        }
        
        res.addAll(new PieChart.Data("Z1 Recuperación", z1.getContador()),
                new PieChart.Data("Z2 Fondo", z2.getContador()),
                new PieChart.Data("Z3 Tempo", z3.getContador()),
                new PieChart.Data("Z4 Umbral", z4.getContador()),
                new PieChart.Data("Z5 Anaerobico",z5.getContador()));
        
        return res;
    }

    private void setStatus(String name){
        if(this.progreso.getProgress()==1){
            this.status.setText(name); 
        }
    }
    
    private static class Zona {
        private int contador;
        
        public Zona() {
            this.contador = 0;
        }
        public void addOne(){
            this.contador++;
        }
        public int getContador(){
            return this.contador;
        }
    }
}
