/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UnereclamationController implements Initializable {

    @FXML
    private AnchorPane anchor_reclamation;
    @FXML
    private Label label_id;
    @FXML
    private Label label_typereclamation;
    @FXML
    private Label label_niveau;
    @FXML
    private Label label_userid;
    @FXML
    private Label label_sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public AnchorPane getAnchor_reclamation() {
        return anchor_reclamation;
    }

    public void setAnchor_reclamation(AnchorPane anchor_reclamation) {
        this.anchor_reclamation = anchor_reclamation;
    }

    public void setLabel_id(int label_id) {
        this.label_id.setText(Integer.toString(label_id)); 
    }

    public void setLabel_typereclamation(int label_typereclamation) {
        this.label_typereclamation.setText(Integer.toString(label_typereclamation));
    }

    public void setLabel_niveau(int label_niveau) {
        this.label_niveau.setText(Integer.toString(label_niveau));
    }

    public void setLabel_userid(int label_userid) {
        this.label_userid.setText(Integer.toString(label_userid));
    }

    public void setLabel_sujet(String label_sujet) {
        this.label_sujet.setText(label_sujet);
    }
    
}
