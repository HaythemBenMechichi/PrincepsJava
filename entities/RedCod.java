/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author Dhia werteteni
 * 
 */
public class RedCod {
    private int id;
    private String Coupon;

    public RedCod(int id, String Coupon) {
        this.id = id;
        this.Coupon = Coupon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String Coupon) {
        this.Coupon = Coupon;
    }

    @Override
    public String toString() {
        return "redcod{" +  ", redcod=" + Coupon + '}';
    }
 
}
