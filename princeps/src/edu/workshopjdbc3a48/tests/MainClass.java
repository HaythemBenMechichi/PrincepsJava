/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) {
//        
//        Produit p1 = new Produit(3,"Abdelaziz",33,"desc","image",200,3);
    //    Produit p2 = new Produit(40,"aaaaaaaaaaaaaaaa",33,"desc","image",200,2);
//        Categorie c1= new Categorie(20,23,"hassen","taswira");
        
        ServicePersonne sp = new ServicePersonne();
        ServiceCategorie cp= new ServiceCategorie();
            //    sp.getSousCat(9);
     //    sp.ajouter(p1);    
//        sp.ajouter(p1);
   //  sp.modifier(p2);
  //     cp.supprimer(13);
        
//sp.getAll();


  }    
}
