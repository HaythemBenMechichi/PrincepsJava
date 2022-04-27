/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author soon
 */
public class Admin extends User{

    public Admin() {
    }

    public Admin(int age, String name, String prenom, String email, String number,String role) {
        super(age, name, prenom, email, number,role);
    }
    

    public Admin(int id, int age, String name, String prenom, String email, String number, String role, String Password) {
        super(id, age, name, prenom, email, number, role, Password);
    }

    public Admin(int id, int age, String name, String prenom, String email, String number,String role) {
        super(id, age, name, prenom, email, number,role);
    }

    public Admin(int age, String name, String prenom, String email, String number, String role, String password) {
        super(age, name, prenom, email, number, role, password);
    }

   
    
    
}
