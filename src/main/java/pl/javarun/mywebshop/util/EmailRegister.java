package pl.javarun.mywebshop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailRegister {

    private String username;


    public EmailRegister() {
    }

    public void send(String username, String token){

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("0100d4b8b18279", "ff18b7f098f839");
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("no.replyl@javarun.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(username));
            message.setSubject("Register new user: "+username);


            String msg = "Hi "+username+" click here to confirm register <a href=\"http://localhost:8080/qunsztowna/?action=activate&regId="+token+"\">click me</a>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
