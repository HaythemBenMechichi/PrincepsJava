/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Sous_categorie;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

/**
 * FXML Controller class
 *
 * @author haythem
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfLibelle;
    @FXML
    private TextField tfPrix;
    @FXML
    private ChoiceBox<Sous_categorie> chbCat;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ImageView imgview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
           ServicePersonne sp = new ServicePersonne();
            ServiceCategorie cp = new ServiceCategorie();
            
                   List<Sous_categorie> list = new ArrayList<>();
                   list= cp.geAllSousCat();
                    for (int i = 0; i < list.size(); i++) {
                    chbCat.getItems().add(list.get(i));
                    }
            
        // TODO 
    }    
    
    @FXML
    private void ajouterProduit(ActionEvent event) {
  
  if (tfLibelle.getText().isEmpty() || tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty() )
         {
            Alert a = new Alert(Alert.AlertType.ERROR, "remplir tous les champs", ButtonType.OK);
            a.showAndWait();
         }
         else
         {
             ServicePersonne sp = new ServicePersonne();
             Produit p = new Produit(tfLibelle.getText(),Integer.parseInt(tfQuantite.getText()),tfDescription.getText(),"image",Integer.parseInt(tfPrix.getText()),chbCat.getValue());
             sp.ajout(p);
        
        }
    }

    @FXML
    private void imageclick(MouseEvent event) {
  
    
    }
 
    
}
