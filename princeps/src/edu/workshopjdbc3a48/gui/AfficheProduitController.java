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
import edu.workshopjdbc3a48.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

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
    private TableColumn<Produit, String> colDescription;
    @FXML
    private Button suppButton;
    @FXML
    private Button btn_Retour;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfLibelle;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ChoiceBox<Sous_categorie> chbCat;
    
                  ServicePersonne sp = new ServicePersonne();
    @FXML
    private TableColumn<Produit,Integer> colId;
    @FXML
    private TextField tfId;


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
                Produit p = new Produit( rs.getInt("id"),rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
      ObservableList<Produit> obl =FXCollections.observableArrayList();
      obl=getAllP(); 
       System.out.println("ob1 = "+ obl);

      tableProd.setItems(obl);
      System.out.println(""+obl);        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        System.out.println("teeeeessttt");
        ServicePersonne Offres = new ServicePersonne();
        Produit p = new Produit();
        p = tableProd.getSelectionModel().getSelectedItem();
        System.out.println("p"+p);
        System.out.println("id = " + p.getId());
       Offres.supprimer(p.getId());
        affiche();
        tableProd.refresh();
    }

    @FXML
    private void RetourMenu(ActionEvent event) {
        
    }

    @FXML
    private void rowClicked(MouseEvent event) {
  
        Produit e = tableProd.getSelectionModel().getSelectedItem();
  
      //  fn = e.getImage();
        
      //  uploadIv.setImage(new Image("file:" + uploads + e.getImage()));
        tfId.setText(""+(e.getId()));
        tfLibelle.setText(e.getLibelle());
        tfDescription.setText(e.getDescription());
        tfQuantite.setText(""+e.getQuantite());
        tfPrix.setText(""+e.getPrix());
        chbCat.setValue(e.getA());
       // tfDateFin.setValue(e.getDateFin().toLocalDate());
    }

    @FXML
    private void modifier(ActionEvent event) {
    
              ServicePersonne sp = new ServicePersonne();

  if (tfLibelle.getText().isEmpty() || tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty() )
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } 
else
        {
//           Sous_categorie sous = new Sous_categorie(); 
//           sous = sp.getSousCatId(chbCat.getValue().getNom_sous());
//            
             Produit p = new Produit(Integer.valueOf(tfId.getText()),tfLibelle.getText(),Integer.valueOf(tfQuantite.getText()),tfDescription.getText(),"image",Float.valueOf(tfPrix.getText()),chbCat.getValue());
            System.out.println("p = "+p.getA().getId());
             sp.modifier(p);
            JOptionPane.showMessageDialog(null, "Produit modifié avec succés");
            refreshData();
            clear();
        }
    }

    private void refreshData() {
            ObservableList listProd = FXCollections.observableArrayList();
        listProd = getAllP();
        tableProd.setItems(listProd);


    }

    private void clear() {

        tfLibelle.clear();
        tfQuantite.clear();
        tfPrix.clear();
        tfDescription.clear();
    }
}
