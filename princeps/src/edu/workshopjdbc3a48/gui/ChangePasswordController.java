/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author soon
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirmPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnPahe(ActionEvent event) {
    }

    @FXML
    private void updatePassword(ActionEvent event) {
        String Password=tfPassword.getText();
            String CPassword=tfConfirmPassword.getText();
         if(!(Password.equals(CPassword)))
                 {
                     Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Missing Fields");
                  t.showAndWait();
                     
                 }
         else
         {
             String hashed = BCrypt.hashpw(Password,  BCrypt.gensalt());
         }
    }
    
}
