/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;

/**
 *
 * @author Dhia werteteni
 */
public class Commande {

   
    int id;
    int user;
    Date date;
    float prix;
    
    public Commande(int id, int user, Date date, float prix) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.prix = prix;
    }

    public Commande( int user, Date date,float prix) {
        this.user = user;
        this.date = date;
        this.prix = prix;
    }
    
    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commande{" + "user=" + user + ", date=" + date + ", prix=" + prix + '}';
    }
}
