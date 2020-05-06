package pl.javarun.mywebshop.util;

import org.springframework.stereotype.Controller;
import pl.javarun.mywebshop.service.ConfigDataService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static pl.javarun.mywebshop.util.MailTrapConfig.*;

@Controller
public class EmailContactForm {

    private ConfigDataService configDataService;


    public EmailContactForm(ConfigDataService configDataService) {
        this.configDataService = configDataService;
    }

    public void send(String companyEmail, String senderName, String senderMail, String messageSubject, String messageContent) {


        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", configDataService.getConfigDataByName("mailSmtpHost").getValue());
        prop.put("mail.smtp.port", configDataService.getConfigDataByName("mailSmtpPort").getValue());
        prop.put("mail.smtp.ssl.trust", configDataService.getConfigDataByName("mailSmtpHostSslTrust").getValue());

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        configDataService.getConfigDataByName("mailUser").getValue(),
                        configDataService.getConfigDataByName("mailPassword").getValue()
                );
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress(configDataService.getConfigDataByName("mailContactFormSenderName").getValue()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(companyEmail));
            String subject = String.format(configDataService.getConfigDataByName("mailContactFormSubject").getValue(), messageSubject);
            message.setSubject(subject);


            String msg =String.format(configDataService.getConfigDataByName("mailContactFormMessage").getValue(), senderName, senderMail, messageSubject, messageContent);

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
