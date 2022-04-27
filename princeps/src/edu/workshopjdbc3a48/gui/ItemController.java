/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author haythem
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    private Produit Produit;
    private MyListener myListener;

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
    private void click(MouseEvent event) {
    
    myListener.onClickListener(Produit);
        
    }
    
    
    
     public void setData(Produit Produit, MyListener myListener) {
        this.Produit = Produit;
        this.myListener = myListener;
        nameLabel.setText(""+Produit.getLibelle());
        priceLable.setText(""+ Produit.getPrix());
      img.setImage(new Image("file:" + uploads + Produit.getImage_p()));
     }
    
}
