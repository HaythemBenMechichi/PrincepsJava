/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import edu.princeps.services.ServiceEvenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zribi
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private Button btn_Retour;
    @FXML
    private TableView<Evenement> TableEvenement;
    @FXML
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> coldatedebut;
    @FXML
    private TableColumn<Evenement, String> coldatefin;
    @FXML
    private TableColumn<Evenement, String> colimage;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btnModifier;
    @FXML
    private DatePicker tfDateFin;
    @FXML
    private DatePicker tfDateDeb;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnReset;

    ServiceEvenement se = new ServiceEvenement();
    ObservableList listEvents = FXCollections.observableArrayList();
    int evID = 0;
    String uploads = "C:\\Users\\zribi\\Desktop\\Pidev\\PiJava\\src\\img\\";
    private String path = "", imgname = "", fn="";

    @FXML
    private ImageView uploadIv;
    @FXML
    private Button btnUpload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
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
        if (tfNom.getText() == null || tfDateDeb.getValue() == null || tfDateFin.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } else if (tfDateDeb.getValue().isAfter(tfDateFin.getValue()) || tfDateDeb.getValue().isBefore(LocalDate.now()) || tfDateFin.getValue().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les dates !");
        } else {
            Evenement ev = new Evenement(tfNom.getText(), Date.valueOf(tfDateDeb.getValue()), Date.valueOf(tfDateFin.getValue()), imgname);
            se.ajouter(ev);
            JOptionPane.showMessageDialog(null, "Evénement ajouté avec succés");
            refreshData();
            clear();
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (tfNom.getText() == null || tfDateDeb.getValue() == null || tfDateFin.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs !");
        } else if (tfDateDeb.getValue().isAfter(tfDateFin.getValue()) || tfDateDeb.getValue().isBefore(LocalDate.now()) || tfDateFin.getValue().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les dates !");
        } else {
            Evenement ev = new Evenement(evID, tfNom.getText(), Date.valueOf(tfDateDeb.getValue()), Date.valueOf(tfDateFin.getValue()), fn);
            se.modifier(ev);
            JOptionPane.showMessageDialog(null, "Evénement modifié avec succés");
            refreshData();
            clear();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Evenement e = TableEvenement.getSelectionModel().getSelectedItem();
        se.supprimer(e.getId());
        JOptionPane.showMessageDialog(null, "Evénement supprimé avec succés");
        refreshData();
        btnAjouter.setDisable(false);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        clear();
    }

    private void refreshData() {
        listEvents = se.getAll();
        TableEvenement.setItems(listEvents);
    }

    private void clear() {
        tfNom.clear();
        tfDateDeb.setValue(null);
        tfDateFin.setValue(null);
        uploadIv.setImage(null);
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        btnAjouter.setDisable(true);
        btnModifier.setDisable(false);
        btnSupprimer.setDisable(false);
        Evenement e = TableEvenement.getSelectionModel().getSelectedItem();
        evID = e.getId();
        fn = e.getImage();
        
        uploadIv.setImage(new Image("file:" + uploads + e.getImage()));
        
        tfNom.setText(e.getNom());
        tfDateDeb.setValue(e.getDateDebut().toLocalDate());
        tfDateFin.setValue(e.getDateFin().toLocalDate());
    }

    @FXML
    private void reset(ActionEvent event) {
        btnAjouter.setDisable(false);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        clear();
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
