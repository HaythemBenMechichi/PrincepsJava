/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.services;

import edu.princeps.entities.Evenement;
import edu.princeps.utils.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zribi
 */
public class ServiceEvenement implements IService<Evenement>  {
    Connection cnx = Dbconnection.getInstance().getCnx();

    @Override
    public void ajouter(Evenement E) {
        try {
            String req = "INSERT INTO `evenement` (`date_debut`, `date_fin`, `image`, `nom`) VALUES ('" + E.getDateDebut() + "','" + E.getDateFin() + "','" + E.getImage() + "','" + E.getNom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Evenement E) {
        try {
            String req = "INSERT INTO `evenement` ( `date_debut`, `date_fin`, `image`, `nom`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setDate(2, E.getDateDebut());
            ps.setDate(3, E.getDateFin());
            ps.setString(4, E.getImage());
            ps.setString(5, E.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement E) {
        try {
            String req = "UPDATE `evenement` SET `date_debut` = '"+ E.getDateDebut()+"',`date_fin` = '"+ E.getDateFin()+"', `image` = '" + E.getImage() + "', `nom` = '" + E.getNom() + "' WHERE `id` = " + E.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> getAll() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Evenement E = new Evenement(rs.getInt(1),rs.getString("nom"),rs.getDate("date_debut"),rs.getDate("date_fin"), rs.getString("image"));
                list.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
}
