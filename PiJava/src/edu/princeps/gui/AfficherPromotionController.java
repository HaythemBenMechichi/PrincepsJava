/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import edu.princeps.entities.EventProd;
import edu.princeps.services.ServicePromotion;
import edu.princeps.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zribi
 */
public class AfficherPromotionController implements Initializable {

    @FXML
    private Button btn_Retour;
    @FXML
    private TableView<EventProd> TablePromotion;
    @FXML
    private TableColumn<EventProd, Integer> colRefProd;
    @FXML
    private TableColumn<EventProd, String> colProd;
    @FXML
    private TableColumn<EventProd, Double> colTaux;
    @FXML
    private TableColumn<EventProd, Evenement> colEvenement;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTaux;
    @FXML
    private ComboBox<Evenement> tfEvenement;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfRefProd;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    ServicePromotion sp = new ServicePromotion();
    ObservableList listPromotions = FXCollections.observableArrayList();
    int promID = 0;
    @FXML
    private Button btnAjouter1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNumericConstraint();
        colProd.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        colRefProd.setCellValueFactory(new PropertyValueFactory<>("ref_produit"));
        colTaux.setCellValueFactory(new PropertyValueFactory<>("taux"));
        colEvenement.setCellValueFactory(new PropertyValueFactory<>("evenement_id"));
        refreshData();
        clear();
    }

    @FXML
    private void RetourMenu(ActionEvent event) throws IOException {
        Parent rootEv = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(true);
        window.show();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (tfNom.getText() == null || tfRefProd.getText() == null || tfTaux.getText() == null || tfEvenement.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } else {
            EventProd ev = new EventProd(tfNom.getText(), Integer.parseInt(tfRefProd.getText()), Double.parseDouble(tfTaux.getText()), tfEvenement.getValue());
            sp.ajouter(ev);
            JOptionPane.showMessageDialog(null, "Promotion ajoutée avec succés");
            refreshData();
            clear();
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (tfNom.getText() == null || tfRefProd.getText() == null || tfTaux.getText() == null || tfEvenement.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } else {
            EventProd ev = new EventProd(promID, tfNom.getText(), Integer.parseInt(tfRefProd.getText()), Double.parseDouble(tfTaux.getText()), tfEvenement.getValue());
            sp.modifier(ev);
            JOptionPane.showMessageDialog(null, "Promotion modifiée avec succés");
            refreshData();
            btnAjouter.setDisable(false);
            btnModifier.setDisable(true);
            btnSupprimer.setDisable(true);
            clear();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        sp.supprimer(promID);
        JOptionPane.showMessageDialog(null, "Promotion supprimée avec succés !");
        refreshData();
        btnAjouter.setDisable(false);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        clear();
    }

    private void refreshData() {
        listPromotions = sp.getAll();
        TablePromotion.setItems(listPromotions);
        showEvents();
    }

    private void clear() {
        tfNom.clear();
        tfRefProd.clear();
        tfTaux.clear();
        tfEvenement.setValue(null);
    }

    private void showEvents() {
        List<Evenement> listE = new ServiceEvenement().getAll();

        ArrayList<Evenement> libelles = new ArrayList<>();
        for (Evenement e : listE) {
            Evenement ev = new Evenement();
            ev.setId(e.getId());
            ev.setNom(e.getNom());
            libelles.add(ev);
        }
        ObservableList<Evenement> choices = FXCollections.observableArrayList(libelles);
        tfEvenement.setItems(choices);
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        btnAjouter.setDisable(true);
        btnModifier.setDisable(false);
        btnSupprimer.setDisable(false);
        EventProd e = TablePromotion.getSelectionModel().getSelectedItem();
        promID = e.getId();
        tfNom.setText(e.getNom_produit());
        tfRefProd.setText(String.valueOf(e.getRef_produit()));
        tfTaux.setText(String.valueOf(e.getTaux()));
        tfEvenement.setValue(e.getEvenement_id());
    }

    @FXML
    private void reset(ActionEvent event) {
        btnAjouter.setDisable(false);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        clear();
    }

    private void setNumericConstraint() {
        tfTaux.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfTaux.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        tfRefProd.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfRefProd.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
