package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EventItemController {

    String uploads = "C:\\Users\\zribi\\Desktop\\Pidev\\PiJava\\src\\img\\";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    @FXML
    private ImageView img;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDate;

    private Evenement event;

    public void setData(Evenement event) {
        this.event = event;
        labelName.setText(event.getNom());
        labelDate.setText("Du " + event.getDateDebut().toLocalDate().format(formatter).toUpperCase() + " Jusqu'à " + event.getDateFin().toLocalDate().format(formatter).toUpperCase());
        Image im = new Image("file:" + uploads + event.getImage());
        img.setImage(im);
    }

}
