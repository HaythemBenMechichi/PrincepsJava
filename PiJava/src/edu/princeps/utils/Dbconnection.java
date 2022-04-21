/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zribi
 */
public class Dbconnection {
     final String url ="jdbc:mysql://localhost:3306/db_data";
    final String user="root";
    final String password="";
    
    static Dbconnection instance ;
    private Connection con;
    
    private Dbconnection(){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connection established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Dbconnection getInstance(){
    if (instance == null)
        instance = new Dbconnection();
    return instance;
    }

    public Connection getCnx() {
        return con;
    }
    
    
    
}
