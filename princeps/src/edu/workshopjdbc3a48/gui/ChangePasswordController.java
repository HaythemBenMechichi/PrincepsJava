/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import Controller.UserSession;
import edu.workshopjdbc3a48.services.UserServices;
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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        System.out.println("nextpage");
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
    private void updatePassword(ActionEvent event) {
        String Password=tfPassword.getText();
            String CPassword=tfConfirmPassword.getText();
         if(!(Password.equals(CPassword)))
                 {
                     Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Confirmer votre password");
                  t.setHeaderText(null);
                  t.setContentText("Confirmer votre password");
                  t.showAndWait();
                     
                 }
         else
         {
             String hashed = BCrypt.hashpw(Password,  BCrypt.gensalt());
             UserServices US = new UserServices();
              System.out.println("p = ");
             US.modifierPassword(hashed,UserSession.getId());
             
            JOptionPane.showMessageDialog(null, "password modifié avec succés");

            
         }
    }
    
}
