/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author haythem
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField tfCategorie;
    @FXML
    private Button btnAjouter;
    @FXML
    private ImageView imgCategorie;
    @FXML
    private Button btn_Retour;

      String uploads = "C:\\Users\\haythem\\Desktop\\PrincepsJava\\princeps\\src\\edu\\workshopjdbc3a48\\img";
    private String path = "", imgname = "", fn="";
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        imgCategorie.setImage(new Image("file:" + dest.getAbsolutePath()));
        
        
        
    }
    

    @FXML
    private void RetourMenu(ActionEvent event) throws IOException {
        
        Parent rootEv = FXMLLoader.load(getClass().getResource("AfficheProduit.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(false);
        window.show();
        
        
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) throws IOException {
    
    
    if (tfCategorie.getText().isEmpty() )
         {
            Alert a = new Alert(Alert.AlertType.ERROR, "remplir tous les champs", ButtonType.OK);
            a.showAndWait();
         }
         else
         {
             ServiceCategorie sp = new ServiceCategorie();
             Categorie p = new Categorie(tfCategorie.getText(),imgname);
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
    
}
