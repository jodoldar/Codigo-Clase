package Prueba;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Practica 1 de IPC(2015-2016)
 * @author Josep Dols
 */
public class Prueba extends Application{
    
    @Override
    public void start(Stage primaryStage){

        AnchorPane window = new AnchorPane();
        Label lbl = new Label("Tres en raya");
        lbl.setPadding(new Insets(20,10,0,10));
        
        GridPane game = new GridPane();
        for(int col=0;col<3;col++){
            for(int row=0;row<3;row++){
                Button btn = new Button();
                btn.setPrefSize(100, 100);
                game.getChildren().add(btn);
                GridPane.setConstraints(btn,col,row);
            }
        }
        game.setVgap(0);
        game.setHgap(0);
        game.setPadding(new Insets(15));
        
        Button b1 = new Button("Empezar");
        Button b2 = new Button("Récords");
        HBox bottom = new HBox(10);
        bottom.getChildren().addAll(b1,b2);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));
        
        VBox top = new VBox();
        top.getChildren().addAll(lbl,game,bottom);
        top.setAlignment(Pos.CENTER);
        window.getChildren().add(top);
        window.setPrefSize(330, 430);
        
        
        Scene scene = new Scene(window,window.getPrefWidth(),window.getPrefHeight());
        primaryStage.setTitle("Practica 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
