/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pi.gui;

import Controller.UserSession;
import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Livreur;
import edu.workshopjdbc3a48.entities.User;
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
         UserServices US = new UserServices();

  if ( tfPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty())
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        }
 else
        {
            
                System.out.println("u= "+ UserSession.getId());
           
            System.out.println("p = "+UserSession.getId());
            String hashed = BCrypt.hashpw(tfPassword.getText(),  BCrypt.gensalt());
             US.modifierPassword(hashed,UserSession.getId());
            JOptionPane.showMessageDialog(null, "User modifié avec succés");
            UserSession.setPassword(tfPassword.getText());
             
        
        
        
        
        
    }
    
}
}


