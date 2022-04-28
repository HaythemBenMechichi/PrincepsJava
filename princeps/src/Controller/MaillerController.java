/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author soon
 */
public class MaillerController {
    Session newSession =null;
    MimeMessage mimeMessage = null;
    public void sendEmail() throws MessagingException {
    String fromUser = "monemehamila@gmail.com";  //Enter sender email id
    String fromUserPassword = "191JMT1346";  //Enter sender gmail password , this will be authenticated by gmail smtp server
    String emailHost = "smtp.gmail.com";
    Transport transport = newSession.getTransport("smtp");
    transport.connect(emailHost, fromUser, fromUserPassword);
    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
    transport.close();
    System.out.println("Email successfully sent!!!");
}

public void draftEmailAct(String email,String Code) throws AddressException, MessagingException, IOException {

   
    String emailReceipients = email;
    //Enter list of email recepients
    String emailSubject = "Account Confirmation";
    String emailBody = "Hello , Your activation code is : "+Code+", Please use it to activate your account in the app";
    mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients));
    
    mimeMessage.setSubject(emailSubject);
    

    MimeBodyPart bodyPart = new MimeBodyPart();
    bodyPart.setContent(emailBody,"text/html");
    MimeMultipart multiPart = new MimeMultipart();
    multiPart.addBodyPart(bodyPart);
    System.out.println("hhh");
    mimeMessage.setContent(multiPart);
   
}
public void setupServerProperties() {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    newSession = Session.getDefaultInstance(properties,null);
}


private static final MaillerController instance = new MaillerController();
public static MaillerController get(){
    return instance;
}
    
}
