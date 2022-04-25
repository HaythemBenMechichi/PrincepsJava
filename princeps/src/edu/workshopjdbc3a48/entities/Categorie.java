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
public class Categorie {
 int id,stat_c;
 String nom_c,image_car;  

    
    public Categorie()
    {}
    
   
    
      public Categorie( String nom_c, String image_car) {
        this.nom_c = nom_c;
        this.image_car = image_car;
    }
    
    
    
     public Categorie(int id, String nom_c, String image_car) {
        this.id = id;
        this.nom_c = nom_c;
        this.image_car = image_car;
    }
    
    public Categorie(int id, int stat_c, String nom_c, String image_car) {
        this.id = id;
        this.stat_c = stat_c;
        this.nom_c = nom_c;
        this.image_car = image_car;
    }

    public int getId() {
        return id;
    }

    public int getStat_c() {
        return stat_c;
    }

    public String getNom_c() {
        return nom_c;
    }

    public String getImage_car() {
        return image_car;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStat_c(int stat_c) {
        this.stat_c = stat_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public void setImage_car(String imager_car) {
        this.image_car = imager_car;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.stat_c;
        hash = 23 * hash + Objects.hashCode(this.nom_c);
        hash = 23 * hash + Objects.hashCode(this.image_car);
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
        final Categorie other = (Categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.stat_c != other.stat_c) {
            return false;
        }
        if (!Objects.equals(this.nom_c, other.nom_c)) {
            return false;
        }
        if (!Objects.equals(this.image_car, other.image_car)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", stat_c=" + stat_c + ", nom_c=" + nom_c + ", imager_car=" + image_car + '}';
    }
    
    
}
