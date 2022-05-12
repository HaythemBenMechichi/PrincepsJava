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
public class Reclamation {
    private int id,typereclamation_id,niveau,user_id;
    private String sujet_rec;

    public Reclamation()
    {
    this.id = 0;
        this.typereclamation_id = 0;
        this.niveau = 0;
        this.user_id = 0;
        this.sujet_rec = "";
    }
    
    public Reclamation(int id, int typereclamation_id, String sujet_rec, int niveau, int user_id) {
        this.id = id;
        this.typereclamation_id = typereclamation_id;
        this.niveau = niveau;
        this.user_id = user_id;
        this.sujet_rec = sujet_rec;
    }

    public Reclamation(int typereclamation_id, int niveau, int user_id, String sujet_rec) {
        this.typereclamation_id = typereclamation_id;
        this.niveau = niveau;
        this.user_id = user_id;
        this.sujet_rec = sujet_rec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypereclamation_id() {
        return typereclamation_id;
    }

    public void setTypereclamation_id(int typereclamation_id) {
        this.typereclamation_id = typereclamation_id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSujet_rec() {
        return sujet_rec;
    }

    public void setSujet_rec(String sujet_rec) {
        this.sujet_rec = sujet_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", typereclamation_id=" + typereclamation_id + ", niveau=" + niveau + ", user_id=" + user_id + ", sujet_rec=" + sujet_rec + '}';
    }
    
    
    
}
