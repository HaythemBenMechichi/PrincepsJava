/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author zribi
 */
public class Evenement {

    int id;
    Date DateDebut, DateFin;
    String image, nom;

    public Evenement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Evenement(String nom, Date DateDebut, Date DateFin, String image) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.image = image;
        this.nom = nom;
    }

    public Evenement(int id, String nom, Date DateDebut, Date DateFin, String image) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.image = image;
        this.nom = nom;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}
