package pl.javarun.mywebshop.util;

import pl.javarun.mywebshop.model.Company;
import pl.javarun.mywebshop.repository.CompanyRepository;
import pl.javarun.mywebshop.service.CompanyService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static pl.javarun.mywebshop.util.SecretData.*;

public class EmailContactForm {


    public EmailContactForm() {

    }

    public void send(String companyEmail, String senderName, String senderMail, String messageSubject, String messageContent){


        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", mailSmtpHost);
        prop.put("mail.smtp.port", mailSmtpPort);
        prop.put("mail.smtp.ssl.trust", mailSmtpHostSslTrust);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPassword);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("formularz_kontaktowy@qunsztowna.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(companyEmail));
            message.setSubject(messageSubject);


            String msg = "<B>Masz wiadomość z formularza kontaktowego qunsztowna.pl</B><BR>"+
                    "<B>Imię: </B>"+senderName+"<BR>"+ "<B>Email: </B>"+senderMail+"<BR>"+
                    "<B>Temat wiadomości: </B>"+messageSubject+"<BR>"+
                    "<B>Wiadomość: </B>"+messageContent;

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
