/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.SousCategorie;
import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public interface IService <T>{
    public void ajouter(T  p);
    public void ajout(Produit P);
    public void supprimer(int id);
    public void modifier(T p);
    public List<T> getAll();
    
}
