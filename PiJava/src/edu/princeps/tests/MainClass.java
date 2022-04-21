/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.tests;

import edu.princeps.entities.Evenement;
import edu.princeps.entities.EventProd;
import edu.princeps.services.ServiceEvenement;
import edu.princeps.services.ServicePromotion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.sql.Date;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.web.PromptData;


/**
 *
 * @author zribi
 */
public class MainClass {
      public static void main(String[] args) throws ParseException {
        String date_string = "2022-01-01";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        //Parsing the given String to Date object
        Date date = Date.valueOf(LocalDate.of(2022, Month.APRIL, 7));

        java.sql.Date d = new java.sql.Date(date.getTime());
        
        ServiceEvenement ps = new ServiceEvenement();
        ServicePromotion sp= new ServicePromotion();
        /*
        Evenement e1 = new Evenement(d,d, "image","blackFr");
        */
//        Evenement e2 = new Evenement(59, "aa",d,d, "imagekkkk");
//        ps.modifier(e2);

          ObservableList<EventProd> p = sp.getAll();
          System.out.println(p);
        
     //  ps.ajouter(e1);
    //  ps.getAll(); 
       // sp.supprimer(55);
     //  sp.modifier()
   //  Date d = Date.valueof(LocalDate.of(2022, Month.APRIL, 7));
    }
    
}
