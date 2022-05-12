/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UnetypeController implements Initializable {

    @FXML
    private Label label_id;
    @FXML
    private Label label_niveau;
    @FXML
    private AnchorPane anchor_type;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public AnchorPane getAnchor_type() {
        return anchor_type;
    }

    public void setAnchor_type(AnchorPane anchor_type) {
        this.anchor_type = anchor_type;
    }

    public void setLabel_id(int label_id) {
        this.label_id.setText(Integer.toString(label_id));
    }

    public void setLabel_niveau(String label_niveau) {
        this.label_niveau.setText(label_niveau);
    }
    public void setIMG(String img){
        Image image = new Image(getClass().getResourceAsStream("/edu/workshopjdbc3a48/gui/"+img));
        this.imageview.setImage(image);
    }
}
