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
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.utils.DataSource;
import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Livreur;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chams
 */
public class UserServices implements iUserServices{
    private PreparedStatement pst;
    Connection cnx = DataSource.getInstance().getCnx();
    
     

 
     public void ajouterUser( User u){
         
        
        String  requete="insert into user (age,nom,prenom,email,number,role,password,flag) values(?,?,?,?,?,?,?,?)";
           try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, u.getAge());
            ps.setString(2, u.getName());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getNumber());
            if(u instanceof Admin)
            {
            ps.setString(6,"['ROLE_ADMIN']");
            }
            else if(u instanceof Client)
            {
                ps.setString(6,"['ROLE_CLIENT']");
            }
            else
            {
                ps.setString(6,"['ROLE_LIVREUR']");
            }
            
            ps.setString(7, u.getPassword());
            ps.setInt(8,1);
            ps.executeUpdate();
            System.out.println("ajout effectuée avec succès");
        } catch (SQLException ex) {
           
            System.out.println("erreur lors de la ajout "+ex.getMessage());
        }
    }
    

   
      public void modifierUser (User u){
         String requete="UPDATE `user` SET `age`='"+u.getAge()+"',`nom`='"+u.getName()+"',`prenom`='"+u.getPrenom()+"',`number`='"+u.getNumber()+"',`email`='"+u.getEmail()+"',`role`='"+ u.getRole() +"' where `user`.`id`= "+u.getId();
        

         
         try{
              Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("user modifié");
        } catch (SQLException ex) {
            System.out.println("erreur lors d "+ex.getMessage());
        }
     }
      public void modifierPassword (String pass , int id){
         String requete="UPDATE `user` SET `password`='"+pass+"' where `user`.`id`= "+id;
         

         
         try{
              Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("user modifié");
        } catch (SQLException ex) {
            System.out.println("erreur lors d "+ex.getMessage());
        }
     }
    
    /**
     *
     * @param id
     */
    @Override
    public void supprimerUser(int id) {
      
            String requete = "delete from user where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
        
    }
   
    @Override
    public ArrayList<User> afficherUser() {
       ArrayList<User> users = new ArrayList<>();
        try {
            String sql1="select age,nom,prenom,email,number,role from user";
             Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(sql1);
            
            User u;
        while (res.next()) {
            if("['ROLE_ADMIN']".equals(res.getString("role")))
            {
                u = new Admin( res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),res.getString("role"));
            }
            else if("['ROLE_CLIENT']".equals(res.getString("role")))
            {
                u = new Client(res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),res.getString("role"));
            }
            else
            {
                u = new Livreur( res.getInt("age"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("number"),res.getString("role"));
            }
            
    users.add(u);
    
    
    
}
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
return users;
    }

   
public void rechercher(String index){
List<User> result = afficherUser().stream().filter(line -> index.equals(line.getId())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);

}
   

   
    
    
    
}
