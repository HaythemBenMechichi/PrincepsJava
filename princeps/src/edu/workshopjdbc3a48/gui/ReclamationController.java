/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.services.Iservices;
import edu.workshopjdbc3a48.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField searchbox;
    @FXML
    private Hyperlink button_to_typereclamation;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Pane scrol_right;
    @FXML
    private Button button_supprimer;
    @FXML
    private Label label_id;
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private ListView<AnchorPane> list_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        
        this.list_reclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        ServiceReclamation Iu = new ServiceReclamation();
        System.out.println(Iu.recherche(searchbox.getText()));
        for(int i = 0; i < Iu.recherche(searchbox.getText()).size(); ++i) {
            Reclamation get = (Reclamation)Iu.recherche(searchbox.getText()).get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("Unereclamation.fxml"));

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
    private void to_Typereclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Typereclamation.fxml"));
        fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        
    }

    @FXML
    private void select(MouseEvent event) {
        

        label_id.setText((((AnchorPane) this.list_reclamation.getSelectionModel().getSelectedItem()).getId()));

        label_id.setVisible(true);
        button_supprimer.setVisible(true);

    
    }

    @FXML
    private void supprimer_terminer(ActionEvent event) throws SQLException, IOException {
    Iservices US = new ServiceReclamation();
    US.supprimer(Integer.parseInt(label_id.getText()));
    button_supprimer.setVisible(false);

    label_id.setText("");
    label_id.setVisible(false);
    afficher();
    
    }

    
    
    private void afficher() throws SQLException, IOException {
        this.list_reclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        Iservices Iu = new ServiceReclamation();
        System.out.println(Iu.afficher());
        for(int i = 0; i < Iu.afficher().size(); ++i) {
            Reclamation get = (Reclamation)Iu.afficher().get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("Unereclamation.fxml"));

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
    private void to_stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
        fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void to_menu_back(ActionEvent event) {
    }

    @FXML
    private void trier(ActionEvent event) throws SQLException {
        this.list_reclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        ServiceReclamation Iu = new ServiceReclamation();
        System.out.println(Iu.trier());
        for(int i = 0; i < Iu.trier().size(); ++i) {
            Reclamation get = (Reclamation)Iu.trier().get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("Unereclamation.fxml"));

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
    private void retour(ActionEvent event) throws IOException {
Parent rootEv = FXMLLoader.load(getClass().getResource("AfficheProduit.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
    }
}
