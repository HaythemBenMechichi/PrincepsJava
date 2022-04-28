/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Livreur;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.services.UserServices;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    @FXML
    private TableView<User> Tusers;
    @FXML
    private TableColumn<User, String> ColNom;
    @FXML
    private TableColumn<User, String> Colprenom;
    @FXML
    private TableColumn<User, Integer> ColAge;
    @FXML
    private TableColumn<User, String> Colemail;
    @FXML
    private TableColumn<User, String> Colrole;
    @FXML
    private TableColumn<User, String> ColNumber;
    @FXML
    private TableColumn<User, Integer> ColId;
    @FXML
    private Label TFNOM;
    @FXML
    private TextField Tfnom;
    @FXML
    private TextField Tfprenom;
    @FXML
    private TextField Tfage;
    @FXML
    private TextField Tfemail;
    @FXML
    private TextField Tfnumber;
    @FXML
    private TextField Tfid;
    @FXML
    private ChoiceBox<String> chRole;
    @FXML
    private AnchorPane ancuser;
    
    @FXML
    private ChoiceBox<String> chRoleRech;



    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
           ancProduit.setVisible(true);
          ancCat.setVisible(false);
          ancuser.setVisible(false);

        
        
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
          ancuser.setVisible(false);

        
    }

    @FXML
    private void changeProd(ActionEvent event) {
        
        
        
           ancProduit.setVisible(true);
          ancCat.setVisible(false);
          ancuser.setVisible(false);
        
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
                                    //User
    
    @FXML
    private void Userfen(ActionEvent event) throws SQLException {
        ancProduit.setVisible(false);
          ancCat.setVisible(false);
        ancuser.setVisible(true);
        afficheUser();
        
        chRoleRech.getItems().add("Livreur");
        chRoleRech.getItems().add("Client");
        chRoleRech.getItems().add("Admin");
        
        
        chRole.getItems().add("Livreur");
        chRole.getItems().add("Client");
        chRole.getItems().add("Admin");
        
        Tusers.refresh();
    }

    @FXML
    private void Delete(ActionEvent event) {
         Alert t = new Alert(Alert.AlertType.CONFIRMATION) ;
                    t.setTitle("Delete");
        t.setHeaderText(null);
        t.setContentText("confirmer de suprimer");
            t.showAndWait();
         System.out.println("teeeeessttt");
        UserServices p = new UserServices();
                User u ;
        u = Tusers.getSelectionModel().getSelectedItem();

        System.out.println("p"+u);
           
       
        System.out.println("id = " + u.getId());
       p.supprimerUser(u.getId());
           
       
        try {
            afficheUser();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tusers.refresh();
      
            clearUser();
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
         UserServices US = new UserServices();

  if (Tfnom.getText().isEmpty() || Tfprenom.getText().isEmpty() || Tfage.getText().isEmpty() || Tfnumber.getText().isEmpty()|| Tfemail.getText().isEmpty())
  {    
      JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        }
 else
        {
             User u ;
             if(chRole.getValue().equals("Admin"))
             {
               u = new  Admin(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfemail.getText(),Tfnumber.getText(),"[\\'ROLE_ADMIN\\']");
             }
             else if(chRole.getValue().equals("Client"))
             {
                 u = new  Client(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfemail.getText(),Tfnumber.getText(),"[\\'ROLE_CLIENT\\']");
             }
             else{
                 u = new  Livreur(Integer.valueOf(Tfid.getText()),Integer.valueOf(Tfage.getText()),Tfnom.getText(),Tfprenom.getText(),Tfemail.getText(),Tfnumber.getText(),"[\\'ROLE_LIVREUR\\']");
             }
                System.out.println("u= "+ u.getId());
           
            System.out.println("p = "+u.getId());
             US.modifierUser(u);
            JOptionPane.showMessageDialog(null, "User modifié avec succés");
            refreshDataUser();
            clearUser();
        }
    }
     public ObservableList<User> getAllUser() throws SQLException {
           
            
        Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();
       
       
             String req="select id,age,nom,prenom,email,number,role from user";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
          ObservableList<User> list = FXCollections.observableArrayList();
                User u ;
                 while (res.next()) {
            if("['ROLE_ADMIN']".equals(res.getString("role")))
            {
                
                u = new Admin(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Admin");
            }
            else if("['ROLE_CLIENT']".equals(res.getString("role")))
            {
                u = new Client(res.getInt("id"),res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Client");
            }
            else
            {
                u = new Livreur(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Livreur");
            }
            list.add(u);
    
            
            }
        return list;
    }
      public void afficheUser() throws SQLException {   
        ColNom.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ColNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        Colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        Colrole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("Id"));
      ObservableList<User> obl =FXCollections.observableArrayList();
      obl=getAllUser();
      Tusers.setItems(obl);
      System.out.println(""+obl);        
    }
      private void refreshDataUser() throws SQLException {
            ObservableList listUser = FXCollections.observableArrayList();
        listUser = getAllUser();
        Tusers.setItems(listUser);
    }
      private void clearUser() {
        Tfnom.clear();
        Tfid.clear();
        Tfprenom.clear();
        Tfage.clear();
        Tfemail.clear();
        
        Tfnumber.clear();
    }

    @FXML
    private void LogOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            Parent root = loader.load();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }

    @FXML
    private void rowClickedTUser(MouseEvent event) {
         User u = Tusers.getSelectionModel().getSelectedItem();
      //  fn = e.getImage();
      //  uploadIv.setImage(new Image("file:" + uploads + e.getImage()));
        Tfnom.setText(u.getName());
        Tfid.setText(""+(u.getId()));
        Tfprenom.setText(u.getPrenom());
        Tfage.setText(""+u.getAge());
        Tfemail.setText(u.getEmail());
        if(u.getRole().equals("Admin"))
        {
            chRole.setValue("Admin");
        }
        else if(u.getRole().equals("Client"))
        {
        chRole.setValue("Client");
        }
        else
        {
            chRole.setValue("Livreur");
        }
       
        Tfnumber.setText(u.getNumber());
       // tfDateFin.setValue(e.getDateFin().toLocalDate());
    }

    @FXML
    private void banUser(ActionEvent event) throws SQLException {
        Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();
       User u ;
       u = Tusers.getSelectionModel().getSelectedItem();
             String req="UPDATE `user` SET  `ban` =1 where `user`.`id`="+u.getId();
           Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user banned");
    }

    @FXML
    private void unban(ActionEvent event) throws SQLException {
        Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();
       User u ;
       u = Tusers.getSelectionModel().getSelectedItem();
             String req="UPDATE `user` SET  `ban` =0 where `user`.`id`="+u.getId();
           Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user banned");
        } catch (SQLException ex) {
            
        }
            
            
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        //Create Stage
Stage newWindow = new Stage();
newWindow.setTitle("STAT DES ROLES");
//Create view from FXML
FXMLLoader loader = new FXMLLoader(getClass().getResource("PieChartRole.fxml"));
//Set view in window
newWindow.setScene(new Scene(loader.load()));
//Launch
newWindow.show();
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
         ObservableList<User>  list =  FXCollections.observableArrayList();
          try {
            Connection cnx = DataSource.getInstance().getCnx();
            String text ;
            if(chRoleRech.getValue().equals("Admin"))
             {
               text="[\\'ROLE_ADMIN\\']";
             }
             else if(chRoleRech.getValue().equals("Client"))
             {
                text="[\\'ROLE_CLIENT\\']";
             }
             else{
                text="[\\'ROLE_LIVREUR\\']";
             }
            
            ResultSet res = cnx.createStatement().executeQuery("SELECT * FROM user where role ='" + text + "'");
            
                 User u ;
                 while (res.next()) {
            if("['ROLE_ADMIN']".equals(res.getString("role")))
            {
                
                u = new Admin(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Admin");
            }
            else if("['ROLE_CLIENT']".equals(res.getString("role")))
            {
                u = new Client(res.getInt("id"),res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Client");
            }
            else
            {
                u = new Livreur(res.getInt("id"), res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),"Livreur");
            }
            list.add(u);
                 
        }
          }catch (SQLException ex) {
           System.out.println(ex);
        }
afficheUser();
     
 Tusers.setItems(list);
  Tusers.refresh();

    }
}