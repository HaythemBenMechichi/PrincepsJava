/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.utils;

/**
 *
 * @author EXTREME-GAMING
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dhia werteteni
 */
public class EmailsUtils {
   
    public static void sendMail(String recepient, String Body) throws Exception{
        System.out.println("Preparing to send:");
        Properties properties = new Properties();
        String myAccountEmail ="princeps.ordre@gmail.com";
        String password ="esprit2022";       
        properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
       
       
       
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
       
        Message message = prepareMessage(session,myAccountEmail,recepient,Body);
       
        Transport.send(message);
        System.out.println("message send successfully");
    }
   
    private static Message prepareMessage(javax.mail.Session session, String myAccountEmail,String recepient, String Body){
        try {
           
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("confirmation commande");
            message.setText(Body);
           
            return message;
        } catch (MessagingException ex) {
         
        }
        return null;
    }
       
}

