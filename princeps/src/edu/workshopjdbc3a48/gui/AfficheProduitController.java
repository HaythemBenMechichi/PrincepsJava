/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;

import java.sql.Statement;


import javafx.scene.control.ChoiceBox;

import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;

















import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javax.swing.JFileChooser;



















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
    String upload = "C:\\Users\\haythem\\Desktop\\PrincepsJava\\princeps\\src\\edu\\workshopjdbc3a48\\";

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
    @FXML
    private TextField tfNomCat;
    @FXML
    private ImageView imageCat;
    @FXML
    private TableView<Categorie> tablecat;
    @FXML
    private TableView<SousCategorie> tableSousCat;
    @FXML
    private Button ajoutSousCat;
    @FXML
    private Button suppSousCat;
    @FXML
    private TableColumn<Categorie, Integer > colStatCat;
    @FXML
    private TableColumn<Categorie,String> colNomCat;
    @FXML
    private Button btnUploadCat;
    @FXML
    private Button btnUpdateCat;
    @FXML
    private TextField tfidCat;
    @FXML
    private TableColumn<SousCategorie, String> colNomSous;
    @FXML
    private TableColumn<SousCategorie, Integer> colStatSous;
    @FXML
    private TableColumn<SousCategorie, Integer> colIdSous;
    @FXML
    private TextField tfIdSous;



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
        afficheCat();
        tablecat.refresh();




       
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
          Image pho = new Image("file:" + upload + "noti.jpg" );

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
             
             
            refreshData();
            clear();
        }
  
  
   Notifications notificationBuilder = Notifications.create()
                                                     .title("offre Ajouter")
                                                      .text("offre ajouter avec succée")
                                                     .graphic(new ImageView(pho))
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_RIGHT) ;
         notificationBuilder.show();

  
    }
    
    
    
    
    
    
    private void refreshData() {
            ObservableList listProd = FXCollections.observableArrayList();
            ObservableList listCat = FXCollections.observableArrayList();

            listProd = getAllP();
        listCat = getAllC();
        tableProd.setItems(listProd);
        tablecat.setItems(listCat);
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

    
    
    
    
    
    
    
    //                                            CATEGORIE                                    //
    
    
    
    
    
    
    
    
    @FXML
    private void rawClickedCat(MouseEvent event) {
          Categorie e = tablecat.getSelectionModel().getSelectedItem();
       fn = e.getImage_car();
       imageCat.setImage(new Image("file:" + uploads + e.getImage_car()));
        tfidCat.setText(""+(e.getId()));
        tfNomCat.setText(e.getNom_c());
        
        
        colNomSous.setCellValueFactory(new PropertyValueFactory<>("nom_sous"));
        colStatSous.setCellValueFactory(new PropertyValueFactory<>("stat_sc"));
        colIdSous.setCellValueFactory(new PropertyValueFactory<>("id"));
      ObservableList<SousCategorie> obl =FXCollections.observableArrayList();
      obl= getAllSousCat(e); 
       System.out.println("ob1 = "+ obl);

      tableSousCat.setItems(obl);
      System.out.println(""+obl);    
        
    }

    @FXML
    private void suppCat(ActionEvent event) {
       System.out.println("teeeeessttt");
        ServiceCategorie Offres = new ServiceCategorie();
        Categorie p = new Categorie();
        p = tablecat.getSelectionModel().getSelectedItem();
        System.out.println("p"+p);
        System.out.println("id = " + p.getId());
       Offres.supprimer(p.getId());
        affiche();
        tablecat.refresh();
    }

    @FXML
    private void ajoutCat(ActionEvent event) throws IOException {
        Parent rootEv = FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
    }
    
    
    
    
    
    public void afficheCat() {      
        colNomCat.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
        colStatCat.setCellValueFactory(new PropertyValueFactory<>("stat_c"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
      ObservableList<Categorie> obl =FXCollections.observableArrayList();
      obl=getAllC(); 
       System.out.println("ob1 = "+ obl);

      tablecat.setItems(obl);
      System.out.println(""+obl);        
    }

    
    
    
    
        
           public ObservableList<Categorie> getAllC() {
            
                Connection cnx = DataSource.getInstance().getCnx();

            ServiceCategorie sp = new ServiceCategorie(); 
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                Categorie p = new Categorie( rs.getInt("id"),rs.getInt("stat_c"),rs.getString("nom_c"),rs.getString("image_car"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @FXML
    private void UploadCat(ActionEvent event) throws IOException {
        
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

        imageCat.setImage(new Image("file:" + dest.getAbsolutePath()));
        
    }

    @FXML
    private void updateCat(ActionEvent event) {
    
    
     ServiceCategorie sp = new ServiceCategorie();

  if (tfNomCat.getText().isEmpty() )
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } 
 else
        {
             Categorie p = new Categorie(Integer.valueOf(tfidCat.getText()),tfNomCat.getText(),fn);
             sp.modifier(p);
             
//             if(p.getQuantite()==0)
//             {   
//                 System.out.println("out of stock");
//             }
//             
            JOptionPane.showMessageDialog(null, "categorie modifié avec succés");
            refreshData();
            clear();
        }
  
    
    }
    
    
    
    
    
    //                                      SOUS 
    
    
           public ObservableList<SousCategorie> getAllSousCat(Categorie cat) {
            
                Connection cnx = DataSource.getInstance().getCnx();

            ServiceCategorie sp = new ServiceCategorie(); 
        ObservableList<SousCategorie> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from sous_categorie where sous_categorie.id_cat_id=" + cat.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie p = new SousCategorie( rs.getInt("id"),rs.getInt("stat_sc"),rs.getString("nom_sous"),cat);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @FXML
    private void clickedSous(MouseEvent event) {
        
        
           SousCategorie e = tableSousCat.getSelectionModel().getSelectedItem();
        tfIdSous.setText(""+(e.getId()));
    }

    @FXML
    private void suppSous(ActionEvent event) {  
         System.out.println("teeeeessttt");
        ServiceCategorie Offres = new ServiceCategorie();
        SousCategorie p = new SousCategorie();
        p = tableSousCat.getSelectionModel().getSelectedItem();
        System.out.println("p"+p);
        System.out.println("id = " + p.getId());
       Offres.supprimerSous(p.getId());
        tableSousCat.refresh();
        affiche();
        tablecat.refresh();

        
        
    }

    @FXML
    private void ajoutSous(ActionEvent event) throws IOException {
      Parent rootEv = FXMLLoader.load(getClass().getResource("AjoutSous.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();   
    }  
    
    
  

    @FXML
    private void printpdf(MouseEvent event) throws Exception {
        
        
         File out = new File("tableProduit.pdf") ;
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ;
        Page page = new Page(pdf, A4.PORTRAIT)  ;
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ;
        List<List<Cell>> tabledata = new ArrayList<List<Cell>>() ;      
        List<Cell> tablerow = new ArrayList<Cell>() ;    
        tablerow.add(        new Cell(f1,colLibelle.getText()) ) ;
        tablerow.add(        new Cell(f1,colDescription.getText())) ;
        tablerow.add(        new Cell(f1,colPrix.getText())) ;
        tabledata.add(tablerow) ;
   
   
     
   
   ServicePersonne ser= new ServicePersonne();
       
       
        List<Produit> li =ser.getAllASC();
  int i =0 ;
for ( i=0 ; i<li.size();i++){
        Cell prc = new Cell(f2,li.get(i).getLibelle()) ;
        Cell gc = new Cell(f2,li.get(i).getDescription()) ;
        Cell lc = new Cell(f2,String.valueOf(li.get(i).getPrix())) ;
       
        tablerow = new ArrayList<Cell>() ;
              tablerow.add(prc) ; tablerow.add(gc) ; tablerow.add(lc)  ;
       
    tabledata.add(tablerow) ;
    table.setData(tabledata);
    table.setPosition(10f, 60f);
    table.setColumnWidth(0, 90f);
    table.setColumnWidth(1, 90f);
    table.setColumnWidth(2, 90f);
   }
   
   
    while(true){
    table.drawOn(page) ;
    if(!table.hasMoreData()){
    table.resetRenderedPagesCount();
    break ;  
   
    }
   
    page=new Page(pdf,A4.PORTRAIT) ;
   
   
    }
   
    pdf.flush();
    fos.close();
        System.out.println("saved to "+out.getAbsolutePath());
       
        
    }

    
}