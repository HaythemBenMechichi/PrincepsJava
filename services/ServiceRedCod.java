/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.RedCod;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dhia werteteni
 */
public class ServiceRedCod {

    /**
     * This method is used to add an Blog to database
     *
     * @param redcod
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    /**
     * This method is used to take all the Blog list.
     *
     * @return
     */
    public ObservableList<RedCod> getAllCoupon() {

        ObservableList<RedCod> CouponList = FXCollections.observableArrayList();
        Connection conn = DataSource.getInstance().getCnx();
        try {
            String sql = "Select * FROM `redcod`";
            Statement statement = conn.createStatement();
            ResultSet rst = statement.executeQuery(sql);
            while (rst.next()) {
                RedCod Coupon = new RedCod(rst.getInt(1), rst.getString(2));
                CouponList.add(Coupon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRedCod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CouponList;
    }
    
    public boolean verif(String c) {
            boolean status = false;
        try {
            String req = "Select * from `redcod` where `redcod`.`redcod`='"+ c +"'";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                status = c.equals(rs.getString("redcod"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRedCod.class.getName()).log(Level.SEVERE, null, ex);
        }
            return status;
    }
}

