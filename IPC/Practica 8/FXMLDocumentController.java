/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upv.inf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author fjabad
 */
public class FXMLDocumentController implements Initializable {

    private static final int NUM_BUTTONS = 5;

    @FXML
    private Label label;
    @FXML
    private HBox buttonContainer;

    private long t0;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < NUM_BUTTONS; i++) {
            Button b = new Button("Click me");
            b.setOnAction(this::onStopTimer);
            b.setPrefSize(80, 80);
            b.setDisable(true);
            buttonContainer.getChildren().add(b);
        }
        label.setText("");
        scene = label.getScene();
    }

    @FXML
    private void onStart(ActionEvent event) {
        for (Node b : buttonContainer.getChildren()) {
            b.setDisable(true);
        }

        
        Task<Long> tarea = new Task<Long>(){
            
            double waitTime = Math.random() * 5 + 1.0;
            @Override
            protected Long call() throws Exception {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        label.getScene().setCursor(Cursor.WAIT);
                    }
                });
                
                try {
                    Thread.sleep((long) (waitTime * 1000));
                } catch (InterruptedException e) {
                    System.out.println("Algo falla");
                }
                t0 = System.currentTimeMillis();
                int winnerButton = (int) (Math.floor(Math.random() * NUM_BUTTONS));
                buttonContainer.getChildren().get(winnerButton).setDisable(false);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        label.getScene().setCursor(Cursor.DEFAULT);
                    }
                });
                return t0;
            }
            
            @Override protected void running(){
                super.running();
                updateMessage("Get ready...");
            }
            
            @Override protected void succeeded(){
                super.succeeded();
                updateMessage("Now!");
                label.textProperty().unbind();
            }
        };
        label.textProperty().bind(tarea.messageProperty());
        
        Thread th = new Thread(tarea);
        th.setDaemon(true);
        th.start();
    }

    private void onStopTimer(ActionEvent event) {
        long t1 = System.currentTimeMillis();
        long elapsed = t1 - t0;

        String msg;
        if (elapsed < 150) {
            msg = "You are fast as an arrow! ";
        } else if (elapsed < 500) {
            msg = "Pretty good. ";
        } else if (elapsed < 1000) {
            msg = "Good. ";
        } else {
            msg = "You should try harder. ";
        }
        label.setText(msg + "You needed " + elapsed + " ms");

        ((Node) event.getSource()).setDisable(true);
    }
}
