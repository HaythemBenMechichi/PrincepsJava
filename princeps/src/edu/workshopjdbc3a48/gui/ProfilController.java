/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soon
 */
public class ProfilController implements Initializable {

   
    @FXML
    private TextField TextNom;
    @FXML
    private TextField TextPrenom;
    @FXML
    private TextField TextEmail;
    @FXML
    private TextField TextAge;
    @FXML
    private TextField TextNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTextNom(TextField TextNom) {
        this.TextNom = TextNom;
    }

    public void setTextPrenom(TextField TextPrenom) {
        this.TextPrenom = TextPrenom;
    }

    public void setTextEmail(TextField TextEmail) {
        this.TextEmail = TextEmail;
    }

    public void setTextAge(String TextAge) {
        this.TextAge.setText(TextAge);
    }

    public void setTextNumber(TextField TextNumber) {
        this.TextNumber = TextNumber;
    }
    @FXML
    private void update(ActionEvent event) {
        
        
    }

    @FXML
    private void changePassword(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        try {
            Parent root = loader.load();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }

    @FXML
    private void LogOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }
    
   
    
}
