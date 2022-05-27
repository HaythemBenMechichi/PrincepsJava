/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Commande;
import edu.workshopjdbc3a48.entities.CommandeDetails;
import edu.workshopjdbc3a48.services.ServiceCommande;
import edu.workshopjdbc3a48.services.ServiceCommandeDetails;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.text.Document;
import edu.workshopjdbc3a48.services.Pdf;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dhia werteteni
 */
public class OrderListController implements Initializable {

    @FXML
    private TableView<Commande> tab;
    @FXML
    private TableColumn<Commande, Integer> userTab;
    @FXML
    private TableColumn<Commande, Date> dateTab;
    @FXML
    private TableColumn<Commande, Float> prixTab;
    ServiceCommande SC = new ServiceCommande();
    @FXML
    private TextField tfQt;
    private TextField tfProduct;
    public int ID;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduit();
        
    }

    @FXML
    public void SetValue(MouseEvent event) {
        Commande selected = tab.getSelectionModel().getSelectedItem();
        ServiceCommandeDetails tabC = new ServiceCommandeDetails();
        if (selected != null) {
            try {
                CommandeDetails cd = tabC.searchCommandeDetails(selected.getId());
                System.out.println(cd);
                int prod = cd.getProduit();
                int qt = cd.getQuantity();
                tfQt.setText(String.valueOf(qt));
                ID = selected.getId();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OrderListController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void showProduit() {

        ObservableList<Commande> list = SC.getAllCommande();
        userTab.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateTab.setCellValueFactory(new PropertyValueFactory<>("date"));
        prixTab.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tab.setItems(list);
        System.out.println(list);
    }

    

    @FXML
    private void retour(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheProduit.fxml"));
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
