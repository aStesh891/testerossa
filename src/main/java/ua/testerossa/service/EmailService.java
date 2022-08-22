package ua.testerossa.service;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class EmailService {
  
  public static final String SOURCE_EMAIL = "source@gmail.com";
  public static final String SOURCE_EMAIL_PSWD = "****";
  
  public void sendEmail(String email, String msg) {
    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtp.mailtrap.io");
    prop.put("mail.smtp.port", "2525");
    prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
    
    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(SOURCE_EMAIL, SOURCE_EMAIL_PSWD);
      }
    });
    
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("ann.steshhh@gmail.com"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
      message.setSubject("NEW REGISTRATION REQUEST");
      
      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
      message.setContent(multipart);
      
      Transport.send(message);
    } catch (
        MessagingException e) {
      e.printStackTrace();
    }
  }
  
}
