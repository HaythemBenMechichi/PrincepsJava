/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author abdelazizmezri
 */
public class ServicePersonne implements IService<Produit> {

    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void ajout(Produit p) {
        try {
            String req = "INSERT INTO produit (libelle,quantite,description,image_p,prix,id_sous_cat_id) VALUES ('   " + p.getLibelle() + "', '" +p.getQuantite() +"', '" +p.getDescription()+ "', '" + p.getImage_p()+"', '" + p.getPrix() + "','" + p.getA().getId()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit created !");
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



   public void ajouter(SousCategorie p) {
        try {
            String req = "INSERT INTO sous_categorie (nom_sous,stat_sc,id_cat_id) VALUES ('   " + p.getNom_sous() + "', '" +p.getStat_sc() +"', '" +p.getCat().getId()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("sous categorie created !");
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
//    public void ajouter2(produit p) {
//        try {
//            String req = "INSERT INTO `prosuit` (`nom`, `prenom`) VALUES (?,?)";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setString(2, p.getLibelle());
//            ps.setString(1, p.getDescription());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
     public void SupprimerNom(String libelle){
       
       String requete = "delete from produit where libelle=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,libelle);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            assert libelle !=null;
           Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    
    
    
     
     
     
    
    @Override
    public void modifier(Produit p) {
        try {
            String req = "UPDATE produit SET `libelle` = '" + p.getLibelle() + "', `prix` = '" + p.getPrix() +  "', `description` = '" + p.getDescription()   +  "', `quantite` = '" + p.getQuantite()  +   "', `quantite` = '" + p.getQuantite()+"', `image_p` = '" + p.getImage_p()+"', `id_sous_cat_id` = '" + p.getA().getId()+ "' WHERE `produit`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
    
    
     
      public Produit rechercheProd(String po) {
          Produit cat= new Produit();
        try {
            
            System.out.println("po = "+ po);
                    
            
            String req = "Select * from  produit where libelle = '"+po+"'"  ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                s = getSousCat(rs.getInt("id_sous_cat_id"));
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
           System.out.println("p="+p);
                return p;
             }
         }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (cat);
    }
      
      
     
      
      
      
      public List<Produit> rechercheProdSous(SousCategorie po) {
        List<Produit> list = new ArrayList<>();
        try {
            
            System.out.println("po = "+ po);
                    
            
            String req = "Select * from  produit where id_sous_cat_id = '"+po.getId()+"'"  ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                s = getSousCat(rs.getInt("id_sous_cat_id"));
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
           System.out.println("p="+p);
                           list.add(p);

             }
                            return list;

         }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (list);
    }
      
      
      
      
    
    public SousCategorie getSousCat(int id)
    {
        SousCategorie cp = new SousCategorie();
         try {
            String req = "Select * from sous_categorie where sous_categorie.id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie cat= new Categorie();
                cat=getCat(rs.getInt("id_cat_id"));
                SousCategorie c = new SousCategorie(rs.getInt("id"), rs.getInt("stat_sc"), rs.getString("nom_sous"),cat);
            //    System.out.println("p="+c);
            
                return c;
             }
         }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (cp);
    }
    
    





    
    public SousCategorie getSousCatId(String nom_sous)
    {
        SousCategorie cp = new SousCategorie();
         try {
            String req = "Select * from sous_categorie where sous_categorie.nom_sous=" + nom_sous;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie cat= new Categorie();
                cat=getCat(rs.getInt("id_cat_id"));
                SousCategorie c = new SousCategorie(rs.getInt("id"), rs.getInt("stat_sc"), rs.getString("nom_sous"),cat);
            //    System.out.println("p="+c);
                return c;
             }
         }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (cp);
    }
    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                s = getSousCat(rs.getInt("id_sous_cat_id"));
               // System.out.println("s = "+s);
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
               // System.out.println("p="+p);
                list.add(p);
            }
                  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    
    
    public List<Produit> getAllASC() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit ORDER BY prix ASC ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                s = getSousCat(rs.getInt("id_sous_cat_id"));
               // System.out.println("s = "+s);
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
               // System.out.println("p="+p);
                list.add(p);
            }
                  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
     public List<Produit> getAllDESC() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit ORDER BY prix DESC ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                SousCategorie s = new SousCategorie();
                s = getSousCat(rs.getInt("id_sous_cat_id"));
               // System.out.println("s = "+s);
                Produit p = new Produit( rs.getString("libelle"), rs.getInt("quantite"),rs.getString("description"),rs.getString("image_p"),rs.getFloat("prix"),s);
               // System.out.println("p="+p);
                list.add(p);
            }
                  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    
    @Override
    public void ajouter(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
