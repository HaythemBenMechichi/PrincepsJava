/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.MyListener;
import sun.applet.Main;

/**
 * FXML Controller class
 *
 * @author haythem
 */
public class FrontProduitController implements Initializable {
    
     
/**
     * Initializes the controller class.
     */
    
    
      List<Produit> Produit = new ArrayList<>();
        private Image image;
        ServicePersonne sp = new ServicePersonne();
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;
     String uploads = "C:\\Users\\haythem\\Desktop\\PrincepsJava\\princeps\\src\\edu\\workshopjdbc3a48\\img";
    @FXML
    private Label DESC;

                
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       Produit = sp.getAll();
        
        if (Produit.size() > 0) {
            setChosenFruit(Produit.get(0));
            
            myListener = new MyListener() {
            @Override
                public void onClickListener(Produit Produit) {
                    setChosenFruit(Produit);
                }     
            };
        }   
        
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Produit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(Produit.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  private void setChosenFruit(Produit Produit) {
        fruitNameLable.setText(Produit.getLibelle());
        fruitPriceLabel.setText("" + Produit.getPrix());
        DESC.setText(Produit.getDescription());
     fruitImg.setImage(new Image("file:" + uploads + Produit.getImage_p()));
    }
}
