/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.Typereclamation;
import edu.workshopjdbc3a48.services.Iservices;
import edu.workshopjdbc3a48.services.ServiceReclamation;
import edu.workshopjdbc3a48.services.ServiceTypereclamation;
import edu.workshopjdbc3a48.utils.Mail;
import edu.workshopjdbc3a48.utils.TrayIconDemo;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReclamationFrontController implements Initializable {

    @FXML
    private TextField searchbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Pane scrol_right;
    @FXML
    private Button button_ajouter;
  
    @FXML
    private Button button_supprimer;
    @FXML
    private Label label_id;
    @FXML
    private ListView<AnchorPane> list_reclamation;
    @FXML
    private TextField sujet;
    @FXML
    private ComboBox<String> combo_niveau;
    @FXML
    private ComboBox<String> combo_type;
    int user_id=2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo_type.getItems().clear();
        combo_niveau.getItems().clear();
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ServiceTypereclamation S= new ServiceTypereclamation();
        List<Typereclamation> list_type_niveau = new ArrayList<>();
        try {
            list_type_niveau=S.afficher();
        } catch (SQLException ex) {

        }
        for (int i = 0; i < list_type_niveau.size(); i++) 
        {
            Typereclamation get = list_type_niveau.get(i);
            combo_type.getItems().add(get.getNiveau());
        }
        
        combo_niveau.getItems().add("Haut");
        combo_niveau.getItems().add("Moyenne");
        combo_niveau.getItems().add("Bas");

        
        
    }    

    @FXML
    private void rechercher(KeyEvent event)throws SQLException {
        this.list_reclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        ServiceReclamation Iu = new ServiceReclamation();
        System.out.println(Iu.recherche(searchbox.getText()));
        for(int i = 0; i < Iu.recherche(searchbox.getText()).size(); ++i) {
            Reclamation get = (Reclamation)Iu.recherche(searchbox.getText()).get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/edu/workshopjdbc3a48/gui/Unereclamation.fxml"));

            try {
                Parent var7 = (Parent)load.load();
            } catch (IOException var27) {
                System.out.println("errour");
            }

            UnereclamationController unereclamationController2 = (UnereclamationController)load.getController();
            AnchorPane p = new AnchorPane(new Node[]{unereclamationController2.getAnchor_reclamation()});
            
            unereclamationController2.setLabel_id(get.getId());
            unereclamationController2.setLabel_niveau(get.getNiveau());
            unereclamationController2.setLabel_sujet(get.getSujet_rec());
            unereclamationController2.setLabel_typereclamation(get.getTypereclamation_id());
            unereclamationController2.setLabel_userid(get.getUser_id());
            
            p.setId(Integer.toString(get.getId()));

            p.setStyle("-fx-backgound-color:blue;");
            
            pubss.add(p);
        }

        this.list_reclamation.getItems().addAll(pubss);
    }


    @FXML
    private void select(MouseEvent event) {
        
        label_id.setText((((AnchorPane) this.list_reclamation.getSelectionModel().getSelectedItem()).getId()));
        button_supprimer.setVisible(true);
        
        button_ajouter.setVisible(false);
        combo_type.setVisible(false);
        combo_niveau.setVisible(false);
        sujet.setVisible(false);
        sujet.clear();
        
    }

    @FXML
    private void ajouter_terminer(ActionEvent event) throws SQLException, IOException, Exception {

        int niveau=5;
        
        if(combo_niveau.getValue().equals("Haut"))
        {niveau=2;}
        else if(combo_niveau.getValue().equals("Moyenne"))
        {niveau=1;}
        else if(combo_niveau.getValue().equals("Bas"))
        {niveau=0;}
        
        if(niveau!=5 && isVerif(sujet.getText()) && sujet.getText().length()>10 && !sujet.getText().equals(""))
        {
        Iservices Is= new ServiceReclamation();
        ServiceTypereclamation St=new ServiceTypereclamation();

        Reclamation R=new Reclamation((St.getID(combo_type.getValue())).getId(),niveau,user_id,sujet.getText());
        Is.ajouter(R);
        
        afficher();
        
        button_ajouter.setVisible(false);
        combo_type.setVisible(false);
        combo_niveau.setVisible(false);
        sujet.setVisible(false);
        sujet.clear();
   
        button_supprimer.setVisible(false);
        TrayIconDemo td= new TrayIconDemo();
        try {
            td.displayTray();
        } catch (AWTException ex) {
        }
          Mail.sendMail("skander.laghmardi@esprit.tn");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Verifier les champs");
            a.show();
        }
    }

  
    
    
    @FXML
    private void supprimer_terminer(ActionEvent event) throws SQLException, IOException {
    Iservices US = new ServiceReclamation();
    US.supprimer(Integer.parseInt(label_id.getText()));
    button_supprimer.setVisible(false);

    label_id.setText("");
    afficher();
    
    }

    @FXML
    private void ajouter_reclamation(ActionEvent event) {
    combo_type.setVisible(true);
    combo_niveau.setVisible(true);
    sujet.setVisible(true);
    sujet.clear();
 
    button_supprimer.setVisible(false);
    button_ajouter.setVisible(true);
    
    }
    
    private void afficher() throws SQLException, IOException {
        this.list_reclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        ServiceReclamation Iu = new ServiceReclamation();


        for(int i = 0; i < Iu.afficherPourutilisateur(user_id).size(); ++i) {
            Reclamation get = (Reclamation)Iu.afficherPourutilisateur(user_id).get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/edu/workshopjdbc3a48/gui/UnereclamationFront.fxml"));

            try {
                Parent var7 = (Parent)load.load();
            } catch (IOException var27) {
                System.out.println("errour");
            }

            UnereclamationFrontController unereclamationController2 = (UnereclamationFrontController)load.getController();
            AnchorPane p = new AnchorPane(new Node[]{unereclamationController2.getAnchor_reclamation()});
            
            unereclamationController2.setLabel_niveau(get.getNiveau());
            unereclamationController2.setLabel_sujet(get.getSujet_rec());
            
            ServiceTypereclamation St= new ServiceTypereclamation();
            St.getTypereclamation(get.getTypereclamation_id());
            unereclamationController2.setLabel_typereclamation(St.getTypereclamation(get.getTypereclamation_id()).getNiveau());
            
            p.setId(Integer.toString(get.getId()));

            p.setStyle("-fx-backgound-color:blue;");
            
            pubss.add(p);
        }

        this.list_reclamation.getItems().addAll(pubss);
    }
 
    
    private boolean isVerif(String chaine){
    
        return chaine.matches("[a-zA-Z- 0-9]+");

    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
Parent rootEv = FXMLLoader.load(getClass().getResource("frontProduit.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
    }
}
