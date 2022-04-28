/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.princeps.entities.Evenement;
import edu.princeps.entities.EventProd;
import edu.princeps.services.ServicePromotion;
import edu.princeps.services.ServiceEvenement;
import edu.princeps.utils.Dbconnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    ObservableList<EventProd> listprod;
    int promID = 0;
    @FXML
    private Button btnAjouter1;
    @FXML
    private Button btnAjouter2;

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

    @FXML
    private void Btn_pdf(ActionEvent event) {
               Document doc = new Document();
        String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\zribi\\Desktop\\Pidev\\PiJava\\Planning.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Princeps");
            p.setAlignment(Element.ALIGN_CENTER);
            
            
            doc.add(p);
            PdfPTable table = new PdfPTable(2); // 2 columns.
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Nom du produit"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Taux"));
          
            cell1.setBackgroundColor(BaseColor.RED);
            cell2.setBackgroundColor(BaseColor.RED);
            cell1.setPadding(2);
            table.addCell(cell1);
            table.addCell(cell2);
            listprod = sp.getAll();
            for (int i=0; i<listprod.size();i++) {
                String nom=listprod.get(i).getNom_produit();
                String taux= listprod.get(i).getTaux().toString();

              
                table.addCell(nom);
                table.addCell(taux);
                
            }
            doc.add(table);
            
            Desktop.getDesktop().open(new File("C:\\Users\\zribi\\Desktop\\Pidev\\PiJava\\Planning.pdf"));
            
            doc.close();
            
            
        } catch (FileNotFoundException ex) {
        } catch (DocumentException ex) {
            Logger.getLogger(AfficherPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void imprimer(ActionEvent event) {
         // Création du job d'impression.
            final PrinterJob printerJob = PrinterJob.createPrinterJob();
           
            // Affichage de la boite de dialog de configation de l'impression.    
            if (printerJob.showPrintDialog(TablePromotion.getScene().getWindow())) {
                final JobSettings settings = printerJob.getJobSettings();
                final PageLayout pageLayout = settings.getPageLayout();
                final double pageWidth = pageLayout.getPrintableWidth();
                final double pageHeight = pageLayout.getPrintableHeight();
                System.out.println(pageWidth);
                System.out.println(Printer.getAllPrinters());
                // Mise en page, si nécessaire.
                // Lancement de l'impression.
                if (printerJob.printPage(pageLayout, TablePromotion)) {
                    // Fin de l'impression.
                    printerJob.endJob();
                }
            }        
    }

    @FXML
    private void exportExcel(ActionEvent event)throws SQLException, FileNotFoundException, IOException {
        Connection con = Dbconnection.getInstance().getCnx();        
            String query = "Select * from event_prod";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails Promotion");
            HSSFRow header = sheet.createRow(0);
           
           
           
            header.createCell(0).setCellValue("ID");
           header.createCell(1).setCellValue("ref_prod");
            header.createCell(2).setCellValue("evenement_id");
            header.createCell(3).setCellValue("nom_produit");
            header.createCell(4).setCellValue("taux");
             

           
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
               
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getInt("ref_prod"));
               row.createCell(2).setCellValue(rs.getInt("evenement_id"));
                row.createCell(3).setCellValue(rs.getString("nom_produit"));
                row.createCell(4).setCellValue(rs.getDouble("taux"));
                index++;
            }
           
            FileOutputStream file = new FileOutputStream("C:\\Users\\zribi\\Desktop\\Evenement.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
           
            pst.close();
            rs.close();
   
    }

}
