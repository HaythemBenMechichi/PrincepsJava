/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import edu.workshopjdbc3a48.utils.DataSource;


/**
 * FXML Controller class
 *
 * @author soon
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
       

        String email = tfEmail.getText();
        String pw = tfPassword.getText();
        

        
            String qry = "SELECT * FROM user WHERE email=\""+email+"\";";
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            if(pw.equals("") || email.equals(""))
            {
                Alert t = new Alert(Alert.AlertType.WARNING) ;
                    t.setTitle("LOGIN!!");
        t.setHeaderText(null);
        t.setContentText("wrong Login");
            t.showAndWait();
            }
                
            while(rs.next()){
                String hashed = BCrypt.hashpw(pw,  BCrypt.gensalt());
                if(BCrypt.checkpw(rs.getString("password"), hashed)){
                    Alert t = new Alert(Alert.AlertType.INFORMATION) ;
                    t.setTitle("LOGIN!!");
        t.setHeaderText(null);
        t.setContentText("Welcome");
            t.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheUser.fxml"));
        try {
            Parent root = loader.load();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
            }
             else
                {
                    Alert t = new Alert(Alert.AlertType.WARNING) ;
                    t.setTitle("wrong login !!");
        t.setHeaderText(null);
        t.setContentText("ERROR LORS DE CONNEXION");
            t.showAndWait();
                }

                
        }
    }

    

    @FXML
    private void SignUp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SignIn.fxml"));
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
