/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Evenement;
import edu.workshopjdbc3a48.entities.EventProd;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zribi
 */
public class ServicePromotion implements IService<EventProd> {

        Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(EventProd p) {
        try {
            String req = "INSERT INTO `event_prod` (`nom_produit`, `ref_prod`, `taux`, `evenement_id`) VALUES ('" + p.getNom_produit() + "','" + p.getRef_produit() + "','" + p.getTaux() + "','" + p.getEvenement_id().getId() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(EventProd p) {
        try {
            String req = "UPDATE `event_prod` SET `nom_produit` = '" + p.getNom_produit() + "',`ref_prod` = '" + p.getRef_produit() + "', `taux` = '" + p.getTaux() + "', `evenement_id` = '" + p.getEvenement_id().getId() + "' WHERE `id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `event_prod` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<EventProd> getAll() {
        ObservableList<EventProd> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from event_prod p, evenement e where e.id = p.evenement_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getInt("e.id"), rs.getString("e.nom"));
                EventProd p = new EventProd(rs.getInt("p.id"), rs.getString("p.nom_produit"), rs.getInt("p.ref_prod"), rs.getDouble("p.taux"), e);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void ajout(Produit P) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
