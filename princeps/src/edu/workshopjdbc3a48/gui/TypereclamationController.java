/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.workshopjdbc3a48.entities.Typereclamation;
import edu.workshopjdbc3a48.services.Iservices;
import edu.workshopjdbc3a48.services.ServiceTypereclamation;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class TypereclamationController implements Initializable {

    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField searchbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ListView<AnchorPane> list_typereclamation;
    @FXML
    private Pane scrol_right;
    @FXML
    private Button button_ajouter;
    @FXML
    private Button button_modifier;
    @FXML
    private Button button_supprimer;
    @FXML
    private TextField type_niveau;
    @FXML
    private Label label_id;
    @FXML
    private Label button_to_reclamation;
    @FXML
    private TextField type_image;
    @FXML
    private Button type_button_image;
    FileChooser fileChooser = new FileChooser();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(TypereclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TypereclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    



    @FXML
    private void ajouter_type(ActionEvent event) {
        
        type_niveau.clear();
        type_niveau.setVisible(true); 
        button_ajouter.setVisible(true);
        button_modifier.setVisible(false);
        button_supprimer.setVisible(false);
        type_image.setVisible(true);
        type_button_image.setVisible(true);
        label_id.setVisible(false);
    }


    @FXML
    private void select(MouseEvent event) throws SQLException {
        
        System.out.println(((AnchorPane) this.list_typereclamation.getSelectionModel().getSelectedItem()).getId());

        ServiceTypereclamation Su = new ServiceTypereclamation();

        Typereclamation U = Su.getTypereclamation(Integer.parseInt(((AnchorPane) this.list_typereclamation.getSelectionModel().getSelectedItem()).getId()));

        type_niveau.setText(U.getNiveau());
        label_id.setText(Integer.toString(U.getId()));
        type_image.setText(U.getImage());
        label_id.setVisible(true);
        type_niveau.setVisible(true);
        button_modifier.setVisible(true);
        button_supprimer.setVisible(true);
        button_ajouter.setVisible(false);
        type_image.setVisible(true);
        type_button_image.setVisible(true);
    
    }

    @FXML
    private void ajouter_terminer(ActionEvent event) throws SQLException, IOException {
        
        if(isAlpha(type_niveau.getText()) && type_niveau.getText().length()>1)
        {
        Iservices US = new ServiceTypereclamation();
        Typereclamation T=new Typereclamation();
        T.setNiveau(type_niveau.getText());
        T.setImage(type_image.getText());
        US.ajouter(T);    
        
        button_supprimer.setVisible(false);
        button_modifier.setVisible(false);
        button_ajouter.setVisible(false);
        type_image.setVisible(false);
        type_button_image.setVisible(false);
        label_id.setVisible(false);
        type_niveau.setVisible(false);
        afficher();}
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Verifier les champs");
            a.show();
        }
    }

    @FXML
    private void modifier_terminer(ActionEvent event) throws SQLException, IOException {
        
        Iservices US = new ServiceTypereclamation();
        Typereclamation T=new Typereclamation();
        T.setId(Integer.parseInt(label_id.getText()));
        T.setImage(type_image.getText());
        T.setNiveau(type_niveau.getText());
        US.modifier(T);
        button_supprimer.setVisible(false);
        button_modifier.setVisible(false);
        button_ajouter.setVisible(false);
        type_image.setVisible(false);
        type_button_image.setVisible(false);
        label_id.setVisible(false);
        type_niveau.setVisible(false);
        afficher();
    }

    @FXML
    private void supprimer_terminer(ActionEvent event) throws SQLException, IOException {
        
        Iservices US = new ServiceTypereclamation();
        US.supprimer(Integer.parseInt(((AnchorPane) this.list_typereclamation.getSelectionModel().getSelectedItem()).getId()));
        button_supprimer.setVisible(false);
        button_modifier.setVisible(false);
        button_ajouter.setVisible(false);
        type_image.setVisible(false);
        type_button_image.setVisible(false);
        label_id.setVisible(false);
        type_niveau.setVisible(false);
        afficher();
    }

    @FXML
    private void to_menu_back(ActionEvent event) {
    }

    @FXML
    private void to_Reclamation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
        fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    
    private void afficher() throws SQLException, IOException {
        this.list_typereclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        Iservices Iu = new ServiceTypereclamation();
        System.out.println(Iu.afficher());

        for(int i = 0; i < Iu.afficher().size(); ++i) {
            Typereclamation get = (Typereclamation)Iu.afficher().get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("Unetype.fxml"));

            try {
                Parent var7 = (Parent)load.load();
            } catch (IOException var27) {
                System.out.println("errour");
            }

            UnetypeController unetypeController2 = (UnetypeController)load.getController();
            AnchorPane p = new AnchorPane(new Node[]{unetypeController2.getAnchor_type()});
            
            unetypeController2.setLabel_id(get.getId());
            unetypeController2.setLabel_niveau(get.getNiveau());
            unetypeController2.setIMG(get.getImage());

           
            
            p.setId(Integer.toString(get.getId()));

            p.setStyle("-fx-backgound-color:blue;");
            
            pubss.add(p);
        }

        this.list_typereclamation.getItems().addAll(pubss);
    }
    
    private boolean isAlpha(String chaine){
    
        return chaine.matches("[a-zA-Z- -]+");

    }

    @FXML
    private void Choser_image(ActionEvent event) {
        
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("image", new String[]{"*.png"});
        this.fileChooser.getExtensionFilters().add(fileExtensions);
        File file = this.fileChooser.showOpenDialog(new Stage());
        System.out.println(file.toURI().toString());
        this.type_image.setText(file.getName());
    }
 
    
        @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        this.list_typereclamation.getItems().clear();
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        
        ServiceTypereclamation Iu = new ServiceTypereclamation();
        System.out.println(Iu.recherche(searchbox.getText()));
        for(int i = 0; i < Iu.recherche(searchbox.getText()).size(); ++i) {
            Typereclamation get = (Typereclamation)Iu.recherche(searchbox.getText()).get(i);
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("Unetype.fxml"));

            try {
                Parent var7 = (Parent)load.load();
            } catch (IOException var27) {
                System.out.println("erreur");
            }

            UnetypeController unereclamationController3 = (UnetypeController)load.getController();
            AnchorPane p = new AnchorPane(new Node[]{unereclamationController3.getAnchor_type()});
            
            unereclamationController3.setLabel_id(get.getId());
            unereclamationController3.setLabel_niveau(get.getNiveau());
            unereclamationController3.setIMG(get.getImage());
            
            
            p.setId(Integer.toString(get.getId()));

            p.setStyle("-fx-backgound-color:blue;");
            
            pubss.add(p);
    }
    this.list_typereclamation.getItems().addAll(pubss);
    
    
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
