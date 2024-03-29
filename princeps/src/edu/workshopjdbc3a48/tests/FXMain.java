/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author haythem
 */
public class FXMain extends Application {
        public static final String CURRENCY = "$";

    @Override
    public void start(Stage primaryStage) throws IOException {
       
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/frontProduit.fxml"));

        
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Gestion Produits");
    primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
