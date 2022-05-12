/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author soon
 */
public class UserSession {
      public static UserSession getInstance() {
        return instance;
    }

    private static int id;
    public static UserSession instance;
   
    private static int age;
    private static String name;
    private static String prenom;
    private static String email;
    private static String number;
    private static String role;
    private static String Password;


    public static void setId(int id) {
        UserSession.id = id;
    }

    public static int getId() {
        return id;
    }

  

    public static String getName() {
        return name;
    }

    public static String getPrenom() {
        return prenom;
    }


    public static void setRole(String role) {
        UserSession.role = role;
    }

    public static String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        UserSession.age = age;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        UserSession.number = number;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String Password) {
        UserSession.Password = Password;
    }
    

 
    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }
   public UserSession(int id, String role, String email, String nom, String prenom, String Number, int age, String Password) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = nom;
        this.prenom = prenom;
        this.number= Number;
        this.age= age;
        this.Password=Password;
        
    }

    public static UserSession getInstance(int id, String role, String email, String nom, String prenom, String Number, int age, String Password) {
        if (instance == null) {
            instance = new UserSession(id,  role,  email, nom,  prenom,  Number,  age,  Password);
        }


        return instance;

    }

    public static void cleanUserSession() {
        id = 0;
        email = null;
        role = null;
        name = null;
        prenom = null;
        number = null;
        Password =null;
        instance = null;
    }

    @Override
    public String toString() {
        return "Logged in as {" +
                "Email='" + email + '\'' +
                ", Role ='" + role + '\'' +
                ", nom ='" + name + '\'' +
                ", password ='" + Password + '\'' +
                ", number ='" + number + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
