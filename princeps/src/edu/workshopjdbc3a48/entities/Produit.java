/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.Objects;

/**
 * @author abdelazizmezri
 */
 public class Produit {
    private int id,quantite;
    private String libelle, description,image_p;
    private float prix;
    SousCategorie sous = new SousCategorie();
    
    
    public Produit()
    {
        
    }
  
       
    
    
    
    public Produit(int id,String libelle ,int quantite, String description, String image_p, float prix,SousCategorie sous) {
        this.quantite = quantite;
        this.libelle = libelle;
        this.description = description;
        this.image_p = image_p;
        this.prix = prix;
        this.sous = sous;
        this.id=id;
    }
    
    
    
    
    
    public Produit(String libelle ,int quantite, String description, String image_p, float prix,SousCategorie sous) {
        this.quantite = quantite;
        this.libelle = libelle;
        this.description = description;
        this.image_p = image_p;
        this.prix = prix;
        this.sous = sous;
    }
    
    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

        public String getImage_p() {
        return image_p;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SousCategorie getA() {
        return sous;
    }

    public void setA(SousCategorie a) {
        this.sous = a;
    }
    

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "produit{" +" libelle=" + libelle + " ,quantite=" + quantite + " , description=" + description +  " , image_p=" + image_p + " , prix=" + prix + ",sous_categorie="+ sous.nom_sous +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.quantite;
        hash = 23 * hash + Objects.hashCode(this.libelle);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.image_p);
        hash = 23 * hash + Float.floatToIntBits(this.prix);
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
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image_p, other.image_p)) {
            return false;
        }
        return true;
    }
   
    
}

