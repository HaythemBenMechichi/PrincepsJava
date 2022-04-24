/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
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
    private TextField tfDescription;
    @FXML
    private ChoiceBox<SousCategorie> chbCat;
    
                  ServicePersonne sp = new ServicePersonne();
    @FXML
    private TableColumn<Produit,Integer> colId;
    @FXML
    private TextField tfId;
    
    
    String uploads = "C:\\Users\\haythem\\Desktop\\PrincepsJava\\princeps\\src\\edu\\workshopjdbc3a48\\img";
    private String path = "", imgname = "", fn="";
    @FXML
    private ImageView uploadIv;
    @FXML
    private AnchorPane AncAll;
    @FXML
    private AnchorPane ancProduit;
    @FXML
    private AnchorPane ancSideNav;
    @FXML
    private AnchorPane ancCat;



    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
           ancProduit.setVisible(true);
          ancCat.setVisible(false);

        
        
               ServicePersonne sp = new ServicePersonne();
            ServiceCategorie cp = new ServiceCategorie();
                   List<SousCategorie> list = new ArrayList<>();
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
                SousCategorie s = new SousCategorie();
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
       fn = e.getImage_p();
       uploadIv.setImage(new Image("file:" + uploads + e.getImage_p()));
        tfId.setText(""+(e.getId()));
        tfLibelle.setText(e.getLibelle());
        tfDescription.setText(e.getDescription());
        tfQuantite.setText(""+e.getQuantite());
        tfPrix.setText(""+e.getPrix());
        chbCat.setValue(e.getA());
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
             Produit p = new Produit(Integer.valueOf(tfId.getText()),tfLibelle.getText(),Integer.valueOf(tfQuantite.getText()),tfDescription.getText(),fn,Float.valueOf(tfPrix.getText()),chbCat.getValue());
             System.out.println("p = "+p.getA().getId());
             sp.modifier(p);
             
             if(p.getQuantite()==0)
             {
                 
                 System.out.println("out of stock");
             }
             
             
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
    
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
         JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        uploadIv.setImage(new Image("file:" + dest.getAbsolutePath()));
        
    }

    @FXML
    private void ajoutwindow(ActionEvent event) throws IOException {
        
        
         Parent rootEv = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
        
        
    }

    @FXML
    private void changeCat(ActionEvent event) {
        
        
           ancProduit.setVisible(false);
          ancCat.setVisible(true);

        
    }

    @FXML
    private void changeProd(ActionEvent event) {
        
        
        
           ancProduit.setVisible(true);
          ancCat.setVisible(false);
        
    }
    
    
}
