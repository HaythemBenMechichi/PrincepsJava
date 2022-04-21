/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zribi
 */
public class MenuPrincipal implements Initializable {

    @FXML
    private Button btn_Ajouter;
    @FXML
    private Button btn_Afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void afficherEvenement(ActionEvent event) throws IOException {
        Parent rootEv = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(true);
        window.show();
    }

    @FXML
    private void afficherPromotion(ActionEvent event) throws IOException {
        Parent rootPr = FXMLLoader.load(getClass().getResource("AfficherPromotion.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootPr);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setMaximized(true);
        window.setScene(gestionViewScene);
        window.show();
    }

}
