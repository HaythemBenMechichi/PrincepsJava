/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Sous_categorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author haythem
 */
public class AfficheProduitController implements Initializable {

    @FXML
    private TableView<Produit> tableProd;
    @FXML
    private TableColumn<Produit,String> colLibelle;
    @FXML
    private TableColumn<Produit,Integer> colQuantite;
    @FXML
    private TableColumn<Produit,Float> colPrix;
    @FXML
    private TableColumn<Produit, Sous_categorie> colSous;
    @FXML
    private TableColumn<Produit, String> colDescription;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        affiche();
        
                         tableProd.refresh();

        // TODO
    }
    
    
    
    
    
        public ObservableList<Produit> getAllP() {
            
                Connection cnx = DataSource.getInstance().getCnx();

            ServicePersonne sp = new ServicePersonne(); 
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Sous_categorie s = new Sous_categorie();
                s = sp.getSousCat(rs.getInt("id_sous_cat_id"));
                System.out.println("s = "+s);
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
                System.out.println("p="+p);
                list.add(p);
            }
                  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    
    
    
    
    public void affiche() {
        
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colSous.setCellValueFactory(new PropertyValueFactory<>("sous"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
      ObservableList<Produit> obl =FXCollections.observableArrayList();
      obl=getAllP(); 
      tableProd.setItems(obl);
      System.out.println(""+obl);        
    }
    
}
