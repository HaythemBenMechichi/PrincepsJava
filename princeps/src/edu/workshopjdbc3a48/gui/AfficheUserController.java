/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Livreur;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.UserServices;


/**
 * FXML Controller class
 *
 * @author soon
 */
public class AfficheUserController implements Initializable {

    @FXML
    private TableView<User> Tusers;
    @FXML
    private TableColumn<User, String> ColNom;
    @FXML
    private TableColumn<User, String> Colprenom;
    @FXML
    private TableColumn<User, Integer> ColAge;
    @FXML
    private TableColumn<User, String> Colemail;
    @FXML
    private TableColumn<User, String> Colrole;
    @FXML
    private TableColumn<User, String> ColNumber;
    @FXML
    private TableColumn<User, Integer> ColId;
    @FXML
    private Label TFNOM;
    @FXML
    private TextField Tfnom;
    @FXML
    private TextField Tfprenom;
    @FXML
    private TextField Tfage;

    @FXML
    private TextField Tfemail;
    @FXML
    private TextField Tfnumber;
    @FXML
    private TextField Tfid;
    @FXML
    private ChoiceBox<String> chRole;

    /**
     * Initializes the controltabller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        chRole.getItems().add("Livreur");
        chRole.getItems().add("Client");
        chRole.getItems().add("Admin");
        
        Tusers.refresh();
    }  
     public ObservableList<User> getAllUser() throws SQLException {
           
            
        Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();
       
       
             String req="select id,age,nom,prenom,email,number,role from user";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
          ObservableList<User> list = FXCollections.observableArrayList();
                User u ;
                 while (res.next()) {
            if("['ROLE_ADMIN']".equals(res.getString("role")))
            {
                u = new Admin(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Admin");
            }
            else if("['ROLE_CLIENT']".equals(res.getString("role")))
            {
                u = new Client(res.getInt("id"),res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Client");
            }
            else
            {
                u = new Livreur(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Livreur");
            }
            list.add(u);
    
            
            }
        return list;
    }
   
   
    public void affiche() throws SQLException {   
        ColNom.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ColNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        Colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        Colrole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("Id"));
      ObservableList<User> obl =FXCollections.observableArrayList();
      obl=getAllUser();
      Tusers.setItems(obl);
      System.out.println(""+obl);        
    }

    

    @FXML
    private void Delete(ActionEvent event) {
                        Alert t = new Alert(Alert.AlertType.CONFIRMATION) ;
                    t.setTitle("Delete");
        t.setHeaderText(null);
        t.setContentText("confirmer de suprimer");
            t.showAndWait();
         System.out.println("teeeeessttt");
        UserServices p = new UserServices();
                User u ;
        u = Tusers.getSelectionModel().getSelectedItem();

        System.out.println("p"+u);
           
       
        System.out.println("id = " + u.getId());
       p.supprimerUser(u.getId());
           
       
        try {
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tusers.refresh();
      
            clear();
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
                 UserServices US = new UserServices();

  if (Tfnom.getText().isEmpty() || Tfprenom.getText().isEmpty() || Tfage.getText().isEmpty() || Tfnumber.getText().isEmpty()|| Tfemail.getText().isEmpty())
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        }
 else
        {
             User u ;
             if(chRole.getValue().equals("Admin"))
             {
               u = new  Admin(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfnumber.getText(),Tfemail.getText(),chRole.getValue());
             }
             else if(chRole.getValue().equals("Client"))
             {
                 u = new  Client(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfnumber.getText(),Tfemail.getText(),chRole.getValue());
             }
             else{
                 u = new  Livreur(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfnumber.getText(),Tfemail.getText(),chRole.getValue());
             }
                System.out.println("u= "+ u.getId());
           
            System.out.println("p = "+u.getId());
             US.modifierUser(u);
            JOptionPane.showMessageDialog(null, "User modifié avec succés");
            refreshData();
            clear();
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
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

    @FXML
    private void rowClicked(MouseEvent event) {
         User u = Tusers.getSelectionModel().getSelectedItem();
      //  fn = e.getImage();
      //  uploadIv.setImage(new Image("file:" + uploads + e.getImage()));
        Tfnom.setText(u.getName());
        Tfid.setText(""+(u.getId()));
        Tfprenom.setText(u.getPrenom());
        Tfage.setText(""+u.getAge());
        Tfemail.setText(u.getEmail());
        if(u.getRole().equals("['ROLE_ADMIN']"))
        {
            chRole.setValue("Admin");
        }
        else if(u.getRole().equals("['ROLE_CLIENT']"))
        {
        chRole.setValue("Client");
        }
        else
        {
            chRole.setValue("Livreur");
        }
       
        Tfnumber.setText(u.getNumber());
       // tfDateFin.setValue(e.getDateFin().toLocalDate());

    }
    private void refreshData() throws SQLException {
            ObservableList listUser = FXCollections.observableArrayList();
        listUser = getAllUser();
        Tusers.setItems(listUser);
    }
    private void clear() {
        Tfnom.clear();
        Tfid.clear();
        Tfprenom.clear();
        Tfage.clear();
        Tfemail.clear();
        
        Tfnumber.clear();
    }
}
