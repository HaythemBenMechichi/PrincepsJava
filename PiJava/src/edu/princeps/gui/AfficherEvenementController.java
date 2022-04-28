/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import edu.princeps.services.ServiceEvenement;
import edu.princeps.utils.Dbconnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    FileChooser fc = new FileChooser();
    String uploads = "C:\\Users\\zribi\\Desktop\\Pidev\\PiJava\\src\\img\\";
    private String path = "", imgname = "", fn = "";
    String name = null;
    Date dateDeb, dateFin = null;

    Mail mailClass = new Mail();

    @FXML
    private ImageView uploadIv;
    @FXML
    private Button btnUpload;
    @FXML
    private TextField ftsearch;
    ServiceEvenement UP = new ServiceEvenement();
    @FXML
    private Button btn_Stat;

    private ObservableList<Evenement> getTableList() {

        ObservableList<Evenement> List = UP.getAll();
        return List;
    }

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
        Parent rootEv = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
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
            name = tfNom.getText();
            dateDeb = Date.valueOf(tfDateDeb.getValue());
            dateFin = Date.valueOf(tfDateFin.getValue());
            se.ajouter(ev);
            JOptionPane.showMessageDialog(null, "Evénement ajouté avec succés");
            refreshData();
            clear();
            mailClass.sendMail(name, dateDeb, dateFin);
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

//   private void upload(ActionEvent event) throws IOException {
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        path = f.getAbsolutePath();
//        imgname = f.getName();
//        fn = imgname;
//        Image getAbsolutePath = null;
//
//        String dd = uploads + f.getName();
//        File dest = new File(dd);
//        this.copyFile(f, dest);
//
//        System.out.println(dd);
//
//        uploadIv.setImage(new Image("file:" + dest.getAbsolutePath()));
//    }
    @FXML
    private void upload(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        // Set the title of the displayed file dialog 
        fc.setTitle("Choisir une image");
        // Gets the extension filters used in the displayed file dialog. 
        fc.getExtensionFilters().clear();
        // Removes all of the elements from this list 
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        // Set the selected file or null if no file has been selected 
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname 
            uploadIv.setImage(new Image(file.toURI().toString()));

            imgname = file.getName();
            path = file.getAbsolutePath();
//            System.out.println("INITAL filename : " + filename);
//            System.out.println("filepath : " + filepath);

            fn = imgname;

            FileChannel source = new FileInputStream(path).getChannel();
            FileChannel dest = new FileOutputStream(uploads + name).getChannel();
            dest.transferFrom(source, 0, source.size());

        } else {
            System.out.println("Fichier invalide!");
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
    FilteredList<Evenement> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Evenement> sort = new SortedList<>(filter);

    @FXML
    private void search(KeyEvent event) {
        ftsearch.setOnKeyReleased(e -> {

            ftsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(t.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            sort.comparatorProperty().bind(TableEvenement.comparatorProperty());
            TableEvenement.setItems(sort);
        });

    }

    @FXML
    private void openStat(ActionEvent event) throws IOException {
        Parent rootEv = FXMLLoader.load(getClass().getResource("statistique.fxml"));
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(true);
        window.show();
    }

   /* @FXML
    private void exportExcel(ActionEvent event)throws SQLException, FileNotFoundException, IOException  {
         Connection con = Dbconnection.getInstance().getCnx();        
            String query = "Select * from evenement";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails Evenement");
            HSSFRow header = sheet.createRow(0);
           
           
           
            header.createCell(0).setCellValue("ID");
           header.createCell(1).setCellValue("nom");
            header.createCell(2).setCellValue("date_debut");
            header.createCell(3).setCellValue("date_fin");
             

           
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
               
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("nom"));
                row.createCell(2).setCellValue(rs.getDate("date_debut"));
                row.createCell(3).setCellValue(rs.getDate("date_fin"));
                index++;
            }
           
            FileOutputStream file = new FileOutputStream("C:\\Users\\zribi\\Desktop\\Evenement.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
           
            pst.close();
            rs.close();
   
    }
*/
        
    }


