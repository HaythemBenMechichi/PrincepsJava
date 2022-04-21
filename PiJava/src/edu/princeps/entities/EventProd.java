/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.entities;

/**
 *
 * @author zribi
 */
public class EventProd {

    int id, ref_produit;
    Evenement evenement_id;
    String nom_produit;
    Double taux;

    public EventProd(int id, String nom_produit, int ref_produit, Double taux, Evenement evenement_id) {
        this.id = id;
        this.ref_produit = ref_produit;
        this.evenement_id = evenement_id;
        this.nom_produit = nom_produit;
        this.taux = taux;
    }

    public EventProd(String nom_produit, int ref_produit, Double taux, Evenement evenement_id) {
        this.ref_produit = ref_produit;
        this.evenement_id = evenement_id;
        this.nom_produit = nom_produit;
        this.taux = taux;
    }

    public EventProd() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }

    public Evenement getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(Evenement evenement_id) {
        this.evenement_id = evenement_id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "EventProd{" + "id=" + id + ", ref_produit=" + ref_produit + ", evenement_id=" + evenement_id + ", nom_produit=" + nom_produit + ", taux=" + taux + '}';
    }

}
