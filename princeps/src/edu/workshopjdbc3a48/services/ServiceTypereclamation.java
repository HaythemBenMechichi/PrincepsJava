/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.Typereclamation;
import edu.workshopjdbc3a48.utils.MaConnexion;

/**
 *
 * @author admin
 */
public class ServiceTypereclamation  implements Iservices<Typereclamation>{

        Connection cnx= MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Typereclamation t) throws SQLException {
        //request 
        String req="INSERT INTO `TYPERECLAMATIONS`(`niveau`,`image`) VALUES (?,?)";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,t.getNiveau());
            pst.setString(2,t.getImage());

            pst.executeUpdate();
            System.out.println("Typereclamation ajouter avec Succes");
    }

    @Override
    public void modifier(Typereclamation t) throws SQLException {
        //request 
        String req="UPDATE `TYPERECLAMATIONS` SET `niveau`=? , `image`=? WHERE `id`=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,t.getNiveau());
            pst.setInt(3,t.getId());
            pst.setString(2,t.getImage());

            pst.executeUpdate();
            System.out.println("Modification terminée avec Succes");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        //request 
        String req="DELETE FROM `TYPERECLAMATIONS` WHERE id=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1,id);            
            pst.executeUpdate();
            
        
            System.out.println("TYPERECLAMATIONS Supprimé avec Succes");
    }

    @Override
    public List<Typereclamation> afficher() throws SQLException {
        //LIST
        List<Typereclamation> Typesreclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typesreclamations.add(new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3)));
                
            }
          
        return Typesreclamations;
    }
    
     public Typereclamation getTypereclamation(int ID_typereclamation) throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS where id="+ID_typereclamation;
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        
        return t;   
    }
     
      public Typereclamation getID(String niveau) throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS where niveau='"+niveau+"';";
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        return t;   
    }
     
     public Typereclamation getAlltype() throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS ;";
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        
        return t;   
    }
     public List<Typereclamation> recherche(String rechercher) throws SQLException {
        //LIST
        List<Typereclamation> Reclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM `typereclamations` WHERE id like '%"+rechercher+"%' OR niveau  LIKE '%"+rechercher+"%' or image LIKE '%"+rechercher+"%' ;";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Reclamations.add(new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3)));
                
            }
          
        return Reclamations;
    
}
}
