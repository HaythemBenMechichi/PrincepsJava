/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author soon
 */
public class PieChartRoleController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();
        stat();
    }    
     private void stat()
    {
          
                   Connection cnx = edu.workshopjdbc3a48.utils.DataSource.getInstance().getCnx();

      try {
           
          String query = "SELECT COUNT(id),role FROM user GROUP BY role" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("role"),rs.getInt("COUNT(id)")));
            }     
        } catch (SQLException ex) {
        }
      
        piechart.setTitle("**Statistiques des user Role**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
    
}
