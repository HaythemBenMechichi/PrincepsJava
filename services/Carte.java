/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Produit;
import java.util.ArrayList;

/**
 *
 * @author Dhia werteteni
 */
public final class Carte {
    
    
    private static ArrayList<Produit> Carte = new ArrayList<>();

    public static ArrayList<Produit> getPanier() {
        return Carte;
    }

    public static void addPanier(Produit e) {
        Carte.add(e);
    }

    public static void resetPanier() {
        Carte.clear();
    }

}
