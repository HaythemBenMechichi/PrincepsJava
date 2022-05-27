/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import Controller.UserSession;
import edu.workshopjdbc3a48.entities.Commande;
import edu.workshopjdbc3a48.entities.CommandeDetails;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.services.Carte;
import edu.workshopjdbc3a48.services.ConcatPDFFiles;
import edu.workshopjdbc3a48.services.Pdf;

import edu.workshopjdbc3a48.services.ServiceCommande;
import edu.workshopjdbc3a48.services.ServiceCommandeDetails;
import edu.workshopjdbc3a48.services.ServiceRedCod;
import edu.workshopjdbc3a48.utils.EmailsUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EXTREME-GAMING
 */
public class CartController implements Initializable {

    @FXML
    private Label totalprice;

    @FXML
    private ListView<String> carte;
    private List<Produit> produits;
    @FXML
    Button plusButton;
    @FXML
    Button moinsButton;
    ServiceRedCod CouponService = new ServiceRedCod();
    ServiceCommande commandeService = new ServiceCommande();
    ServiceCommandeDetails commandeDetailService = new ServiceCommandeDetails();
    @FXML
    private Button btnCoupon;
    @FXML
    private TextField qte;
    int newQuantity;
    Float PrixTot = 0f;
    @FXML
    private TextField tfCoupon;
    @FXML
    private Button Btn_bc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produits = new ArrayList<>();
        produits = Carte.getPanier();
        Carte.getPanier().forEach(e -> {
            this.carte.getItems().add(e.getLibelle()+ e.getPrix()+ e.getQt());
        });

        carte.getSelectionModel().selectedItemProperty().addListener(lis -> {
            plusButton.setOnAction((ActionEvent plus) -> {
                int a = carte.getSelectionModel().getSelectedIndex();
                newQuantity = produits.get(a).getQt() + 1;
                float Prix = produits.get(a).getPrix();
                PrixTot = Prix * newQuantity;
                System.out.println(produits.get(a) + Integer.toString(newQuantity) + Float.toString(PrixTot) + "hhhhh");
                totalprice.setText(Float.toString(PrixTot));
                produits.get(a).setQt(newQuantity);
                System.out.println(produits.get(a) + "nouveeauuuu");
                System.out.println(newQuantity);
                qte.setText(Integer.toString(newQuantity));

            });
        });
        carte.getSelectionModel().selectedItemProperty().addListener(lis -> {
            moinsButton.setOnAction(moins -> {
                int a = carte.getSelectionModel().getSelectedIndex();
                newQuantity = produits.get(a).getQt() + 1;
                float Prix = produits.get(a).getPrix();
                PrixTot = Prix * newQuantity;
                newQuantity = produits.get(a).getQt() - 1;
                produits.get(a).setQt(newQuantity);
                System.out.println(produits.get(a) + "moins");
                System.out.println(newQuantity);
                qte.setText(Integer.toString(newQuantity));
                totalprice.setText(Float.toString(PrixTot));

            });

        });

        
    }

    @FXML
    public void validerPanier() throws IOException {

        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Commande c = new Commande(1, date, PrixTot);
            commandeService.addCommande(c);
            Commande d = commandeService.searchCommande(c);
            Pdf.TotaleCommande = PrixTot;

            Pdf pdf = new Pdf();
            pdf.createpdf();

            ConcatPDFFiles concatPdf = new ConcatPDFFiles();
           // concatPdf.concat();

            System.out.println(d);

            Carte.getPanier().forEach(x -> {
                try {
                    CommandeDetails cd = new CommandeDetails(x.getId(), d.getId(), x.getQt());
                    System.out.println(cd);
                    commandeDetailService.addCommandeDetails(cd);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("hello hanihn");
            String email_participent = UserSession.getEmail();
            System.out.println(UserSession.getEmail());
            EmailsUtils.sendMail(email_participent, "Thanks you purchased from our products in");
        } catch (Exception ex) {
        }

    }

    @FXML
    private void RedCod(ActionEvent event) {
        
        boolean c = CouponService.verif(tfCoupon.getText());
        if (c) {
                   PrixTot = PrixTot*0.3f;
                   totalprice.setText(String.valueOf(PrixTot));
                   btnCoupon.setDisable(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Invalid RedCod");
                    alert.showAndWait();
                }

    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("frontProduit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}