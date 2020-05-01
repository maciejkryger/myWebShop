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
public class EmailRegister {

    private ConfigDataService configDataService;



    public EmailRegister(ConfigDataService configDataService) {
        this.configDataService=configDataService;
    }

    public void send(String username, String name, String email, String token){
        System.out.println("register mail is sending... step2...");
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", configDataService.getConfigDataByName("mailSmtpHost").getValue());
        prop.put("mail.smtp.port", configDataService.getConfigDataByName("mailSmtpPort").getValue());
        prop.put("mail.smtp.ssl.trust", configDataService.getConfigDataByName("mailSmtpHostSslTrust"));

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("register mail in progress...password authentication");
                return new PasswordAuthentication(
                        configDataService.getConfigDataByName("mailUser").getValue(),
                        configDataService.getConfigDataByName("mailPassword").getValue()
                );
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("rejestracja@qunsztowna.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            String subject = "QUNSZTOWNA.pl - rejestracja użytkownika: ";
            message.setSubject(subject);


            String msg = "Dzień dobry <B>"+name+"</B>,<BR><BR> dziękujemy za rejestrację w sklepie QUNSZTOWNA.<BR>" +
                    "By aktywować użytkownika i potwierdzić rejestrację <a href=\"http://qunsztowna.javarun.pl/activation?username="+username+"&regId="+token+"\">kliknij tutaj</a>";

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
