/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.gui;

import edu.princeps.entities.Evenement;
import edu.princeps.services.ServiceEvenement;
import edu.princeps.services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zribi
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart stats;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private CategoryAxis yAxis;

    ServiceEvenement se = new ServiceEvenement();
    ServicePromotion sp = new ServicePromotion();
    ObservableList<Evenement> eventsList = FXCollections.observableArrayList();

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventsList = se.getAll();
        for (Evenement e : eventsList) {
            pieChartData.add(new PieChart.Data(e.getNom(), sp.countPromotions(e.getId())));
        }
        stats.setData(pieChartData);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();

        for (Evenement e : eventsList) {
            dataSeries1.getData().add(new XYChart.Data(e.getNom(), sp.countPromotions(e.getId())));
        }

        barChart.getData().add(dataSeries1);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent rootEv = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));//eli heya category
        Scene gestionViewScene = new Scene(rootEv);
        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.setMaximized(true);
        window.show();
    }
}
