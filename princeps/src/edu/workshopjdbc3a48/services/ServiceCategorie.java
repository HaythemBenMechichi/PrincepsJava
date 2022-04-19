/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Sous_categorie;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author haythem
 */
public class ServiceCategorie implements IService<Categorie>{
    
    
        Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Categorie p) {
        try {
            String req = "INSERT INTO categorie (nom_c,stat_c,image_car) VALUES ('   " + p.getNom_c() + "', '" +p.getStat_c() +"', '" +p.getImage_car() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
    @Override
    public void modifier(Categorie p) {
        try {
            String req = "UPDATE categorie SET `nom_c` = '" + p.getNom_c() + "', `stat_c` = '" + p.getStat_c() +  "', `image_car` = '" + p.getImage_car()   +  "' WHERE `produit`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    @Override
    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie p = new Categorie(rs.getInt(1), rs.getInt("stat_c") , rs.getString("nom_c"),rs.getString("image_car"));
          //      System.out.println("cat = "+p);
                list.add(p);
            }
                  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    
    
    
      public Categorie getCat(int id)
    {
        Categorie cat=new Categorie();
         try {
            String req = "Select * from categorie where categorie.id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
         
           while(rs.next()){
                
                Categorie c = new Categorie(rs.getInt("id"), rs.getInt("stat_c"), rs.getString("nom_c"),rs.getString("image_car"));
            //    System.out.println("p="+c);
                return c;
             }
         }
          catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return(cat);
    }
    
    
    
        
    public List<Sous_categorie> geAllSousCat()
    {
       List<Sous_categorie> list = new ArrayList<>();

        Sous_categorie cp = new Sous_categorie();
         try {
            String req = "Select * from sous_categorie" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie cat= new Categorie();
                cat=getCat(rs.getInt("id_cat_id"));
                Sous_categorie p = new Sous_categorie(rs.getInt("id"), rs.getInt("stat_sc"), rs.getString("nom_sous"),cat);
            //    System.out.println("p="+c);
                list.add(p);
             }
         }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (list);
    }
    
    
 
    @Override
    public void ajout(Produit P) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
