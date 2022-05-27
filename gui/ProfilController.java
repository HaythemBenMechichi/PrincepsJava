/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
        TextNom.setText(UserSession.getName());
        TextPrenom.setText(UserSession.getPrenom());
        TextEmail.setText(UserSession.getEmail());
        TextAge.setText(String.valueOf(UserSession.getAge()));
        TextNumber.setText(UserSession.getNumber());
    }    

    public void setTextNom(String TextNom) {
        this.TextNom.setText(TextNom);
    }

    public void setTextPrenom(String TextPrenom) {
        this.TextPrenom.setText(TextPrenom);
    }

    public void setTextEmail(String TextEmail) {
        this.TextEmail.setText(TextEmail);
    }

    public void setTextAge(String TextAge) {
        this.TextAge.setText(TextAge);
    }

    public void setTextNumber(String TextNumber) {
        this.TextNumber.setText(TextNumber);
    }
    @FXML
    private void update(ActionEvent event) {
        
        UserServices US = new UserServices();

  if (TextNom.getText().isEmpty() || TextPrenom.getText().isEmpty() || TextAge.getText().isEmpty() || TextNumber.getText().isEmpty()|| TextEmail.getText().isEmpty())
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        }
 else
        {
             User u ;
             if(UserSession.getRole().equals("Admin"))
             {
               u = new  Admin(UserSession.getId(),Integer.valueOf(TextAge.getText()),TextNom.getText(),TextPrenom.getText(),TextNumber.getText(),TextEmail.getText(),UserSession.getRole());
             }
             else if(UserSession.getRole().equals("Client"))
             {
                 u = new  Client(UserSession.getId(),Integer.valueOf(TextAge.getText()),TextNom.getText(),TextPrenom.getText(),TextNumber.getText(),TextEmail.getText(),UserSession.getRole());
             }
             else{
                 u = new  Livreur(UserSession.getId(),Integer.valueOf(TextAge.getText()),TextNom.getText(),TextPrenom.getText(),TextNumber.getText(),TextEmail.getText(),UserSession.getRole());
             }
                System.out.println("u= "+ u.getId());
           
            System.out.println("p = "+u.getId());
             US.modifierUser(u);
            JOptionPane.showMessageDialog(null, "User modifié avec succés");
            String pass=UserSession.getPassword();
             UserSession.cleanUserSession();
            UserSession userOnline = new UserSession(u.getId(),  u.getRole(), u.getEmail() , u.getName(), u.getPrenom(),  u.getNumber(),  u.getAge(), pass);
            UserSession.setInstance(userOnline);
        TextNom.setText(UserSession.getName());
        TextPrenom.setText(UserSession.getPrenom());
        TextEmail.setText(UserSession.getEmail());
        TextAge.setText(String.valueOf(UserSession.getAge()));
        TextNumber.setText(UserSession.getNumber());
        }
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
                    UserSession.cleanUserSession();

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
