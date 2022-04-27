/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import java.util.ArrayList;
import edu.workshopjdbc3a48.entities.User;

/**
 *
 * @author chams
 */

public interface iUserServices {
    
    public void ajouterUser(User u);
    public void modifierUser(User u); 
    public void supprimerUser( int id);
    public ArrayList<User> afficherUser();
    public void modifierPassword (User u);
    
}
