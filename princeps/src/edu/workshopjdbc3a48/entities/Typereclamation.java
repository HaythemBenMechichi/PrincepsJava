/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author admin
 */
public class Typereclamation{
    private int id;
    private String niveau,image;

    public Typereclamation() {
        this.id = 0;
        this.niveau = "";
    }

    
    public Typereclamation(int id, String niveau,String image) {
        this.id = id;
        this.niveau = niveau;
        this.image= image;
    }

    public Typereclamation(String niveau,String image) {
        this.niveau = niveau;this.image= image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Typereclamation{" + "id=" + id + ", niveau=" + niveau + ", image=" + image + '}';
    }

 
    
}
