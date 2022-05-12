/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import Controller.Activator;
import Controller.MaillerController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.UserServices;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.mindrot.jbcrypt.BCrypt;
/**
 * FXML Controller class
 *
 * @author soon
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumber;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirmpassword;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) throws MessagingException, AddressException, IOException {
        String Nom=tfNom.getText();
         String Prenom=tfPrenom.getText();
          int Age=0;
         String Email=tfEmail.getText();
            String Number=tfNumber.getText();
            String Password=tfPassword.getText();
            String CPassword=tfConfirmpassword.getText();

            
            if(Nom.equals("") || Prenom.equals("") || Email.equals("") ||   CPassword.equals("")|| Password.equals("")|| !(Password.equals(CPassword)))
            {
                 Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Missing Fields");
                  t.showAndWait();
            }
            else if(checkIfStringContainsString(Nom) || Nom.length()<5)
            {
                Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Le nom doit Etre sup a 5");
                  t.showAndWait();
            }
            else if(checkIfStringContainsString(Prenom) || Prenom.length()<3)
            {
                Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Le prenom doit Etre sup a 3");
                  t.showAndWait();
            }
            else if( Email.indexOf("@")==-1)
            {
                Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Le Email doit contenir @ ");
                  t.showAndWait();
            }
            else if(checkIfStringContainsNumber(Number) || Number.length()<8)
            {
                Alert t = new Alert(Alert.AlertType.WARNING) ;
                  t.setTitle("Missing Fields");
                  t.setHeaderText(null);
                  t.setContentText("Le Numero ne doit pas avoir des lettres ou il manque ");
                  t.showAndWait();
            }
            
            else
            { 
                Age=Integer.parseInt(tfAge.getText());
                String hashed = BCrypt.hashpw(Password,  BCrypt.gensalt());
                
                User u= new Client( Age,Nom, Prenom,  Email,Number,"Client", hashed);
                 UserServices p = new UserServices();
                 p.ajouterUser(u);
                 
        
                
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
                   
        try {
            Parent root = loader.load();
            ProfilController Profil = new ProfilController();
            
            //
            tfNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }
                    
                
            
           
            
    }
    @FXML
    private void btnlogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            Parent root = loader.load();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }
 
    public boolean checkIfStringContainsString(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }

}
