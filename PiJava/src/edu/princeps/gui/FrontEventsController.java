package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import edu.princeps.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FrontEventsController implements Initializable {

    @FXML
    private GridPane eventsContainer;

    ServiceEvenement se = new ServiceEvenement();
    private List<Evenement> eventsList;
    private List<Evenement> temp;

    @FXML
    private ComboBox<String> sortComboBox;

    int column = 0;
    int row = 1;
    @FXML
    private Button btnRetour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill ComboBox
        sortComboBox.getItems().removeAll(sortComboBox.getItems());
        sortComboBox.getItems().addAll("Trier", "Trier par Nom ↑", "Trier par Nom ↓", "Trier par Date ↑", "Trier par Date ↓");
        sortComboBox.getSelectionModel().select("Trier");

        eventsList = se.getAll();
        setEventItems(eventsList);
    }

    @FXML
    private void selectSort(ActionEvent event) {
        String selected = sortComboBox.getSelectionModel().getSelectedItem();
        if (selected.equals("Trier par Nom ↑")) {
            column = 0;
            row = 1;
            eventsContainer.getChildren().clear();
            temp = se.sortNameAsc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Nom ↓")) {
            column = 0;
            row = 1;
            eventsContainer.getChildren().clear();
            temp = se.sortNameDesc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Date ↑")) {
            column = 0;
            row = 1;
            eventsContainer.getChildren().clear();
            temp = se.sortDateAsc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Date ↓")) {
            column = 0;
            row = 1;
            eventsContainer.getChildren().clear();
            temp = se.sortDateDesc();
            setEventItems(temp);
        }

    }

    private void setEventItems(List<Evenement> list) {
        try {
            for (Evenement event : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventItem.fxml"));
                AnchorPane itemBox = fxmlLoader.load();
                EventItemController itemController = fxmlLoader.getController();
                itemController.setData(event);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                eventsContainer.add(itemBox, column++, row);
                GridPane.setMargin(itemBox, new Insets(50));

            }
        } catch (IOException ex) {
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        System.out.println("tttt");
        Parent rootEv = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(true);
        window.show();
    }

}
