/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author haythem
 */
public class AjoutSousController implements Initializable {

    @FXML
    private TextField tfSous;
    @FXML
    private ChoiceBox<Categorie> chbCat;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btn_Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      ServiceCategorie sp = new ServiceCategorie();
            ServiceCategorie cp = new ServiceCategorie();
                   List<Categorie> list = new ArrayList<>();
                   list= cp.getAll();
                    for (int i = 0; i < list.size(); i++) {
                    chbCat.getItems().add(list.get(i));
       }
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
        
         if (tfSous.getText().isEmpty()  )
         {
            Alert a = new Alert(Alert.AlertType.ERROR, "remplir tous les champs", ButtonType.OK);
            a.showAndWait();
         }
         else
         {
             ServicePersonne sp = new ServicePersonne();
             SousCategorie p = new SousCategorie(0,tfSous.getText(),chbCat.getValue());
             System.out.println(" p = "+p);
             sp.ajouter(p);
   Parent rootEv = FXMLLoader.load(getClass().getResource("AfficheProduit.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
        

        }
  
  
  
  
        
    }

    @FXML
    private void RetourMenu(ActionEvent event) {
    }
    
}
