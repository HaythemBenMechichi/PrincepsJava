/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import edu.workshopjdbc3a48.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> StatistiqueChart;
    @FXML
    private NumberAxis v;
    @FXML
    private CategoryAxis x;
    private Parent fxml;
    private Scene scene;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceReclamation Sr=new ServiceReclamation();
;
        
        XYChart.Series set1=new XYChart.Series<>();
    
        try {
            set1.getData().add(new XYChart.Data("Bas",Sr.CountN0()));
        } catch (SQLException ex) {

        }
        try {
            set1.getData().add(new XYChart.Data("Moyenne",Sr.CountN1()));
        } catch (SQLException ex) {

        }
        try {
            set1.getData().add(new XYChart.Data("Haut",Sr.CountN2()));
        } catch (SQLException ex) {

        }

        StatistiqueChart.getData().addAll(set1);
    }    

    @FXML
    private void to_reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/Reclamation.fxml"));
        fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
}
