/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;


/**
 *
 * @author Dhia werteteni
 */
public class CommandeDetails {

   
    int id, produit, order, quantity;

    public CommandeDetails(int produit, int order, int quantity) {
        this.produit = produit;
        this.order = order;
        this.quantity = quantity;
    }

    public CommandeDetails(int id, int produit, int order, int quantity) {
        this.id = id;
        this.produit = produit;
        this.order = order;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }
    
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CommandeDetails{" + "produit=" + produit + ", order=" + order + ", quantity=" + quantity + '}';
    }
    
}
