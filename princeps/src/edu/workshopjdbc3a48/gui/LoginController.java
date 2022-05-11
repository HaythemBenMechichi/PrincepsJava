/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import Controller.Activator;
import Controller.MaillerController;
import Controller.UserSession;
import edu.workshopjdbc3a48.entities.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


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
    private String code;
    
    @FXML
    private Label LabelCode;
    @FXML
    private TextField TfCode;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnConfirm;
    @FXML
    private Label labelConf;
    @FXML
    private Label labelEmail;
    @FXML
    private Label LabelPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, MessagingException, AddressException, IOException {
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
                
                if(BCrypt.checkpw(pw,rs.getString("password"))){
                     if(rs.getInt("ban")==0)
                {
                     
                     Alert t = new Alert(Alert.AlertType.INFORMATION) ;
                    t.setTitle("LOGIN!!");
        t.setHeaderText(null);
        t.setContentText("Welcome");
            t.showAndWait();
             
        LabelCode.setVisible(true);
        TfCode.setVisible(true);
        tfEmail.setVisible(false);
        tfPassword.setVisible(false);
        btnlogin.setVisible(false);
        btnConfirm.setVisible(true);
        labelConf.setVisible(true);
        labelEmail.setVisible(false);
        LabelPass.setVisible(false);
        
        
             code=Activator.get().generateCode();
        MaillerController.get().setupServerProperties();
       MaillerController.get().draftEmailAct(email,code);
        MaillerController.get().sendEmail();
       
        
            }
           
             else
                {
                    Alert t = new Alert(Alert.AlertType.WARNING) ;
                    t.setTitle("LOGIN!!");
        t.setHeaderText(null);
        t.setContentText("You're banned from the application !!");
            t.showAndWait();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
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
    private void Valider(ActionEvent event) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
        if(TfCode.getText().equals(code))
        {
            String email = tfEmail.getText();
        String pw = tfPassword.getText();
        

        
            String qry = "SELECT * FROM user WHERE email=\""+email+"\";";
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

           
                
            while(rs.next()){
                
             int   id = rs.getInt("id");
              String  role = rs.getString("role");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
                int age = rs.getInt("age");
                String password= rs.getString("password");
                String emaill = rs.getString("email");
                String number =rs.getString("number");
            
            UserSession.cleanUserSession();
            UserSession userOnline = new UserSession(id,  role,  emaill, nom,  prenom,  number,  age,  password);
            UserSession.setInstance(userOnline);
            if(rs.getString("role").equals("['ROLE_ADMIN']"))
            {
               
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheProduit.fxml"));
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
            else
            {
         
              
              
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frontProduit.fxml"));
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
            
        }
    }
    }
        
 
    
}
