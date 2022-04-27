/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.Objects;

/**
 *
 * @author haythem
 */
public class SousCategorie {
    int id,stat_sc;
    String nom_sous;
    Categorie cat;
    public SousCategorie(){
    }
        
   
     public SousCategorie( String nom_sous,Categorie cat){
        this.id = id;
        this.stat_sc = stat_sc;
        this.nom_sous = nom_sous;
       this.cat=cat;
    }

    public SousCategorie(int stat_sc, String nom_sous,Categorie cat){
        this.id = id;
        this.stat_sc = stat_sc;
        this.nom_sous = nom_sous;
       this.cat=cat;
    }
     
     
    public SousCategorie(int id, int stat_sc, String nom_sous,Categorie cat){
        this.id = id;
        this.stat_sc = stat_sc;
        this.nom_sous = nom_sous;
       this.cat=cat;
    }

    public int getId() {
        return id;
    }

    public int getStat_sc() {
        return stat_sc;
    }

    public String getNom_sous() {
        return nom_sous;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStat_sc(int stat_sc) {
        this.stat_sc = stat_sc;
    }

    public void setNom_sous(String nom_sous) {
        this.nom_sous = nom_sous;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.stat_sc;
        hash = 97 * hash + Objects.hashCode(this.nom_sous);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SousCategorie other = (SousCategorie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.stat_sc != other.stat_sc) {
            return false;
        }
        if (!Objects.equals(this.nom_sous, other.nom_sous)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return    nom_sous  ;
    }
            
            
            
            
            
            
            
}
